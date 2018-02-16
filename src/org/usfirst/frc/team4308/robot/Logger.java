package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Logger {
	
	private class Stick {
		public double x;
		public double y;
	}
	
	static Stick left;
	static Stick right;
	
	public static void log() {
		
		left.x = OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftX);
		left.y = -OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		right.x = OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightX);
		right.y = -OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		
		SmartDashboard.putNumber("leftX", left.x);
		SmartDashboard.putNumber("Left Y", left.y);
		SmartDashboard.putNumber("Right X", right.x);
		SmartDashboard.putNumber("Right Y", right.y);
		
		SmartDashboard.putNumber("Left Encoder Position", Robot.drive.getLeftSensorPosition());
		SmartDashboard.putNumber("Left Encoder Velocity", Robot.drive.frontLeft.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right Encoder Position", Robot.drive.getRightSensorPosition());
		SmartDashboard.putNumber("Right Encoder Velocity", Robot.drive.frontRight.getSelectedSensorVelocity(0));
		
		SmartDashboard.putNumber("Gyro Angle", Robot.navx.gyro.getAngle());
		SmartDashboard.putNumber("Gyro Displacement X", Robot.navx.gyro.getDisplacementX());
		SmartDashboard.putNumber("Gyro Displacement Y", Robot.navx.gyro.getDisplacementY());
		SmartDashboard.putNumber("Gyro Displacement Z", Robot.navx.gyro.getDisplacementZ());

		SmartDashboard.putNumber("FrontLeftMotor Current", DriveTrain.frontLeft.getOutputCurrent());
		SmartDashboard.putNumber("FrontRightMotor Current", DriveTrain.frontRight.getOutputCurrent());
		SmartDashboard.putNumber("RearLeftMotor Current", DriveTrain.rearLeft.getOutputCurrent());
		SmartDashboard.putNumber("RearRightMotor Current", DriveTrain.rearRight.getOutputCurrent());
		
		SmartDashboard.putNumber("Total Current", Robot.pdp.getTotalCurrent());
		
	}
	
}
