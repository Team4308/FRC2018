package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.SynchronousPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BankMove extends Command {
	
	private SynchronousPID pid;
	private double movement;
	private double rotation;
	
	public BankMove(double displacement, double angle) {
		pid = new SynchronousPID();	
		pid.setOutputRange(-0.3, 0.3);
		
		if (SmartDashboard.getNumber("Bank P Val", -500.0) == -500.0) { // -500.0 is an arbitrary number
			SmartDashboard.putNumber("Bank P Val", 0.02); // P default value
		}
		if (SmartDashboard.getNumber("Bank I Val", -500.0) == -500.0) {
			SmartDashboard.putNumber("Bank I Val", 0.0); // I default value
		}
		if (SmartDashboard.getNumber("Bank D Val", -500.0) == -500.0) {
			SmartDashboard.putNumber("Bank D Val", 0.2); // D default value
		}
		
	    	movement = displacement;
	    rotation = angle;
	    	
		requires(Robot.drive);
		setTimeout(2.0);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		double Kp = SmartDashboard.getNumber("Move P Val", 0.0); 
		double Ki = SmartDashboard.getNumber("Move I Val", 0.0);
		double Kd = SmartDashboard.getNumber("Move D Val", 0.0);
		
		pid.setPID(Kp, Ki, Kd);
		pid.reset();
		pid.setSetpoint(movement);
		
		Robot.drive.resetSensors();
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		double calculation = pid.calculate((Robot.drive.getLeftSensorPosition() + Robot.drive.getRightSensorPosition())/2);
		
		SmartDashboard.putNumber("Left Encoder Position", Robot.drive.getLeftSensorPosition());
		SmartDashboard.putNumber("Left Encoder Velocity", Robot.drive.frontLeft.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right Encoder Position", Robot.drive.getRightSensorPosition());
		SmartDashboard.putNumber("Right Encoder Velocity", Robot.drive.frontRight.getSelectedSensorVelocity(0));
		
		Robot.drive.setDrive(calculation, calculation); // TODO - not working (robot keeps moving)
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return pid.onTarget(2.0) || isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drive.stopMoving();
		pid.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
