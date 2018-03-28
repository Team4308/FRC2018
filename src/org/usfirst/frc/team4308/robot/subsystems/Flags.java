package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.commands.AbsoluteFlags;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Flags extends Subsystem {

	private Servo[] servos;
	private FlagStatus[] statuses;
	
	public enum FlagStatus {
		UP, DOWN, STOPPED
	}
	
	private double raised = 1.0;
	private double down = 0.0;
	
	public Flags() {
		servos = new Servo[2];
		servos[0] = new Servo(0);
		servos[1] = new Servo(1);
		
		statuses = new FlagStatus[2];
		retreatFlags();
		
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AbsoluteFlags());
	}
	
	public void retreatFlags() {
		for (int i = 0; i < servos.length; i++) {
			servos[i].set(down);
			statuses[i] = FlagStatus.DOWN;
		}
	}

	public void stopFlag(int channel) {
		servos[channel].setDisabled();
		statuses[channel] = FlagStatus.STOPPED;
	}
	
	public void switchFlag(int channel) {
		if (channel >= servos.length) {
			System.out.println("Servo channel out of range");
			return;
		}
		if (statuses[channel] == FlagStatus.DOWN) {
			raiseFlag(channel);
		}
		else {
			dropFlag(channel);
		}
	}
	
	public void raiseFlag(int channel) {
		if (channel >= servos.length) {
			System.out.println("Servo channel out of range");
			return;
		}
		servos[channel].set(raised);
		statuses[channel] = FlagStatus.UP;
	}
	
	public void dropFlag(int channel) {
		if (channel >= servos.length) {
			System.out.println("Servo channel out of range");
			return;
		}
		servos[channel].set(down);
		statuses[channel] = FlagStatus.DOWN;
	}

}
