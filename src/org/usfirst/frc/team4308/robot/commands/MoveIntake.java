package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveIntake extends Command {
	
	private double left;
	private double right;

	public MoveIntake(double left, double right, double time) {
		this.left = left;
		this.right = right;
		
		setTimeout(time);
	}

	@Override
	protected void execute() {
		super.execute();

		Robot.intake.moveIntake(left, right);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	protected void end() {
		Robot.intake.stopIntake();
	}

	protected void interrupted() {
		Robot.intake.stopIntake();
	}


}
