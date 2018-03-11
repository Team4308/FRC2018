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
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4308.robot.auto.CenterAuto;
import org.usfirst.frc.team4308.robot.auto.LeftAuto;
import org.usfirst.frc.team4308.robot.auto.PathCenterLeft;
import org.usfirst.frc.team4308.robot.auto.PathCenterRight;
import org.usfirst.frc.team4308.robot.auto.PathForward;
import org.usfirst.frc.team4308.robot.auto.PathLeftLeft;
import org.usfirst.frc.team4308.robot.auto.PathLeftRight;
import org.usfirst.frc.team4308.robot.auto.PathRightLeft;
import org.usfirst.frc.team4308.robot.auto.PathRightRight;
import org.usfirst.frc.team4308.robot.auto.RightAuto;
import org.usfirst.frc.team4308.robot.auto.TestPath;
import org.usfirst.frc.team4308.robot.commands.ResetSensors;
import org.usfirst.frc.team4308.robot.subsystems.Conveyor;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4308.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4308.robot.subsystems.Gyroscope;
import org.usfirst.frc.team4308.robot.subsystems.Intake;
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
	public static Intake intake;
	public static Conveyor conveyor;

	public static String gameData;
	
	public static SendableChooser<CommandGroup> autoChooser;
	public static Command auto;
	
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialisation code.
	 */
	@Override
	public void robotInit() {

		pdp = new PowerDistributionPanel(RobotMap.PDP_ID);
		LiveWindow.disableAllTelemetry();
		
		drive = new DriveTrain();
		usb = new USBVision();		
		navx = new Gyroscope();
		oi = new OI();
		intake = new Intake();
		conveyor = new Conveyor();
		

		autoChooser.addDefault("Left", new LeftAuto());
		autoChooser.addObject("Right", new RightAuto());
		autoChooser.addObject("Center", new CenterAuto());
		SmartDashboard.putData("Where are you?", autoChooser);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Command reset = new ResetSensors();
		reset.start();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		Logger.log();
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

		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		auto = autoChooser.getSelected();
		if (auto != null) {
			auto.start();
		}
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		Logger.log();
	}

	@Override
	public void teleopInit() {
		// Stops auto when teleop starts
		if (auto != null) {
			auto.cancel();
		}
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Logger.log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
