package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveConveyor extends Command {

	private double speed;

	public MoveConveyor(double speed) {
		this.speed = speed;
	}

	@Override
	protected void execute() {
		super.execute();
		
		Robot.conveyor.moveConveyor(speed); // 1 is up, -1 is down
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		Robot.conveyor.stopConveyor();
	}

	protected void interrupted() {
		Robot.conveyor.stopConveyor();
	}

}
