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
public class Move extends Command {
	private SynchronousPID pid;
	private double movement;
	
	public Move(double displacement) {
		pid = new SynchronousPID();
		pid.setOutputRange(-1.0, 1.0);
		SmartDashboard.putNumber("P Val", 0.02);
	    	SmartDashboard.putNumber("I Val", 0.0);
	    	SmartDashboard.putNumber("D Val", 0.03);
		
	    	movement = displacement;
	    	
		requires(Robot.drive);
		setTimeout(0.5);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		double Kp = SmartDashboard.getNumber("P Val", 0.02); 
		double Ki = SmartDashboard.getNumber("I Val", 0.0);
		double Kd = SmartDashboard.getNumber("D Val", 0.03);
		
		pid.setPID(Kp, Ki, Kd);
		pid.reset();
		pid.setSetpoint(movement);
		
		Robot.drive.resetSensors();
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double calculationLeft = pid.calculate(Robot.drive.getLeftSensorPosition());
		double calculationRight = pid.calculate(-Robot.drive.getRightSensorPosition());
		
		Robot.drive.setDrive(calculationLeft, calculationRight); // TODO - not working (robot keeps moving)
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return pid.onTarget(0.4) || isTimedOut();
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
