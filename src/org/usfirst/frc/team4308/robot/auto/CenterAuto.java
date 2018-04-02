package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterAuto extends CommandGroup {
	public CenterAuto(String key){
		if (Robot.gameData.charAt(0) == 'L') {
			// Left side is our alliance switch
//			addSequential(new Move(-14.0));
//			addSequential(new Rotate(-35.0));
//			addSequential(new Move(-105.0));
//			addSequential(new PullConveyor(false), 2.5);
			
			// Ryerson Tested Auto
			addSequential(new Move(-20.0)); // Move 20" forward
			addSequential(new Rotate(-45.0)); // Rotate 45 degrees left
			addSequential(new Move(-88.0)); // Move 62.25" left and forward
			addSequential(new Rotate(45.0)); // Rotate 45 degrees right (straighten)
			addSequential(new Move(-30.0)); // Move 20" forward (overshooting 17.75")
			addSequential(new PullConveyor(false), 2.5);
		} else {
			// Right side is our alliance switch
//			addSequential(new Move(-14.0));
//			addSequential(new Rotate(35.0));
//			addSequential(new Move(-105.0));
//			addSequential(new PullConveyor(false), 2.5);
			
			// Ryerson Tested Auto
			addSequential(new Move(-20.0)); // Move 20" forward
			addSequential(new Rotate(45.0)); // Rotate 45 degrees left
			addSequential(new Move(-72.0)); // Move 50.75" left and forward
			addSequential(new Rotate(-45.0)); // Rotate 45 degrees right (straighten)
			addSequential(new Move(-42.0)); // Move 32" forward (overshooting 29.25")
			addSequential(new PullConveyor(false), 2.5);
		}
}
}