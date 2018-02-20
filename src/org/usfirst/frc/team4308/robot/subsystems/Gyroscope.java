package org.usfirst.frc.team4308.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gyroscope extends Subsystem {

	public AHRS gyro;
	
	public Gyroscope() {
		gyro = new AHRS(SPI.Port.kMXP);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
