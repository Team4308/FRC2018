package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arduino extends Subsystem {
	
	private SerialPort serial;
	
	public Arduino() {
		serial = new SerialPort(9600, SerialPort.Port.kUSB1);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

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
