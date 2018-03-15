package org.usfirst.frc.team4308.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathRightLeft extends CommandGroup {
	
	public PathRightLeft() {
		
		addSequential(new Move(105.0)); // Move 105" (overshoot 100")
		
	}

}
