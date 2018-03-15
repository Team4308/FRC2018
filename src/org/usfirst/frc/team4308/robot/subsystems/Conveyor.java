package org.usfirst.frc.team4308.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team4308.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Conveyor extends Subsystem {

	public WPI_TalonSRX conveyorLeft;
	public WPI_TalonSRX conveyorRight;

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
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void conveyorControl() {

	}

	public void stopConveyor() {
		conveyorLeft.set(0);
		conveyorRight.set(0);
	}

	public void moveConveyor(double move) {
		conveyorLeft.set(move);
		conveyorRight.set(move);
	}

}
