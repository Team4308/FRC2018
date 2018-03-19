package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchCompressor extends Command {
	
	@Override
	protected void execute() {
		super.execute();
		
		if(Robot.c != null) {
			boolean on = Robot.c.enabled();
			
			if (on) {
				Robot.c.setClosedLoopControl(false);
			}
			else {
				Robot.c.setClosedLoopControl(true);
			}
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
