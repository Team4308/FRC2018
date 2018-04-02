package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.OI;
import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.commands.AbsoluteIntake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	public WPI_TalonSRX intakeLeft, intakeRight;
	
	public enum IntakeState {
		OPEN, CLOSE, OFF
	}
	
	public IntakeState state = IntakeState.CLOSE;
	
	public DoubleSolenoid solenoidLeft;
	public DoubleSolenoid solenoidRight;
	
	public Intake() {

		solenoidLeft = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.Intake.solenoidLeftForward, RobotMap.Intake.solenoidLeftReverse);
		solenoidRight = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.Intake.solenoidRightForward, RobotMap.Intake.solenoidRightReverse);
		
		intakeLeft = new WPI_TalonSRX(RobotMap.Intake.intakeLeft);
		intakeRight = new WPI_TalonSRX(RobotMap.Intake.intakeRight);
		
		intakeRight.setInverted(true);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AbsoluteIntake());
	}
	
	public void switchIntake() {
		if (state == IntakeState.CLOSE) {
			openIntake();
		} else {
			closeIntake();
		}
	}
	
	public void openIntake() {
		solenoidLeft.set(DoubleSolenoid.Value.kReverse);
		solenoidRight.set(DoubleSolenoid.Value.kReverse);
		state = IntakeState.OPEN;
	}
	
	public void closeIntake() {
		solenoidLeft.set(DoubleSolenoid.Value.kForward);
		solenoidRight.set(DoubleSolenoid.Value.kForward);
		state = IntakeState.CLOSE;
	}
	
	public void offIntake() {
		solenoidLeft.set(DoubleSolenoid.Value.kOff);
		solenoidRight.set(DoubleSolenoid.Value.kOff);
		state = IntakeState.OFF;
	}
	
	public void intakeControl() {
		
		Robot.pdp.clearStickyFaults();
		
		moveIntake(OI.getIntakeSchemeLeft(), OI.getIntakeSchemeRight());
		
	}
	
	public void moveIntake(double left, double right) {

		intakeLeft.set(left);
		intakeRight.set(right);
		
	}
	
	
	public void stopIntake() {
		intakeLeft.set(0);
		intakeRight.set(0);
	}

}
