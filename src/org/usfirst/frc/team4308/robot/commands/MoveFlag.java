package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveFlag extends Command {
	
	private int channel;
	private boolean set;
	private boolean useTimeout = true;
	
	public MoveFlag(int channel, boolean up, double timeout) {
		this.set = up;
		this.channel = channel;
		
		if (timeout < 0) {
			useTimeout = false;
		}
		else {
			useTimeout = true;
			setTimeout(timeout);
		}
		
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		if (set) {
			Robot.flags.raiseFlag(channel);
		}
		else {
			Robot.flags.dropFlag(channel);
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return useTimeout && isTimedOut();
	}
	

	protected void end() {
    		Robot.flags.stopFlags();
    }
	
	protected void interrupted() {
		Robot.flags.stopFlags();
	}

}
