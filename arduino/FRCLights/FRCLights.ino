#include <FastLED.h>

#define LED_PIN     5
#define NUM_LEDS    40
#define BRIGHTNESS  64
#define LED_TYPE    WS2811
#define COLOR_ORDER GRB
CRGB leds[NUM_LEDS];

CHSV colorPatternLeft[20]; // LEDs 20 - 39
CHSV colorPatternRight[20]; // LEDs 0 - 19

int currentIndex;
int currentDirection;
int currentSpeed;

int temporaryStateTimer;

char currentPattern;
char previousPattern;

bool wrap;
bool useTimer;

#define CONVEYOR_SPEED  100

void setup() 
{
    delay( 3000 ); // power-up safety delay
    FastLED.addLeds<LED_TYPE, LED_PIN, COLOR_ORDER>(leds, NUM_LEDS).setCorrection( TypicalLEDStrip );
    FastLED.setBrightness(  BRIGHTNESS );

    Serial.begin(9600);
    Serial.println("<Arduino is ready>");
    
    currentIndex = 0;
    currentDirection = 1;
    currentSpeed = 30;

    currentPattern = '-';
    setNoPattern();
}

void loop()
{   
  RunPattern();
  
  if (Serial.available() > 0 && !useTimer) {
    char receivedChar = Serial.read();
    if (currentPattern != receivedChar) {
      previousPattern = currentPattern;
      currentPattern = receivedChar;
    }

    NewPattern();
  }

  temporaryStateTimer -= 1;
  if (temporaryStateTimer < 0 && useTimer) {
    currentPattern = previousPattern;
    NewPattern();
  }

  FillLEDs();
  
  FastLED.show();
  FastLED.delay(1000 / currentSpeed);
  
}

void FillLEDs()
{ 
  if (wrap) {
    for (int i = 0; i < 20; i++) {
      unsigned int index = (i + currentIndex);
      index = index % 20;
      leds[39 - i] = colorPatternLeft[index];
      leds[i] = colorPatternRight[index];
    }
  }
  else {
    for (int i = 0; i < 20; i++) {
      leds[39 - i] = CHSV(0, 0, 0);
      leds[i] = CHSV(0, 0, 0);
    }
    for (int i = 0; i < 20; i++) {
      if ((i + currentIndex) < 20 && (i + currentIndex) >= 0) {
        leds[39 - i] = colorPatternLeft[(i + currentIndex)];
        leds[i] = colorPatternRight[(i + currentIndex)];
      }
    }
  }
}

void RunPattern() 
{
  switch(currentPattern) {
    case 'C':  // Requesting cube drop - Purple Blinking
      runBlinkPatternC();
      break;
    case 'D':  // Rave
      runBounce();
      break;
    case 'E':  // Red moving conveyor down
    case 'F':  // Red moving conveyor up
    case 'G':  // Blue moving conveyor down
    case 'H':  // Blue moving conveyor up
      runShift();
      break;
    case 'I':  // 30 seconds left - yellow pulsing
      runPulse();
      break;
    case 'J':
      runBlinkPatternJ();
      break;
    case 'K':
      runBlinkPatternK();
      break;
    default:  // null state
      break;
  }
}

void runShift() 
{
  
  currentIndex += currentDirection;

}

void runBounce() 
{
  
  currentIndex += currentDirection;

  if (currentIndex >= 9) {
    currentDirection = -1;
  }
  else if (currentIndex <= -9) {
    currentDirection = 1;
  }
  
}

void runBlinkPatternC() 
{

  switch(temporaryStateTimer) {
    case 8:
    case 6:
    case 4:
    case 2:
    case 1:
    case 0:
      setPatternC();
      break;
    default:
      setNoPattern();
      break;
  }
  
}

void runBlinkPatternJ() 
{

  switch(temporaryStateTimer) {
    case 8:
    case 6:
    case 4:
    case 2:
    case 1:
    case 0:
      setPatternJ();
      break;
    default:
      setNoPattern();
      break;
  }
  
}

void runBlinkPatternK() 
{

  switch(temporaryStateTimer) {
    case 8:
    case 6:
    case 4:
    case 2:
    case 1:
    case 0:
      setPatternK();
      break;
    default:
      setNoPattern();
      break;
  }
  
}

void runPulse()
{

  int num = temporaryStateTimer % 100;
  if (num >= 50) {
    num = 100 - num;
  }

  int value = 105 + num * 1.5;

  for (int i = 0; i < 20; i++) {
    if (colorPatternLeft[i].value != 0) {
      colorPatternLeft[i].value = value;
    }
    if (colorPatternRight[i].value != 0) {
      colorPatternRight[i].value = value;
    }
  }
  
}

void NewPattern() 
{
  
  currentIndex = 0;
  currentDirection = 0;
  currentSpeed = 1000;
  wrap = false;
  useTimer = false;

  setNoPattern();

  switch(currentPattern) {
    case 'A':  // Red alliance normal state - solid red
      setPatternA();
      break;
    case 'B':  // Blue alliance normal state - solid blue
      setPatternB();
      break;
    case 'C':  // Requesting cube drop - purple blinking
      temporaryStateTimer = 9;
      useTimer = true;
      currentSpeed = 8;
      break;
    case 'D':  // Rave - rainbow bouncing
      setPatternD();
      currentSpeed = 100;
      currentDirection = 1;
      break;
    case 'E':  // Red conveyor moving down - red shifting down
      setPatternE();
      wrap = true;
      currentSpeed = CONVEYOR_SPEED;
      currentDirection = -1;
      break;
    case 'F':  // Red conveyor moving up - red shifting up
      setPatternE();
      wrap = true;
      currentSpeed = CONVEYOR_SPEED;
      currentDirection = 1;
      break;
    case 'G':  // Blue conveyor moving down - red shifting down
      setPatternG();
      wrap = true;
      currentSpeed = CONVEYOR_SPEED;
      currentDirection = -1;
      break;
    case 'H':  // Blue conveyor moving up - red shifting up
      setPatternG();
      wrap = true;
      currentSpeed = CONVEYOR_SPEED;
      currentDirection = 1;
      break;
    case 'I':  // 30 seconds left - yellow pulsing
      setPatternI();
      temporaryStateTimer = 200;
      useTimer = true;
      currentSpeed = 100;
      break;
    case 'J':  // Auto switch on left - left side blink green
      temporaryStateTimer = 9;
      useTimer = true;
      currentSpeed = 8;
      break;
    case 'K':  // Auto switch on right - right side blink green
      temporaryStateTimer = 9;
      useTimer = true;
      currentSpeed = 8;
      break;
    default:  // null state
      setNoPattern();
      break;
  }
}

void setNoPattern() 
{
  for (int i = 0; i < 20; i++) {
    colorPatternLeft[i] = CHSV(0, 0, 0);
    colorPatternRight[i] = CHSV(0, 0, 0);
  }
}

void setPatternA() 
{
  for (int i = 0; i < 20; i++) {
    colorPatternLeft[i] = CHSV(0, 255, 255);
    colorPatternRight[i] = CHSV(0, 255, 255);
  }
}

void setPatternB() 
{
  for (int i = 0; i < 20; i++) {
    colorPatternLeft[i] = CHSV(160, 255, 255);
    colorPatternRight[i] = CHSV(160, 255, 255);
  }
}

void setPatternC() 
{
  for (int i = 0; i < 20; i++) {
    colorPatternLeft[i] = CHSV(192, 255, 255);
    colorPatternRight[i] = CHSV(192, 255, 255);
  }
}

void setPatternD() 
{
  for (int i = 0; i < 10; i++) {
    colorPatternLeft[9-i] = CHSV(128 - 13*i, 255, 255 - 20*i);
    colorPatternRight[9-i] = CHSV(128 - 13*i, 255, 255 - 20*i);
  }
  for (int i = 0; i < 10; i++) {
    colorPatternLeft[i+10] = CHSV(128 + 13*i, 255, 255 - 20*i);
    colorPatternRight[i+10] = CHSV(128 + 13*i, 255, 255 - 20*i);
  }
}

void setPatternE() 
{
  for (int i = 0; i < 8; i++) {
    colorPatternLeft[i+6] = CHSV(0, 255, 255);
    colorPatternRight[i+6] = CHSV(0, 255, 255);
  }
}

void setPatternG() 
{
  for (int i = 0; i < 8; i++) {
    colorPatternLeft[i+6] = CHSV(160, 255, 255);
    colorPatternRight[i+6] = CHSV(160, 255, 255);
  }
}

void setPatternI()
{
  for (int i = 0; i < 20; i++) {
    colorPatternLeft[i] = CHSV(64, 255, 255);
    colorPatternRight[i] = CHSV(64, 255, 255);
  }
}

void setPatternJ()
{
  for (int i = 0; i < 20; i++) {
    colorPatternLeft[i] = CHSV(96, 255, 255);
  }
}

void setPatternK()
{
  for (int i = 0; i < 20; i++) {
    colorPatternRight[i] = CHSV(96, 255, 255);
  }
}

