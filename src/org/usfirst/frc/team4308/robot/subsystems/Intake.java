package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.util.IntakeState;
import org.usfirst.frc.team4308.robot.util.PincerState;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	public static Spark intakeLeft,intakeRight;
	
	public PincerState pincerState = PincerState.CLOSE;
	public IntakeState intakeState = IntakeState.OFF;
	
	
	public static Compressor c;
	public static DoubleSolenoid intake;
	
	public Intake() {
		// TODO Auto-generated constructor stub
		c = new Compressor();
		intake = new DoubleSolenoid(0, 1);
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void openClaw() {
		intake.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeClaw() {
		intake.set(DoubleSolenoid.Value.kForward);
	}
	
	public void inTake() {
		intakeLeft.set(1);
		intakeRight.set(-1);
	}
	
	public void outTake() {
		intakeLeft.set(-1);
		intakeRight.set(1);
	}
	
	public void offTake() {
		intakeLeft.set(0);
		intakeRight.set(0);
	}

}
