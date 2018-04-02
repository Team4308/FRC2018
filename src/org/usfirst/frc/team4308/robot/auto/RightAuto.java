package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.PullConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightAuto extends CommandGroup {
  
	public RightAuto(String key){
		if (Robot.gameData.charAt(0) == ('L')) {
			//Alliance Switch is on the left side; ignore and cross baseline
			addSequential(new Move(-148.0)); // Move 148"
		} else {
			//Alliance Switch is on the right side; go for switch
			addSequential(new Move(-148.0)); // Move 148"
			addSequential(new Rotate(-90.0));
			addSequential(new Move(-20.0)); // Generous overshooting
			addSequential(new PullConveyor(false), 2.5);
		}

	}
}
