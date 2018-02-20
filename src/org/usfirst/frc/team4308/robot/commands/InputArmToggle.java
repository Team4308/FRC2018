package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.subsystems.Intake;
import org.usfirst.frc.team4308.robot.util.PincerState;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class InputArmToggle extends Command {
	
	PincerState state;
	
	public InputArmToggle() {
		// TODO Auto-generated constructor stub
		state = PincerState.CLOSE;
	}
	
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		if (state == PincerState.CLOSE) {
			Intake.intake.set(DoubleSolenoid.Value.kReverse);
			state = PincerState.OPEN;
		} else {
			Intake.intake.set(DoubleSolenoid.Value.kForward);
			state = PincerState.CLOSE;
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
