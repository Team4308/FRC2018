package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightAuto extends CommandGroup {
  
	public RightAuto(String key){
		
		// Alliance switch is on the left
		if (Robot.gameData.charAt(0) == ('L')) {
			// 100% auto, go to other side to score cube
			if (key.startsWith("100")) {
				addSequential(new Move(-230.0)); // Move 230"
				addSequential(new Rotate(-90.0)); // Rotate 90 degrees to the left
				addSequential(new Move(-215.0)); // Move 215"
				addSequential(new Rotate(-90.0)); // Rotate 90 degrees to the left
				addSequential(new Move(-82.0));  // Move 82"
				addSequential(new Rotate(-90.0)); // Rotate 90 degrees to the left
				addSequential(new TimedMove(1.0, -1.0)); // For generous overshooting
				addParallel(new DelayedCommand(new PullConveyor(false), 2.5), 0.5); // Move conveyor while moving, but a bit delayed
			}
			// No special setting, do not score cube
			else {
				addSequential(new Move(-148.0)); // Move 148"
			}
		} 
		// Alliance switch is on the right
		else {
			addSequential(new Move(-148.0)); // Move 148"
			addSequential(new Rotate(-90.0)); // Rotate 90 degrees to the left
			addSequential(new TimedMove(1.0, -1.0)); // For generous overshooting
			addParallel(new DelayedCommand(new PullConveyor(false), 2.5), 0.5); // Move conveyor while moving, but a bit delayed
		}

	}
}
