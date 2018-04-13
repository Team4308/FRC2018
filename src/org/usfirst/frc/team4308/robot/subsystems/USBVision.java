package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class USBVision extends Subsystem {

	
	
	public USBVision() {
		super();
		// TODO Auto-generated constructor stub
		CameraServer.getInstance().startAutomaticCapture();
	}

	public USBVision(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
