package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathCenterRight extends CommandGroup {

	public PathCenterRight() {
		
		addSequential(new Move(20.0)); // Move 20" forward
		addSequential(new Rotate(45.0)); // Rotate 45 degrees left
		addSequential(new Move(72.0)); // Move 50.75" left and forward
		addSequential(new Rotate(-45.0)); // Rotate 45 degrees right (straighten)
		addSequential(new Move(32.0)); // Move 32" forward (overshooting 29.25")
		addSequential(new PullConveyor(false), 2.5);
		
	}
	
}
