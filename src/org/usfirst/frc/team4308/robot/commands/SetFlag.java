package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetFlag extends Command {
	
	private FlagType setFlag;
	private int channel;
	
	public enum FlagType {
		UP, DOWN, SWITCH, STOP
	}
	
	public SetFlag(FlagType set, int channel) {
		setFlag = set;
		this.channel = channel;
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		if (setFlag == FlagType.SWITCH) {
			Robot.flags.switchFlag(channel);
		}
		else if (setFlag == FlagType.UP) {
			Robot.flags.raiseFlag(channel);
		}
		else if (setFlag == FlagType.DOWN) {
			Robot.flags.dropFlag(channel);
		}
		else {
			Robot.flags.stopFlag(channel);
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
