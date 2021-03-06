/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4308.robot.auto;

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
	
	private double leftStartPos;
	private double rightStartPos;
	
	public Move(double displacement) {
		pid = new SynchronousPID();	
		pid.setOutputRange(-1.0, 1.0);
		
	    	movement = displacement;
	    	
		requires(Robot.drive);
		setTimeout(2.0);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		double Kp = SmartDashboard.getNumber("MoveP", 0.03); 
		double Ki = SmartDashboard.getNumber("MoveI", 0.0);
		double Kd = SmartDashboard.getNumber("MoveD", 0.3);
		
		pid.setPID(Kp, Ki, Kd);
		pid.reset();
		pid.setSetpoint(movement);
		
		leftStartPos = Robot.drive.getLeftSensorPosition();
		rightStartPos = Robot.drive.getRightSensorPosition();
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		// Currently average to prevent weird movement
		double leftError = Robot.drive.getLeftSensorPosition() - leftStartPos;
		double rightError = Robot.drive.getRightSensorPosition() - rightStartPos;
		
		double calculation = pid.calculate((leftError + rightError)/2);
		
		Robot.drive.setDrive(calculation, calculation);
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
