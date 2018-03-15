package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightAuto extends CommandGroup {
	public RightAuto(){

		if (Robot.gameData.charAt(0) == ('L')) {
			//Alliance Switch is on the left side; ignore and cross baseline
			addSequential(new Move(100.0)); // Move 100"
		} else {
			//Alliance Switch is on the right side; go for switch
			addSequential(new Move(105.0)); // Move 105" (overshoot 100")
			addSequential(new PullConveyor(false), 2.5);
		}

	}
}
