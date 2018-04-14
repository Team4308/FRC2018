package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetCurrentLimiting extends Command {
	
	@Override
	protected void execute() {
		super.execute();
		
		boolean limit = SmartDashboard.getBoolean("CurrentLimiting", true);
		int continuous = (int) SmartDashboard.getNumber("ContinuousCurrent", 35);
		int peak = (int) SmartDashboard.getNumber("PeakCurrent", 35);
		double ramp = SmartDashboard.getNumber("CurrentRamping", 0.5);
		int peakDuration = (int) SmartDashboard.getNumber("PeakDuration", 100);
		
		Robot.drive.setCurrentLimit(limit, ramp, continuous, peak, peakDuration);
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
