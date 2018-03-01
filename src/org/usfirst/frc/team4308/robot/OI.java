/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4308.robot;

import org.usfirst.frc.team4308.robot.commands.ResetSensors;
import org.usfirst.frc.team4308.robot.auto.Move;
import org.usfirst.frc.team4308.robot.auto.Rotate;
import org.usfirst.frc.team4308.robot.commands.IntakeToggle;
import org.usfirst.frc.team4308.robot.commands.IntakeToggle.ToggleType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick driveStick = new Joystick(0);
	public static Joystick helperStick = new Joystick(1);
	
	private JoystickButton A1 = new JoystickButton(driveStick, RobotMap.Control.Standard.a);
	private JoystickButton B1 = new JoystickButton(driveStick, RobotMap.Control.Standard.b);
	private JoystickButton X1 = new JoystickButton(driveStick, RobotMap.Control.Standard.x);
	private JoystickButton Y1 = new JoystickButton(driveStick, RobotMap.Control.Standard.y);
	
	private JoystickButton LB1 = new JoystickButton(driveStick, RobotMap.Control.Standard.leftBumper);
	private JoystickButton RB1 = new JoystickButton(driveStick, RobotMap.Control.Standard.rightBumper);
	
	private JoystickButton Start1 = new JoystickButton(driveStick, RobotMap.Control.Standard.start);
	
	public OI() {
		
		A1.whenPressed(new IntakeToggle(ToggleType.SWITCH));
		B1.whenPressed(new Rotate(90.0));
		X1.whenPressed(new Rotate(-90.0));
		Y1.whenPressed(new Move(24.0));

		LB1.whenPressed(new IntakeToggle(ToggleType.CLOSE));
		RB1.whenPressed(new IntakeToggle(ToggleType.OPEN));
		
		Start1.whenPressed(new ResetSensors());
		
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
