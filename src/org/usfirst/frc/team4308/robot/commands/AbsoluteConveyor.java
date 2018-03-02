package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AbsoluteConveyor extends Command {

	public AbsoluteConveyor() {
		requires(Robot.conveyor);
	}
	
	protected void execute() {
		Robot.conveyor.conveyorControl();
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
    		Robot.conveyor.stopConveyor();
    }
	
	protected void interrupted() {
		Robot.conveyor.stopConveyor();
	}

}
