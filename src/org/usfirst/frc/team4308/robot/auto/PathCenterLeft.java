package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathCenterLeft extends CommandGroup {
	
	public PathCenterLeft() {
		
		addSequential(new Move(20.0)); // Move 20" forward
		addSequential(new Rotate(-45.0)); // Rotate 45 degrees left
		addSequential(new Move(88.0)); // Move 62.25" left and forward
		addSequential(new Rotate(45.0)); // Rotate 45 degrees right (straighten)
		addSequential(new Move(20.0)); // Move 20" forward (overshooting 17.75")
		addSequential(new PullConveyor(false), 2.5);
		
	}

}
