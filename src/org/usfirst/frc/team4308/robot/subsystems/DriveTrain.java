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
		
		leftDrive = new SpeedControllerGroup(frontLeft,rearLeft);
		rightDrive = new SpeedControllerGroup(frontRight,rearRight);
		rightDrive.setInverted(true);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AbsoluteDrive());
	}
	
	public void driveControl() {
//		Robot.pdp.clearStickyFaults();
		tankDrive();
	}
	
	public void tankDrive() {
		leftDrive.set(OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftY));
		rightDrive.set(OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightY));
	}
	
	public void arcadeDrive() {
//		leftDrive.set(RobotMap.Control.Standard.leftX);
//		rightDrive.set(RobotMap.Control.Standard.rightX);
	}
	
	public void stopMoving() {
		leftDrive.set(0.0);
		rightDrive.set(0.0);
	}
	

}
