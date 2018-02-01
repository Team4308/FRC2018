package org.usfirst.frc.team4308.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.AbsoluteDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
	public static WPI_TalonSRX frontLeft,frontRight,rearLeft,rearRight;
	ArrayList<WPI_TalonSRX> driveMotors = new ArrayList<WPI_TalonSRX>();
	SpeedControllerGroup leftDrive,rightDrive;
	public DifferentialDrive driveHandler;
	
	public DriveTrain() {
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
		double leftY = OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightX);
		double rightY = OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		SmartDashboard.putNumber("Left X", leftX);
		SmartDashboard.putNumber("Left Y", leftY);
		SmartDashboard.putNumber("Right X", rightX);
		SmartDashboard.putNumber("Right Y", rightY);
		
		SmartDashboard.putNumber("Left Encoder Position", getLeftSensorPosition());
		SmartDashboard.putNumber("Left Encoder Velocity", getRightSensorPosition());
		SmartDashboard.putNumber("Right Encoder Position", frontRight.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Encoder Velocity", frontRight.getSelectedSensorVelocity(0));
		
		// Tank Drive
		setDrive(-leftY,-rightY); 
		
		// Arcade Drive - Left Stick
		//setDrive(-leftY + leftX, -leftY - leftX);
		
		// Arcade Drive - Left: Drive, Right: Steer
//		setDrive(leftY + rightX, leftY - rightX);
		
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
		return (-1*frontLeft.getSelectedSensorPosition(0));
	}
	public double getRightSensorPosition() {
		return frontRight.getSelectedSensorPosition(0);
	}
	

}
