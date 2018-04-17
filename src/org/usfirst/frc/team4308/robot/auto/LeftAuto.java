package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.MoveConveyor;
import org.usfirst.frc.team4308.robot.commands.MoveFlag;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftAuto extends CommandGroup {

	public LeftAuto(String key) {
		
		addParallel(new MoveFlag(0, false, 1.3));
		
		// Alliance switch is on the left
		if (Robot.gameData.charAt(0) == 'L') {
			addSequential(new Move(-148.0)); // Move 148"
			addSequential(new Rotate(90.0)); // Rotate 90 degrees to the right
			addSequential(new TimedMove(0.5, -1.0)); // For generous overshooting
			addSequential(new MoveConveyor(1.0), 1.0); // Move conveyor
		} 
		// Alliance switch is on the right
		else {
			// 100% auto, go to other side to score cube
			if (key.startsWith("100")) {
				addSequential(new Move(-230.0)); // Move 230"
				addSequential(new Rotate(90.0)); // Rotate 90 degrees to the right
				addSequential(new Move(-215.0)); // Move 215"
				addSequential(new Rotate(90.0)); // Rotate 90 degrees to the right
				addSequential(new Move(-82.0));  // Move 82"
				addSequential(new Rotate(90.0)); // Rotate 90 degrees to the right
				addSequential(new TimedMove(0.5, -1.0)); // For generous overshooting
				addSequential(new MoveConveyor(1.0), 1.0); // Move conveyor
			}
			// No special setting, do not score cube
			else {
				addSequential(new Move(-148.0)); // Move 148"
			}
		}
		
	}

}
