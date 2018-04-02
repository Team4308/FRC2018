package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arduino extends Subsystem {
	
	private SerialPort serial;
	private String alliance;
	private String currentState;
	
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
		
		String updatedState = state;
		
		if (currentState.equals(state)) {
			return;
		}
		
		if (currentState.equals("conveyor down") || currentState.equals("conveyor up")) {
			if (state.equals("conveyor stop")) {
				updatedState = "normal";
			}
		}
		
		currentState = updatedState;
		
		switch(updatedState) {
			case "normal":  // set in OI.getConveyorScheme(); and SetRobotState end/interrupted
				if (alliance.equals("red")) {
					stateRed();
				}
				else {
					stateBlue();
				}
				break;
			
			case "rave":  // set in OI
				stateRave();
				break;
			
			case "conveyor down":  // set in Conveyor.moveConveyor();
				if (alliance.equals("red")) {
					stateRedConveyorDown();
				}
				else {
					stateBlueConveyorDown();
				}
				break;
			
			case "conveyor up":  // set in Conveyor.moveConveyor();
				if (alliance.equals("red")) {
					stateRedConveyorUp();
				}
				else {
					stateBlueConveyorUp();
				}
				break;
			
			case "cube drop":  // set in OI
				messageCubeDrop();
				break;
			
			case "endgame":  // set in Robot
				messageEndgame();
				break;
				
			case "auto left":  // set in Robot
				messageAutoLeft();
				break;
				
			case "auto right":  // set in Robot
				messageAutoRight();
				break;
				
			default:  // off
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
