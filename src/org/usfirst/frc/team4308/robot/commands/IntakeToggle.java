package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.util.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeToggle extends Command {
	
	private IntakeState current;
	private IntakeState request;
	
	public IntakeToggle(IntakeState request) {
		// TODO Auto-generated constructor stub
		this.request = request;
		current = Robot.intake.intakeState;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		if (request == current) {
			Robot.intake.offTake();
			Robot.intake.intakeState = IntakeState.OFF;
		} else {
			if(current == IntakeState.OFF) {
				if (request == IntakeState.INTAKE) {
					Robot.intake.inTake();
					Robot.intake.intakeState = IntakeState.INTAKE;
				} else {
					Robot.intake.outTake();
					Robot.intake.intakeState = IntakeState.OUTTAKE;
				}
			} else {
				Robot.intake.offTake();
				Robot.intake.intakeState = IntakeState.OFF;
			}
			
		}
		
	}

}
