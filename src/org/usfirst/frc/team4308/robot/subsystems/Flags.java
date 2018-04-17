package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Flags extends Subsystem {

	private Talon[] talons;
	
	private double up = 0.5;
	private double down = -0.5;
	
	
	public Flags() {
		talons = new Talon[1];
		talons[0] = new Talon(0);
//		talons[1] = new Talon(1);
	
	}
	
	public void stopFlags() {
		for (int i = 0; i < talons.length; i++) {
			stopFlag(i);
		}
	}
	
	public void stopFlag(int channel) {
		talons[channel].set(0.0);
	}
	
	public void raiseFlag(int channel) {
		if (channel >= talons.length) {
			System.out.println("Servo channel out of range");
			return;
		}
		talons[channel].set(up);
	}
	
	public void dropFlag(int channel) {
		if (channel >= talons.length) {
			System.out.println("Servo channel out of range");
			return;
		}
		talons[channel].set(down);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
