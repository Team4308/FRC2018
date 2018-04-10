package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetRobotState extends Command {
	
	private String state;
	private boolean sent = false;
	
	public SetRobotState(String state) {
		this.state = state;
	}
	
	@Override
	protected void initialize() {
		sent = false;
	}
	
	@Override
	protected void execute() {
		super.execute();
		
		if (!sent) {
			Robot.leds.setState(state);
			sent = true;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	protected void end() {
//		Robot.leds.setState("normal");
	}

	protected void interrupted() {
//		Robot.leds.setState("normal");
	}

}
