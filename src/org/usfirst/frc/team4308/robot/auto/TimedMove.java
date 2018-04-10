package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TimedMove extends Command {
	
	private double speed;
	
	public TimedMove(double time, double speed) {
		
		this.speed = speed;
		
		requires(Robot.drive);
		setTimeout(time);
		
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		Robot.drive.setDrive(speed, speed);
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drive.stopMoving();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}

}
