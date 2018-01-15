package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AbsoluteDrive extends Command {
	
	int leftStickID,rightStickID;
	
	public AbsoluteDrive(int leftStickID,int rightStickID) {
		// TODO Auto-generated constructor stub
		requires(Robot.drive);
		
		this.leftStickID = leftStickID;
		this.rightStickID = rightStickID;
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		Robot.drive.driveHandler.tankDrive(-OI.driveStick.getRawAxis(leftStickID),-OI.driveStick.getRawAxis(rightStickID));
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
