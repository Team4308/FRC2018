package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Claw extends PIDSubsystem {
	
	public Claw() {
		super("Claw",2.0, 0.0, 0.0);
		setAbsoluteTolerance(0.05);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
