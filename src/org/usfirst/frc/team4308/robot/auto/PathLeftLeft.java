package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathLeftLeft extends CommandGroup {
	
	public PathLeftLeft() {
		
		addSequential(new Move(115.0)); // Move 115" (overshoot 103.5")
		addSequential(new PullConveyor(false), 2.5);
		
	}

}
