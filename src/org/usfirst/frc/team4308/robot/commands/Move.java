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
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class Move extends Command {
	private SynchronousPID pid;
	private double movement;
	
	public Move(double displacement) {
		pid = new SynchronousPID();	
		pid.setOutputRange(-0.5, 0.5);
		SmartDashboard.putNumber("Move P Val", 0.02);
	    SmartDashboard.putNumber("Move I Val", 0.0);
	    SmartDashboard.putNumber("Move D Val", 0.2);
		
	    	movement = displacement;
	    	
		requires(Robot.drive);
		setTimeout(0.5);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		double Kp = SmartDashboard.getNumber("Move P Val", 0.0); 
		double Ki = SmartDashboard.getNumber("Move I Val", 0.0);
		double Kd = SmartDashboard.getNumber("Move D Val", 0.0);
		
		pid.setPID(Kp, Ki, Kd);
		pid.reset();
		pid.setSetpoint(movement);
		
		Robot.drive.resetSensors();
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		
		double calculation = pid.calculate((Robot.drive.getLeftSensorPosition()+Robot.drive.getRightSensorPosition())/2);
		
		
		SmartDashboard.putNumber("Left Encoder Position", Robot.drive.getLeftSensorPosition());
		SmartDashboard.putNumber("Left Encoder Velocity", Robot.drive.getRightSensorPosition());
		SmartDashboard.putNumber("Right Encoder Position", DriveTrain.frontRight.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Encoder Velocity", DriveTrain.frontRight.getSelectedSensorVelocity(0));
		
		Robot.drive.setDrive(calculation, calculation); // TODO - not working (robot keeps moving)
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (Robot.drive.getLeftSensorPosition() > movement) && (Robot.drive.getRightSensorPosition() > movement);
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
