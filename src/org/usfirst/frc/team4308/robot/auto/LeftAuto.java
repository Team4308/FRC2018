package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftAuto extends CommandGroup {
	public LeftAuto(){
		//if (Robot.gameData.charAt(0) == 'L') {
			// Alliance Switch is on the left; attempt to score cube!
			addSequential(new Move(115.0)); // Move 115" (overshoot 103.5")
			addSequential(new PullConveyor(false), 2.5);
		//} else {
			//Alliance Switch is on the Right Side; Ignore and go for BaseLine
			addSequential(new Move(100.0)); // Move 100"
		//}
	}

}
