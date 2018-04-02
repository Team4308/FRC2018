package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.PullConveyor;
import org.usfirst.frc.team4308.robot.commands.IntakeToggle;
import org.usfirst.frc.team4308.robot.commands.IntakeToggle.ToggleType;
import org.usfirst.frc.team4308.robot.commands.MoveIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterAuto extends CommandGroup {
	public CenterAuto(String key){
		
		// Left side is our alliance switch
		if (Robot.gameData.charAt(0) == 'L') {
			
			// New auto
			addSequential(new Move(-11.0));
			addSequential(new Rotate(-35.0));
			addSequential(new TimedMove(3.0, -1.0)); // For generous overshooting
			addParallel(new DelayedCommand(new PullConveyor(false), 2.5), 0.5); // Move conveyor while moving, but a bit delayed
			
//			addSequential(new Move(-110.0));
//			addSequential(new PullConveyor(false), 2.5);
			
			// Ryerson tested auto
//			addSequential(new Move(-20.0)); // Move 20" forward
//			addSequential(new Rotate(-45.0)); // Rotate 45 degrees left
//			addSequential(new Move(-88.0)); // Move 62.25" left and forward
//			addSequential(new Rotate(45.0)); // Rotate 45 degrees right (straighten)
//			addSequential(new Move(-30.0)); // Move 20" forward (overshooting 17.75")
//			addSequential(new PullConveyor(false), 2.5);
			
			// Get another cube
			if (key.contains("2")) {
				addSequential(new Move(50.0));
				addSequential(new Rotate(-90.0)); 
				addSequential(new Move(56.5));
				addParallel(new IntakeToggle(ToggleType.OPEN));
				addSequential(new Rotate(-90.0));
				addSequential(new Move(10.0));
				addParallel(new MoveIntake(1.0, 1.0, 2.0));
				
				if (key.contains("S")) {
					addSequential(new IntakeToggle(ToggleType.CLOSE));
					addSequential(new Move(10.0));
					addSequential(new Rotate(-90.0));
					addSequential(new Move(56.5));
					addSequential(new Rotate(-90.0));
					
					addSequential(new TimedMove(1.0, -1.0)); // For generous overshooting
					addParallel(new DelayedCommand(new PullConveyor(false), 2.5), 0.5); // Move conveyor while moving, but a bit delayed
				}
			}
			
		} 
		// Right side is our alliance switch
		else {
			
			//New auto
			addSequential(new Move(-11.0));
			addSequential(new Rotate(30.0));
			addSequential(new TimedMove(3.0, -1.0)); // For generous overshooting
			addParallel(new DelayedCommand(new PullConveyor(false), 2.5), 0.5); // Move conveyor while moving, but a bit delayed
			
//			addSequential(new Move(-110.0));
//			addSequential(new PullConveyor(false), 2.5);
			
			// Ryerson tested auto
//			addSequential(new Move(-20.0)); // Move 20" forward
//			addSequential(new Rotate(45.0)); // Rotate 45 degrees left
//			addSequential(new Move(-72.0)); // Move 50.75" left and forward
//			addSequential(new Rotate(-45.0)); // Rotate 45 degrees right (straighten)
//			addSequential(new Move(-42.0)); // Move 32" forward (overshooting 29.25")
//			addSequential(new PullConveyor(false), 2.5);
			
			// Get another cube
			if (key.contains("2")) {
				addSequential(new Move(50.0));
				addSequential(new Rotate(90.0)); 
				addSequential(new Move(56.5));
				addParallel(new IntakeToggle(ToggleType.OPEN));
				addSequential(new Rotate(90.0));
				addSequential(new Move(10.0));
				addParallel(new MoveIntake(1.0, 1.0, 2.0));
				
				if (key.contains("S")) {
					addSequential(new IntakeToggle(ToggleType.CLOSE));
					addSequential(new Move(10.0));
					addSequential(new Rotate(90.0));
					addSequential(new Move(56.5));
					addSequential(new Rotate(90.0));
					
					addSequential(new TimedMove(1.0, -1.0)); // For generous overshooting
					addParallel(new DelayedCommand(new PullConveyor(false), 2.5), 0.5); // Move conveyor while moving, but a bit delayed
				}
			}
		}
		
		// Got another cube, now vault
		if (key.contains("2") && key.contains("V")) {
			addSequential(new IntakeToggle(ToggleType.CLOSE));
			addSequential(new Move(-25.0));
			addSequential(new Rotate(-90.0));
			addSequential(new Move(25.0));
			addSequential(new Rotate(-90.0));
			addSequential(new Move(-25.0));
			addParallel(new IntakeToggle(ToggleType.OPEN));
			addSequential(new MoveIntake(-1.0, -1.0, 2.0));
		}
	}
}