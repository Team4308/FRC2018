package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PullConveyor extends Command {

	private boolean reversed;
	
	public PullConveyor(boolean reverse) {
		reversed = reverse;
	}
	
	@Override
	protected void execute() {
		super.execute();
		
		if (!reversed)
			Robot.conveyor.moveConveyor(1.0);
		else
			Robot.conveyor.moveConveyor(-1.0);
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
