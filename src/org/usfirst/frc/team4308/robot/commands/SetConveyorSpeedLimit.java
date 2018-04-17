package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetConveyorSpeedLimit extends Command {
	
	private double limit;
	
	public SetConveyorSpeedLimit(double limit) {
		this.limit = limit;
	}
	
	@Override
	protected void execute() {
		super.execute();
		
		Robot.conveyor.speedLimit = limit;
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
