package org.usfirst.frc.team4308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Auto extends CommandGroup {

	public Auto() {
		
		addSequential(new Move(60.0));
		addSequential(new Rotate(-90.0));
		addSequential(new Move(24.0));
		addSequential(new WaitCommand(2.0));
		addSequential(new Move(-24.0));
		addSequential(new Rotate(90.0));
		addSequential(new Move(48.0));
		
	}
	
	
	
}
