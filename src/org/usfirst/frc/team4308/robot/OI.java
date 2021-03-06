/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.robot.commands.ResetSensors;
import org.usfirst.frc.team4308.robot.commands.SetConveyorSpeedLimit;
import org.usfirst.frc.team4308.robot.commands.SetCurrentLimiting;
import org.usfirst.frc.team4308.robot.commands.MoveFlag;
import org.usfirst.frc.team4308.robot.commands.SetRobotState;
import org.usfirst.frc.team4308.robot.auto.CenterAuto;
import org.usfirst.frc.team4308.robot.auto.Move;
import org.usfirst.frc.team4308.robot.auto.Rotate;
import org.usfirst.frc.team4308.robot.auto.RotateLong;
import org.usfirst.frc.team4308.robot.commands.ToggleIntake;
import org.usfirst.frc.team4308.robot.commands.ToggleIntake.ToggleType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick driveStick = new Joystick(0);
	public static Joystick controlStick = new Joystick(1);
	public static Joystick testingStick = new Joystick(2);
	
	private JoystickButton A1 = new JoystickButton(driveStick, RobotMap.Control.Standard.a);
	private JoystickButton B1 = new JoystickButton(driveStick, RobotMap.Control.Standard.b);
	private JoystickButton X1 = new JoystickButton(driveStick, RobotMap.Control.Standard.x);
	private JoystickButton Y1 = new JoystickButton(driveStick, RobotMap.Control.Standard.y);
	
	private JoystickButton LB1 = new JoystickButton(driveStick, RobotMap.Control.Standard.leftBumper);
	private JoystickButton RB1 = new JoystickButton(driveStick, RobotMap.Control.Standard.rightBumper);

	private JoystickButton Start1 = new JoystickButton(driveStick, RobotMap.Control.Standard.start);
	
	private JoystickButton A2 = new JoystickButton(controlStick, RobotMap.Control.Standard.a);
	private JoystickButton B2 = new JoystickButton(controlStick, RobotMap.Control.Standard.b);
	private JoystickButton X2 = new JoystickButton(controlStick, RobotMap.Control.Standard.x);
	private JoystickButton Y2 = new JoystickButton(controlStick, RobotMap.Control.Standard.y);
	
	private JoystickButton LB2 = new JoystickButton(controlStick, RobotMap.Control.Standard.leftBumper);
	private JoystickButton RB2 = new JoystickButton(controlStick, RobotMap.Control.Standard.rightBumper);

	private JoystickButton Start2 = new JoystickButton(controlStick, RobotMap.Control.Standard.start);
	
	private JoystickButton A3 = new JoystickButton(testingStick, RobotMap.Control.Standard.a);
	private JoystickButton B3 = new JoystickButton(testingStick, RobotMap.Control.Standard.b);
	private JoystickButton X3 = new JoystickButton(testingStick, RobotMap.Control.Standard.x);
	private JoystickButton Y3 = new JoystickButton(testingStick, RobotMap.Control.Standard.y);
	
	private JoystickButton LB3 = new JoystickButton(testingStick, RobotMap.Control.Standard.leftBumper);
	private JoystickButton RB3 = new JoystickButton(testingStick, RobotMap.Control.Standard.rightBumper);

	private JoystickButton Start3 = new JoystickButton(testingStick, RobotMap.Control.Standard.start);
	
	public OI() {
		
		LB1.whenPressed(new SetCurrentLimiting());
		RB1.whenPressed(new SetCurrentLimiting());

		B1.whileHeld(new MoveFlag(0, false, -1));
		X1.whileHeld(new MoveFlag(0, true, -1));
		A1.whenPressed(new SetRobotState("cube drop"));
		Y1.whenPressed(new SetRobotState("rave"));
		
		LB2.whenPressed(new ToggleIntake(ToggleType.CLOSE));
		RB2.whenPressed(new ToggleIntake(ToggleType.OPEN));
		
		A2.whenPressed(new SetRobotState("cube drop"));
		Y2.whenPressed(new SetRobotState("rave"));
		
		B2.whenPressed(new SetConveyorSpeedLimit(1.0)); // max speed
		X2.whenPressed(new SetConveyorSpeedLimit(0.5));
		
		Start1.whenPressed(new ResetSensors());
		Start2.whenPressed(new ResetSensors());
		Start3.whenPressed(new ResetSensors());
		
		LB3.whenPressed(new Rotate(-45.0)); // Left
		RB3.whenPressed(new Rotate(45.0)); // Right
		
		A3.whenPressed(new Move(-60.0));
		Y3.whenPressed(new Move(60.0));
		X3.whenPressed(new RotateLong(-135.0));
		B3.whenPressed(new RotateLong(135.0));
		
		
	}
	
	// STICK CONTROLS
	// X -> RIGHT is POSITIVE, LEFT is NEGATIVE
	// Y -> UP is POSITIVE, DOWN is NEGATIVE (this includes the negative sign)
	
	// Tank Drive
//	(leftY, rightY); 
	
	// Arcade Drive - Left Stick
//	(leftY + leftX, leftY - leftX);
	
	// Arcade Drive - Left: Drive, Right: Steer
//	(leftY + rightX, leftY - rightX);
	
	public static double getDriveSchemeLeft() {
		
//		double leftX = driveStick.getRawAxis(RobotMap.Control.Standard.leftX);
		double leftY = -driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = driveStick.getRawAxis(RobotMap.Control.Standard.rightX);
//		double rightY = -driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		if (rightX <= 0) {
			rightX = -(rightX*rightX);
		} else {
			rightX = (rightX*rightX);
		}
		
		
		return normalized(leftY + rightX);
		
	}
	
	public static double getDriveSchemeRight() {
		
//		double leftX = driveStick.getRawAxis(RobotMap.Control.Standard.leftX);
		double leftY = -driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = driveStick.getRawAxis(RobotMap.Control.Standard.rightX);
//		double rightY = -driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		if (rightX <= 0) {
			rightX = -(rightX*rightX);
		} else {
			rightX = (rightX*rightX);
		}
		
		return normalized(leftY - rightX);
		
	}
	
	public static double getConveyorScheme() {

//		double leftX = controlStick.getRawAxis(RobotMap.Control.Standard.leftX);
		double leftY = -controlStick.getRawAxis(RobotMap.Control.Standard.leftY);
//		double rightX = controlStick.getRawAxis(RobotMap.Control.Standard.rightX);
//		double rightY = -controlStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		return -leftY;
		
	}
	
	public static double getIntakeSchemeLeft() {

//		double leftX = controlStick.getRawAxis(RobotMap.Control.Standard.leftX);
//		double leftY = -controlStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = controlStick.getRawAxis(RobotMap.Control.Standard.rightX);
		double rightY = -controlStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		double leftTrigger = controlStick.getRawAxis(RobotMap.Control.Standard.leftTrigger);
		
		return normalized(rightY + rightX - leftTrigger);
		
	}
	
	public static double getIntakeSchemeRight() {

//		double leftX = controlStick.getRawAxis(RobotMap.Control.Standard.leftX);
//		double leftY = -controlStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = controlStick.getRawAxis(RobotMap.Control.Standard.rightX);
		double rightY = -controlStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		double rightTrigger = controlStick.getRawAxis(RobotMap.Control.Standard.rightTrigger);
		
		return normalized(rightY - rightX - rightTrigger);
		
	}
	
	private static double normalized(double val) {
		if (val > 1.0) {
			return 1.0;
		}
		else if (val < -1.0) {
			return -1.0;
		}
		else {
			return val;
		}
	}
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
