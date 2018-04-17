/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4308.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4308.robot.auto.CenterAuto;
import org.usfirst.frc.team4308.robot.auto.LeftAuto;
import org.usfirst.frc.team4308.robot.auto.Move;
import org.usfirst.frc.team4308.robot.auto.RightAuto;
import org.usfirst.frc.team4308.robot.commands.ResetSensors;
import org.usfirst.frc.team4308.robot.subsystems.Arduino;
import org.usfirst.frc.team4308.robot.subsystems.Conveyor;
import org.usfirst.frc.team4308.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4308.robot.subsystems.Flags;
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
	
	public static OI oi;
	public static Drivetrain drive;
	public static USBVision usb;
	public static Gyroscope navx;
	public static PowerDistributionPanel pdp;
	public static Intake intake;
	public static Conveyor conveyor;
	public static Compressor c;
	public static Flags flags;
	public static Arduino leds;

	public static String gameData = "";
	
	public static Command auto;

	private boolean endgameAlerted = false;
	
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {

		pdp = new PowerDistributionPanel(RobotMap.PDP_ID);
		LiveWindow.disableAllTelemetry();
		
		c = new Compressor(RobotMap.PCM_ID);
		drive = new Drivetrain();
		navx = new Gyroscope();
		oi = new OI();
		intake = new Intake();
		conveyor = new Conveyor();
		flags = new Flags();
		leds = new Arduino();	
//		usb = new USBVision();
		
		auto = null;
		
		
		SmartDashboard.putString("Choose Auto", SmartDashboard.getString("Choose Auto", "B"));
		
		SmartDashboard.putNumber("RotateP", SmartDashboard.getNumber("RotateP", 0.07));  // 0.06
		SmartDashboard.putNumber("RotateI", SmartDashboard.getNumber("RotateI", 0.0));
		SmartDashboard.putNumber("RotateD", SmartDashboard.getNumber("RotateD", 0.35)); // 0.35
		
		SmartDashboard.putNumber("RotateLongP", SmartDashboard.getNumber("RotateLongP", 0.07));  // 0.06
		SmartDashboard.putNumber("RotateLongI", SmartDashboard.getNumber("RotateLongI", 0.0));
		SmartDashboard.putNumber("RotateLongD", SmartDashboard.getNumber("RotateLongD", 0.5));

		SmartDashboard.putNumber("MoveP", SmartDashboard.getNumber("MoveP", 0.03));  // 0.023
		SmartDashboard.putNumber("MoveI", SmartDashboard.getNumber("MoveI", 0.0));
		SmartDashboard.putNumber("MoveD", SmartDashboard.getNumber("MoveD", 0.3));
		
		
		SmartDashboard.putBoolean("CurrentLimiting", SmartDashboard.getBoolean("CurrentLimiting", true));
		SmartDashboard.putNumber("ContinuousCurrent", SmartDashboard.getNumber("ContinuousCurrent", 10));
		SmartDashboard.putNumber("PeakCurrent", SmartDashboard.getNumber("PeakCurrent", 15));
		SmartDashboard.putNumber("CurrentRamping", SmartDashboard.getNumber("CurrentRamping", 0));
		SmartDashboard.putNumber("PeakDuration", SmartDashboard.getNumber("PeakDuration", 100));
		
		
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
		intake.offIntake();
		drive.setDrive(0.0, 0.0);
		intake.moveIntake(0.0, 0.0);
		conveyor.moveConveyor(0.0);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		Logger.log();
	}

	@Override
	public void autonomousInit() {
		while (gameData.equals("")) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		
		leds.setState("normal");
		if (gameData.charAt(0) == 'L') {
			leds.setState("auto left");
		}
		else {
			leds.setState("auto right");
		}
		
		String key = SmartDashboard.getString("Position(L,C,R,B):", "B");
		if(key.startsWith("L")) {
			auto = new LeftAuto(key.substring(1));
		} else if (key.startsWith("R")) {
			auto = new RightAuto(key.substring(1));
		} else if (key.startsWith("C")){
			auto = new CenterAuto(key.substring(1));
		} else {
			auto = new Move(-148.0);
		}
    
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
		
		drive.setDrive(0.0, 0.0);
		intake.moveIntake(0.0, 0.0);
		conveyor.moveConveyor(0.0);
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Logger.log();
		
		if (!endgameAlerted && Timer.getMatchTime() <= 30) {
			leds.setState("endgame");
			endgameAlerted = true;
		}
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
