package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateCube extends Command {
	
	public RotateCube() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		Robot.intake.intakeLeft.set(-1);
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
