package org.usfirst.frc.team4308.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.AbsoluteDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {
	public WPI_TalonSRX frontLeft;
	public WPI_TalonSRX frontRight;
	public WPI_TalonSRX rearLeft;
	public WPI_TalonSRX rearRight;
	ArrayList<WPI_TalonSRX> driveMotors = new ArrayList<WPI_TalonSRX>();
	public SpeedControllerGroup leftDrive,rightDrive;
	public DifferentialDrive driveHandler;
	
	public static double ENCODER_TICKS_TO_INCHES;
	
	
	public DriveTrain() {
		
		ENCODER_TICKS_TO_INCHES = Math.PI * 6/4080;
		
		// TODO Auto-generated constructor stub
		frontLeft = new WPI_TalonSRX(RobotMap.Drive.leftFront);
		driveMotors.add(frontLeft);
		frontRight = new WPI_TalonSRX(RobotMap.Drive.rightFront);
		driveMotors.add(frontRight);
		rearLeft = new WPI_TalonSRX(RobotMap.Drive.leftBack);
		driveMotors.add(rearLeft);
		rearRight = new WPI_TalonSRX(RobotMap.Drive.rightBack);
		driveMotors.add(rearRight);
		
		/*for(WPI_TalonSRX talon : driveMotors) {
			talon.configOpenloopRamp(.5, 0);
			talon.configContinuousCurrentLimit(10, 0);
			talon.configPeakCurrentLimit(15, 0);
			talon.configPeakCurrentDuration(100, 0);
			talon.enableCurrentLimit(true);
		}*/
		
		frontLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		frontLeft.setSensorPhase(false);
		frontRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		frontRight.setSensorPhase(false);
		
		leftDrive = new SpeedControllerGroup(frontLeft,rearLeft);
		rightDrive = new SpeedControllerGroup(frontRight,rearRight);
		rightDrive.setInverted(true);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AbsoluteDrive());
	}
	
	public void setDrive(double left, double right) {
		leftDrive.set(left);
		rightDrive.set(right);
	}
	
	public void driveControl() {
//		Robot.pdp.clearStickyFaults();
		double leftX = OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftX);
		double leftY = -OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightX);
		double rightY = -OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		// Tank Drive
//		setDrive(leftY, rightY); 
		
		// Arcade Drive - Left Stick
//		setDrive(leftY + leftX, leftY - leftX);
		
		// Arcade Drive - Left: Drive, Right: Steer
		setDrive(leftY + rightX, leftY - rightX);
		
	}
	
	public void stopMoving() {
		leftDrive.set(0.0);
		rightDrive.set(0.0);
	}
	
	public void resetSensors() {
		frontLeft.setSelectedSensorPosition(0, 0, 0);
		frontRight.setSelectedSensorPosition(0, 0, 0);
	}
	
	public double getLeftSensorPosition() {
		return -frontLeft.getSelectedSensorPosition(0) * ENCODER_TICKS_TO_INCHES;
	}
	public double getRightSensorPosition() {
		return frontRight.getSelectedSensorPosition(0) * ENCODER_TICKS_TO_INCHES;
	}
	

}
