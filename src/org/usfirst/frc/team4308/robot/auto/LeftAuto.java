package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftAuto extends CommandGroup {

	public LeftAuto() {
		if (Robot.gameData.charAt(0) == 'L') {
			// Alliance Switch is on the left; attempt to score cube!
			addSequential(new Move(-148.0)); // Move 148"
			addSequential(new Rotate(90));
			addSequential(new Move(-20.0)); // For generous overshooting
			addSequential(new PullConveyor(false), 2.5);
		} else {
			//Alliance Switch is on the Right Side; Ignore and go for BaseLine
			addSequential(new Move(-148.0)); // Move 148"
		}
	}

}
