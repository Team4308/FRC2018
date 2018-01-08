package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AbsoluteDrive extends Command {
	
	
	public AbsoluteDrive() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
		Robot.drive.driveHandler.arcadeDrive(OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftY), OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightX));
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
