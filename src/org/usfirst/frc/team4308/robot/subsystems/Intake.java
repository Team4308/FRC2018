package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.AbsoluteIntake;
import org.usfirst.frc.team4308.robot.util.PincerState;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	public static WPI_TalonSRX intakeLeft, intakeRight;
	
	public PincerState pincerState = PincerState.CLOSE;
//	public IntakeState intakeState = IntakeState.OFF;
	
//	public static Compressor c;
	public static DoubleSolenoid intake;
	
	public Intake() {
//		c = new Compressor();
		intake = new DoubleSolenoid(RobotMap.Intake.solenoidForward, RobotMap.Intake.solenoidReverse);
		
		intakeLeft = new WPI_TalonSRX(RobotMap.Intake.intakeLeft);
		intakeRight = new WPI_TalonSRX(RobotMap.Intake.intakeRight);
		
		intakeRight.setInverted(true);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AbsoluteIntake());
	}
	
	public void openIntake() {
		intake.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeIntake() {
		intake.set(DoubleSolenoid.Value.kForward);
	}
	
	public void intakeControl() {
		
		double leftTrigger  = OI.driveStick.getRawAxis(RobotMap.Control.Standard.leftTrigger);
		double rightTrigger = OI.driveStick.getRawAxis(RobotMap.Control.Standard.rightTrigger);
		
		double diff = leftTrigger - rightTrigger;
		
		intakeLeft.set(diff);
		intakeRight.set(diff);
	}
	
	
	public void stopIntake() {
		intakeLeft.set(0);
		intakeRight.set(0);
	}

}
