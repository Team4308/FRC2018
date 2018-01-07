package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {
	WPI_TalonSRX frontLeft,frontRight,rearLeft,rearRight;
	SpeedControllerGroup leftDrive,rightDrive;
	public static DifferentialDrive drive;
	
	public DriveTrain() {
		// TODO Auto-generated constructor stub
		frontLeft = new WPI_TalonSRX(RobotMap.Drive.leftFront);
		frontRight = new WPI_TalonSRX(RobotMap.Drive.rightFront);
		rearLeft = new WPI_TalonSRX(RobotMap.Drive.leftBack);
		rearRight = new WPI_TalonSRX(RobotMap.Drive.rightBack);
		leftDrive = new SpeedControllerGroup(frontLeft,rearLeft);
		rightDrive = new SpeedControllerGroup(frontRight,rearRight);
		drive = new DifferentialDrive(leftDrive, rightDrive);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	

}
