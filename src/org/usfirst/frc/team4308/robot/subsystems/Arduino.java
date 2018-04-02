package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arduino extends Subsystem {
	
	private SerialPort serial;
	private String alliance;
	
	public Arduino() {
		serial = new SerialPort(9600, SerialPort.Port.kUSB1);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void setAlliance(String alliance) {
		this.alliance = alliance;
	}
	
	public void setState(String state) {
		switch(state) {
			case "normal":
				if (alliance.equals("red")) {
					stateRed();
				}
				else {
					stateBlue();
				}
				break;
			
			case "rave":
				stateRave();
				break;
			
			case "conveyor down":
				if (alliance.equals("red")) {
					stateRedConveyorDown();
				}
				else {
					stateBlueConveyorDown();
				}
				break;
			
			case "conveyor up":
				if (alliance.equals("red")) {
					stateRedConveyorUp();
				}
				else {
					stateBlueConveyorUp();
				}
				break;
			
			case "cube drop":
				messageCubeDrop();
				break;
			
			case "endgame":
				messageEndgame();
				break;
				
			case "auto left":
				messageAutoLeft();
				break;
				
			case "auto right":
				messageAutoRight();
				break;
				
			default:
				stateOff();
				break;
		}
	}
	
	public void stateOff() {
		serial.writeString("-");
	}
	
	public void stateRed() {
		serial.writeString("A");
	}
	
	public void stateBlue() {
		serial.writeString("B");
	}
	
	public void messageCubeDrop() {
		serial.writeString("C");
	}
	
	public void stateRave() {
		serial.writeString("D");
	}
	
	public void stateRedConveyorDown() {
		serial.writeString("E");
	}
	
	public void stateRedConveyorUp() {
		serial.writeString("F");
	}
	
	public void stateBlueConveyorDown() {
		serial.writeString("G");
	}
	
	public void stateBlueConveyorUp() {
		serial.writeString("H");
	}
	
	public void messageEndgame() {
		serial.writeString("I");
	}
	
	public void messageAutoLeft() {
		serial.writeString("J");
	}
	
	public void messageAutoRight() {
		serial.writeString("K");
	}

}
