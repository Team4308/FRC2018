package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Conveyor extends Subsystem {
	
	public WPI_TalonSRX conveyorLeft;
	public WPI_TalonSRX conveyorRight;
	
	public Conveyor() {
		conveyorLeft = new WPI_TalonSRX(RobotMap.Conveyor.conveyorLeft);
		conveyorRight = new WPI_TalonSRX(RobotMap.Conveyor.conveyorRight);
		
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

}
