/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4308.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4308.robot.Robot;

public class ResetSensors extends Command {
	public ResetSensors() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		requires(Robot.navx);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drive.resetSensors();
		Robot.navx.gyro.reset();
		Robot.timer.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drive.resetSensors();
		Robot.navx.gyro.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
