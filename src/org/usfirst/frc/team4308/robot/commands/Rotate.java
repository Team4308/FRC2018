/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4308.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.SynchronousPID;

/**
 * An example command.  You can replace me with your own command.
 */
public class Rotate extends Command {
	private SynchronousPID pid;
	private double rotation;
	
	public Rotate(double angle) {
		pid = new SynchronousPID();
		pid.setOutputRange(-0.5, 0.5);
		
	    	rotation = angle;
	    	
		requires(Robot.drive);
		setTimeout(1.5);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		double Kp = SmartDashboard.getNumber("RotateP", 0.02); 
		double Ki = SmartDashboard.getNumber("RotateI", 0.0);
		double Kd = SmartDashboard.getNumber("RotateD", 0.2);
		
		pid.setPID(Kp, Ki, Kd);
		pid.reset();
		pid.setSetpoint(rotation);
		
		Robot.navx.gyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double calculation = pid.calculate(Robot.navx.gyro.getAngle());
		
		Robot.drive.setDrive(calculation, -calculation);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return pid.onTarget(0.5) || isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drive.stopMoving();
		pid.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
