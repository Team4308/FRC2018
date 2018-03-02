package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathRightRight extends CommandGroup {
	
	public PathRightRight() {
		
		addSequential(new Move(105.0)); // Move 105" (overshoot 100")
		addSequential(new PullConveyor(false), 2.5);
		
	}

}
