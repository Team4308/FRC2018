package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AbsoluteDrive extends Command {
	
	public AbsoluteDrive() {
		requires(Robot.drive);
	}
	
	protected void execute() {
		Robot.drive.driveControl();
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
    		Robot.drive.stopMoving();
    }
	
	protected void interrupted() {
		Robot.drive.stopMoving();
	}

}
