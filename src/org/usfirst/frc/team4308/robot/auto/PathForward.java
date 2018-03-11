package org.usfirst.frc.team4308.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathForward extends CommandGroup {

	public PathForward() {
		
		addSequential(new Move(100.0)); // Move 100"
		
	}
	
}
