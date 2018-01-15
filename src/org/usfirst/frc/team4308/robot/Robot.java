/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4308.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4308.robot.auto.BlindAuto;
import org.usfirst.frc.team4308.robot.commands.AbsoluteDrive;
import org.usfirst.frc.team4308.robot.commands.ExampleCommand;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4308.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4308.robot.subsystems.Gyroscope;
import org.usfirst.frc.team4308.robot.subsystems.USBVision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static DriveTrain drive;
	public static USBVision usb;
	public static Gyroscope navx;
	public static PowerDistributionPanel pdp;

	public static String gameData;

	Command m_autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {

		drive = new DriveTrain();
		usb = new USBVision();		
		navx = new Gyroscope();
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		oi = new OI();
		autoChooser.addDefault("Default Auto", new ExampleCommand());
		autoChooser.addObject("Blind Auto", new BlindAuto());
		SmartDashboard.putData("Auto mode", autoChooser);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}

		if (gameData.charAt(0) == 'L') {
		} else {

		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}

		
		new AbsoluteDrive(RobotMap.Control.Standard.leftY, RobotMap.Control.Standard.rightY).start();
		
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Y", OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftY));
		SmartDashboard.putNumber("Right Y", OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightY));

		SmartDashboard.putNumber("FrontLeftMotor Current", DriveTrain.frontLeft.getOutputCurrent());
		SmartDashboard.putNumber("FrontRightMotor Current", DriveTrain.frontRight.getOutputCurrent());
		SmartDashboard.putNumber("RearLeftMotor Current", DriveTrain.rearLeft.getOutputCurrent());
		SmartDashboard.putNumber("RearRightMotor Current", DriveTrain.rearRight.getOutputCurrent());
		
		//SmartDashboard.putNumber("Total Current", pdp.getTotalCurrent());
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
