package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.subsystems.Intake.IntakeState;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeToggle extends Command {
	
	ToggleType setToggle;
	
	public enum ToggleType {
		OPEN, CLOSE, SWITCH, OFF
	}
	
	public IntakeToggle(ToggleType set) {
		setToggle = set;
	}
	
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		if (setToggle == ToggleType.SWITCH) {
			if (Robot.intake.state == IntakeState.CLOSE) {
				Robot.intake.openIntake();
			} else {
				Robot.intake.closeIntake();
			}
		}
		else if (setToggle == ToggleType.OPEN) {
			Robot.intake.openIntake();
		}
		else if (setToggle == ToggleType.CLOSE) {
			Robot.intake.closeIntake();
		}
		else {
			Robot.intake.offIntake();
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
