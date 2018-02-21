package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AbsoluteIntake extends Command {

	public AbsoluteIntake() {
		requires(Robot.intake);
	}
	
	protected void execute() {
		Robot.intake.intakeControl();
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
    		Robot.intake.stopIntake();
    }
	
	protected void interrupted() {
		Robot.intake.stopIntake();
	}
}
