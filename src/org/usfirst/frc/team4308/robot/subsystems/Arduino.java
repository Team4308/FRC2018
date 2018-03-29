package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arduino extends Subsystem {
	
	
	private SerialPort serial;
	
	public Arduino() {

		serial = new SerialPort(9600, SerialPort.Port.kUSB1);

	}
	
	public void sendState() {
		
		serial.writeString("A");
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
