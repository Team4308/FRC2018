/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.robot.commands.ResetSensors;
import org.usfirst.frc.team4308.robot.commands.SwitchCompressor;
import org.usfirst.frc.team4308.robot.auto.Move;
import org.usfirst.frc.team4308.robot.auto.Rotate;
import org.usfirst.frc.team4308.robot.commands.IntakeToggle;
import org.usfirst.frc.team4308.robot.commands.IntakeToggle.ToggleType;
import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick driveStick = new Joystick(0);
	public static Joystick controlStick = new Joystick(1);
	
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
	
	public OI() {
		
		// Single controller controls
		A1.whenPressed(new SwitchCompressor());
//		B1.whileHeld(new PullConveyor(true));
//		X1.whileHeld(new PullConveyor(false));
		
		X1.whenPressed(new Rotate(90));
		Y1.whenPressed(new Move(60));
		
//
//		LB1.whenPressed(new IntakeToggle(ToggleType.CLOSE));
//		RB1.whenPressed(new IntakeToggle(ToggleType.OPEN));
		
//		A2.whenPressed(new IntakeToggle(ToggleType.SWITCH));
		
		// Dual controller controls
		B2.whenPressed(new IntakeToggle(ToggleType.OFF));
		
		LB2.whenPressed(new IntakeToggle(ToggleType.CLOSE));
		RB2.whenPressed(new IntakeToggle(ToggleType.OPEN));
		
		
		Start1.whenPressed(new ResetSensors());
		Start2.whenPressed(new ResetSensors());
		
	}
	
	// Tank Drive
//	(leftY, rightY); 
	
	// Arcade Drive - Left Stick
//	(leftY + leftX, leftY - leftX);
	
	// Arcade Drive - Left: Drive, Right: Steer
//	(leftY + rightX, leftY - rightX);
	
	public static double getDriveSchemeLeft() {
		
//		double leftX = driveStick.getRawAxis(RobotMap.Control.Standard.leftX);
		double leftY = -driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = driveStick.getRawAxis(RobotMap.Control.Standard.rightX) * 0.5;
//		double rightY = -driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		return (leftY + rightX);
		
	}
	
	public static double getDriveSchemeRight() {
		
//		double leftX = driveStick.getRawAxis(RobotMap.Control.Standard.leftX);
		double leftY = -driveStick.getRawAxis(RobotMap.Control.Standard.leftY);
		double rightX = driveStick.getRawAxis(RobotMap.Control.Standard.rightX) * 0.5;
//		double rightY = -driveStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		return (leftY - rightX);
		
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
//		double rightX = controlStick.getRawAxis(RobotMap.Control.Standard.rightX);
		double rightY = -controlStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		double leftTrigger = controlStick.getRawAxis(RobotMap.Control.Standard.leftTrigger);
		
		return rightY - leftTrigger;
		
	}
	
	public static double getIntakeSchemeRight() {

//		double leftX = controlStick.getRawAxis(RobotMap.Control.Standard.leftX);
//		double leftY = -controlStick.getRawAxis(RobotMap.Control.Standard.leftY);
//		double rightX = controlStick.getRawAxis(RobotMap.Control.Standard.rightX);
		double rightY = -controlStick.getRawAxis(RobotMap.Control.Standard.rightY);
		
		double rightTrigger = controlStick.getRawAxis(RobotMap.Control.Standard.rightTrigger);
		
		return rightY - rightTrigger;
		
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
