package org.usfirst.frc.team4308.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathLeftRight extends CommandGroup {
	
	public PathLeftRight() {
		
		addSequential(new Rotate(-30.0)); // Rotate 30 degrees
		addSequential(new Move(115.0)); // Move 115" (overshoot 103.5")
		
	}

}
