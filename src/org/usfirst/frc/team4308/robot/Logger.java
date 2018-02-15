package org.usfirst.frc.team4308.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Logger {

	
	
	public void log() {
		
		double leftX = OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftX);
		double leftY = -OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightX);
		double rightY = -OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		SmartDashboard.putNumber("Left X", leftX);
		SmartDashboard.putNumber("Left Y", leftY);
		SmartDashboard.putNumber("Right X", rightX);
		SmartDashboard.putNumber("Right Y", rightY);
		
		SmartDashboard.putNumber("Left Encoder Position", Robot.drive.getLeftSensorPosition());
		SmartDashboard.putNumber("Left Encoder Velocity", Robot.drive.frontLeft.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right Encoder Position", Robot.drive.getRightSensorPosition());
		SmartDashboard.putNumber("Right Encoder Velocity", Robot.drive.frontRight.getSelectedSensorVelocity(0));
		
	}
	
}
