package org.usfirst.frc.team4308.robot.subsystems;

import java.awt.List;
import java.util.ArrayList;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {
	WPI_TalonSRX frontLeft,frontRight,rearLeft,rearRight;
	ArrayList<WPI_TalonSRX> driveMotors = new ArrayList<WPI_TalonSRX>();
	SpeedControllerGroup leftDrive,rightDrive;
	public static DifferentialDrive driveHandler;
	
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
		
		for(WPI_TalonSRX talon : driveMotors) {
			talon.configOpenloopRamp(2, 0);
			talon.configContinuousCurrentLimit(10, 0);
			talon.configPeakCurrentLimit(15, 0);
			talon.configPeakCurrentDuration(100, 0);
			talon.enableCurrentLimit(true);
		}
		
		leftDrive = new SpeedControllerGroup(frontLeft,rearLeft);
		rightDrive = new SpeedControllerGroup(frontRight,rearRight);
		driveHandler = new DifferentialDrive(leftDrive, rightDrive);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
