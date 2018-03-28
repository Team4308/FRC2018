package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AbsoluteFlags extends Command {

	public AbsoluteFlags() {
		requires(Robot.flags);
	}
	
	protected void execute() {
		
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
    		Robot.flags.stopFlags();
    }
	
	protected void interrupted() {
		Robot.flags.stopFlags();
	}
}
