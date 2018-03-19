package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.robot.subsystems.Intake.IntakeState;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Logger {	
	
	public static void log() {
		
		double leftX = OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftX);
		double leftY = -OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightX);
		double rightY = -OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		SmartDashboard.putNumber("Time",Robot.timer.get());
		
		//SmartDashboard.putNumber(key, );
		
		SmartDashboard.putNumber("LeftX", leftX);
		SmartDashboard.putNumber("LeftY", leftY);
		SmartDashboard.putNumber("RightX", rightX);
		SmartDashboard.putNumber("RightY", rightY);
		
		SmartDashboard.putNumber("LeftEncoderPosition", Robot.drive.getLeftSensorPosition());
		SmartDashboard.putNumber("LeftEncoderVelocity", Robot.drive.frontLeft.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("RightEncoderPosition", Robot.drive.getRightSensorPosition());
		SmartDashboard.putNumber("RightEncoderVelocity", Robot.drive.frontRight.getSelectedSensorVelocity(0));
		
		SmartDashboard.putNumber("GyroAngle", Robot.navx.gyro.getAngle());
		SmartDashboard.putNumber("GyroDisplacement X", Robot.navx.gyro.getDisplacementX());
		SmartDashboard.putNumber("GyroDisplacement Y", Robot.navx.gyro.getDisplacementY());
		SmartDashboard.putNumber("GyroDisplacement Z", Robot.navx.gyro.getDisplacementZ());

		SmartDashboard.putNumber("FrontLeftMotorCurrent", Robot.drive.frontLeft.getOutputCurrent());
		SmartDashboard.putNumber("FrontRightMotorCurrent", Robot.drive.frontRight.getOutputCurrent());
		SmartDashboard.putNumber("RearLeftMotorCurrent", Robot.drive.rearLeft.getOutputCurrent());
		SmartDashboard.putNumber("RearRightMotorCurrent", Robot.drive.rearRight.getOutputCurrent());
		
		SmartDashboard.putNumber("LeftConveyorCurrent", Robot.conveyor.conveyorLeft.getOutputCurrent());
		SmartDashboard.putNumber("RightConveyorCurrent", Robot.conveyor.conveyorRight.getOutputCurrent());
		
		SmartDashboard.putNumber("LeftIntakeCurrent", Robot.intake.intakeLeft.getOutputCurrent());
		SmartDashboard.putNumber("RightIntakeCurrent", Robot.intake.intakeRight.getOutputCurrent());
		
		SmartDashboard.putNumber("TotalCurrent", Robot.pdp.getTotalCurrent());
		
		SmartDashboard.putNumber("Intake State", Robot.intake.state == IntakeState.OPEN ? 1 : 0);
		
		SmartDashboard.getString("Position (B, L,R,C): ", "");
		
	}
	
}
