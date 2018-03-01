package org.usfirst.frc.team4308.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestPath extends CommandGroup {

	public TestPath() {
		
		addSequential(new Move(60.0));
		addSequential(new Rotate(-90.0));
		addSequential(new Move(24.0));
		addSequential(new WaitCommand(2.0));
		addSequential(new Move(-24.0));
		addSequential(new Rotate(90.0));
		addSequential(new Move(48.0));
		
	}
	
	
	
}
