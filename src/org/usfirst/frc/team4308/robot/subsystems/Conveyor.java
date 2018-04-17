package org.usfirst.frc.team4308.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.AbsoluteConveyor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Conveyor extends Subsystem {

	public WPI_TalonSRX conveyorLeft;
	public WPI_TalonSRX conveyorRight;
	
	public double speedLimit = 1.0;
	
	public Conveyor() {
		conveyorLeft = new WPI_TalonSRX(RobotMap.Conveyor.conveyorLeft);
		conveyorRight = new WPI_TalonSRX(RobotMap.Conveyor.conveyorRight);
		ArrayList<WPI_TalonSRX> driveMotors = new ArrayList<WPI_TalonSRX>();
		driveMotors.add(conveyorRight);
		driveMotors.add(conveyorLeft);

		for (WPI_TalonSRX talon : driveMotors) {
			talon.configOpenloopRamp(0, 0);
			talon.enableCurrentLimit(false);
		}

		conveyorRight.setInverted(true);
		
		speedLimit = 1.0;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AbsoluteConveyor());
	}

	public void conveyorControl() {
		
		Robot.pdp.clearStickyFaults();
		
		moveConveyor(OI.getConveyorScheme());

	}

	public void stopConveyor() {
		conveyorLeft.set(0);
		conveyorRight.set(0);
	}

	public void moveConveyor(double move) {
		conveyorLeft.set(move * speedLimit);
		conveyorRight.set(move * speedLimit);
		

		if (move > 0.2) {
			Robot.leds.setState("conveyor up");
		}
		else if (move < -0.2) {
			Robot.leds.setState("conveyor down");
		}
		else {
			Robot.leds.setState("conveyor stop");
		}
	}

}
