package org.usfirst.frc.team4308.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.AbsoluteDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	public WPI_TalonSRX frontLeft;
	public WPI_TalonSRX frontRight;
	public WPI_TalonSRX rearLeft;
	public WPI_TalonSRX rearRight;
	ArrayList<WPI_TalonSRX> driveMotors = new ArrayList<WPI_TalonSRX>();
	public SpeedControllerGroup leftDrive,rightDrive;
	
	public static double ENCODER_TICKS_TO_INCHES;
	
	
	public DriveTrain() {
		
		ENCODER_TICKS_TO_INCHES = Math.PI * 6/4080;
		
		frontLeft = new WPI_TalonSRX(RobotMap.Drive.leftFront);
		driveMotors.add(frontLeft);
		frontRight = new WPI_TalonSRX(RobotMap.Drive.rightFront);
		driveMotors.add(frontRight);
		rearLeft = new WPI_TalonSRX(RobotMap.Drive.leftBack);
		driveMotors.add(rearLeft);
		rearRight = new WPI_TalonSRX(RobotMap.Drive.rightBack);
		driveMotors.add(rearRight);
		
		for(WPI_TalonSRX talon : driveMotors) {
			talon.configOpenloopRamp(0, 0);
			talon.configContinuousCurrentLimit(35, 0); // 10
			talon.configPeakCurrentLimit(35, 0);  // 15
			talon.configPeakCurrentDuration(100, 0);
			talon.enableCurrentLimit(true);
		}
		
		frontLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		frontLeft.setSensorPhase(false);
		frontRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		frontRight.setSensorPhase(false);
		
		leftDrive = new SpeedControllerGroup(frontLeft,rearLeft);
		rightDrive = new SpeedControllerGroup(frontRight,rearRight);
		rightDrive.setInverted(true);
	}
	
	public void setCurrentLimit(boolean limit, double ramp, int continuous, int peak, int peakDuration) { 
		for(WPI_TalonSRX talon : driveMotors) {
			talon.configOpenloopRamp(ramp, 0);
			talon.configContinuousCurrentLimit(continuous, 0); // 10
			talon.configPeakCurrentLimit(peak, 0);  // 15
			talon.configPeakCurrentDuration(peakDuration, 0);
			talon.enableCurrentLimit(limit);
		}
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
		
		Robot.pdp.clearStickyFaults();
		
		setDrive(OI.getDriveSchemeLeft(), OI.getDriveSchemeRight());
		
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
