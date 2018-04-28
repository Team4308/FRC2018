package org.usfirst.frc.team4308.robot.auto;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.commands.MoveConveyor;
import org.usfirst.frc.team4308.robot.commands.MoveFlag;
import org.usfirst.frc.team4308.robot.commands.ToggleIntake;
import org.usfirst.frc.team4308.robot.commands.ToggleIntake.ToggleType;
import org.usfirst.frc.team4308.robot.commands.MoveIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterAuto extends CommandGroup {
	public CenterAuto(String key){
		
		addParallel(new MoveFlag(0, false, 1.3));
		
		// Left side is our alliance switch
		if (Robot.gameData.charAt(0) == 'L') {
			
			// Ontario CMP Auto
			addSequential(new Move(-20.0)); // Move 20" forward
			addSequential(new Rotate(-45.0)); // Rotate 45 degrees left
			addSequential(new Move(-88.0)); // Move 62.25" left and forward
			addSequential(new Rotate(45.0)); // Rotate 45 degrees right (straighten)
			addSequential(new Move(-17.75)); // Move 19" forward (overshooting 17.75")			
			addSequential(new MoveConveyor(1.0), 1.0);
			
			// McMaster tested auto
//			addSequential(new Move(-20.0)); // Move 20" forward
//			addSequential(new Rotate(-45.0)); // Rotate 45 degrees left
//			addSequential(new Move(-88.0)); // Move 62.25" left and forward
//			addSequential(new Rotate(45.0)); // Rotate 45 degrees right (straighten)
//			addSequential(new Move(-20.0)); // Move 20" forward (overshooting 17.75")		
//			addSequential(new PullConveyor(false), 1.0);
			
			// Ryerson tested auto
//			addSequential(new Move(-20.0)); // Move 20" forward
//			addSequential(new Rotate(-45.0)); // Rotate 45 degrees left
//			addSequential(new Move(-88.0)); // Move 62.25" left and forward
//			addSequential(new Rotate(45.0)); // Rotate 45 degrees right (straighten)
//			addSequential(new Move(-30.0)); // Move 20" forward (overshooting 17.75")
//			addSequential(new PullConveyor(false), 2.5);
									
			// Reset to center
			if (key.contains("M")) {
				addSequential(new Move(60.0));
				addSequential(new Rotate(-90.0)); 
				addParallel(new ToggleIntake(ToggleType.OPEN));
				addSequential(new Move(56.5)); // 56.5
				addSequential(new Rotate(-90.0));
			}
			// Get another cube
			else if (key.contains("2")) {
				
				addParallel(new ToggleIntake(ToggleType.OPEN));
				addSequential(new Move(73.0)); // 63 + 20 for robot size - 10 for adjust
				addSequential(new RotateLong(-135.0));
				addParallel(new Move(68.0)); // 64 + 4 for a little more
				addSequential(new MoveIntake(-1.0, -1.0), 3.0);
				
				if (key.contains("V")) {
					
//					addParallel(new IntakeToggle(ToggleType.CLOSE));
					addParallel(new MoveIntake(-1.0, -1.0), 5.0);
					addSequential(new Move(-34.0)); // 32 + 2 since a little more
					addSequential(new RotateLong(135.0));
					addSequential(new Move(42.0)); // 78 - 20 - 6 for intake size - 20 for adjust
					
					if (key.contains("A")) {
						addParallel(new MoveConveyor(-1.0), 2.0);
						addSequential(new MoveIntake(1.0, 1.0), 2.0);
					}
					
				}
				
				if (key.contains("S")) {
					
//					addParallel(new ToggleIntake(ToggleType.CLOSE));
					addParallel(new MoveIntake(-1.0, -1.0), 1.0);
					addSequential(new Move(-66.0));
					addSequential(new RotateLong(135.0));
					addSequential(new Move(-75.0)); // 73 + 2 for adjust
//					addParallel(new ToggleIntake(ToggleType.OPEN));
					addParallel(new MoveIntake(-1.0, -1.0), 2.0);
					addSequential(new MoveConveyor(1.0), 2.0);
					
					if (key.contains("3")) {
					
//						addParallel(new ToggleIntake(ToggleType.OPEN));
						addSequential(new Move(52.0)); // 73 - 21
						addSequential(new RotateLong(-135.0));
						addParallel(new Move(52.0)); // 66 - 14
						addSequential(new MoveIntake(-1.0, -1.0), 3.0);
						
						if (key.contains("W")) {

//							addParallel(new ToggleIntake(ToggleType.CLOSE));
							addSequential(new Move(-52.0));
							addSequential(new RotateLong(135.0));
							addSequential(new Move(-52.0));
							addParallel(new ToggleIntake(ToggleType.OPEN));
							addParallel(new MoveIntake(-1.0, -1.0), 2.0);
							addSequential(new MoveConveyor(1.0), 2.0);
							
						}
						
					}
				}
				
			}
			
		} 
		// Right side is our alliance switch
		else {

			// Ontario CMP
			addSequential(new Move(-20.0)); // Move 20" forward
			addSequential(new Rotate(45.0)); // Rotate 45 degrees right
			addSequential(new Move(-72.0)); // Move 50.75" left and forward
			addSequential(new Rotate(-45.0)); // Rotate 45 degrees left (straighten)
			addSequential(new Move(-29.25)); // Move 31" forward (overshooting 29.25")
			addSequential(new MoveConveyor(1.0), 1.0);
			
			// McMaster tested auto
//			addSequential(new Move(-20.0)); // Move 20" forward
//			addSequential(new Rotate(45.0)); // Rotate 45 degrees right
//			addSequential(new Move(-72.0)); // Move 50.75" left and forward
//			addSequential(new Rotate(-45.0)); // Rotate 45 degrees left (straighten)
//			addSequential(new Move(-32.0)); // Move 32" forward (overshooting 29.25")
//			addSequential(new PullConveyor(false), 1.0);
						
			// Ryerson tested auto
//			addSequential(new Move(-20.0)); // Move 20" forward
//			addSequential(new Rotate(45.0)); // Rotate 45 degrees right
//			addSequential(new Move(-72.0)); // Move 50.75" left and forward
//			addSequential(new Rotate(-45.0)); // Rotate 45 degrees left (straighten)
//			addSequential(new Move(-42.0)); // Move 32" forward (overshooting 29.25")
//			addSequential(new PullConveyor(false), 2.5);
			
			// Reset to center
			if (key.contains("M")) {
				addSequential(new Move(60.0));
				addSequential(new Rotate(90.0)); 
				addParallel(new ToggleIntake(ToggleType.OPEN));
				addSequential(new Move(56.5)); // 56.5
				addSequential(new Rotate(90.0));
			}
			// Get another cube
			else if (key.contains("2")) {
				
				addParallel(new ToggleIntake(ToggleType.OPEN));
				addSequential(new Move(73.0)); // 63 + 20
				addSequential(new RotateLong(135.0));
				addParallel(new Move(66.0)); // 62 + 4 for a little more
				addSequential(new MoveIntake(-1.0, -1.0), 3.0);

				
				if (key.contains("V")) {
					
//					addParallel(new IntakeToggle(ToggleType.CLOSE));
					addParallel(new MoveIntake(-1.0, -1.0), 5.0);
					addSequential(new Rotate(-45)); // Rotate immediately
					addSequential(new Move(48.0)); // Move across
					addSequential(new Rotate(-90.0));
					addSequential(new Move(62.0)); // 88 - 20 - 6 for intake size
					
					if (key.contains("A")) {
						addParallel(new MoveConveyor(-1.0), 2.0);
						addSequential(new MoveIntake(1.0, 1.0), 2.0);
					}
					
				}
				
				if (key.contains("S")) {

//					addParallel(new ToggleIntake(ToggleType.CLOSE));
					addParallel(new MoveIntake(-1.0, -1.0), 1.0);
					addSequential(new Move(-64.0));
					addSequential(new RotateLong(-135.0));
					addSequential(new Move(-75.0)); // 73 + 2 for adjust
//					addParallel(new ToggleIntake(ToggleType.OPEN));
					addParallel(new MoveIntake(-1.0, -1.0), 2.0);
					addSequential(new MoveConveyor(1.0), 2.0);

					if (key.contains("3")) {
					
//						addParallel(new ToggleIntake(ToggleType.OPEN));
						addSequential(new Move(52.0)); // 73 - 21
						addSequential(new RotateLong(135.0));
						addParallel(new Move(52.0)); // 66 - 14
						addSequential(new MoveIntake(-1.0, -1.0), 3.0);
						
						if (key.contains("W")) {

//							addParallel(new ToggleIntake(ToggleType.CLOSE));
							addSequential(new Move(-52.0));
							addSequential(new RotateLong(-135.0));
							addSequential(new Move(-52.0));
//							addParallel(new ToggleIntake(ToggleType.OPEN));
							addParallel(new MoveIntake(-1.0, -1.0), 2.0);
							addSequential(new MoveConveyor(1.0), 2.0);
							
						}
						
					}
				}
				
			}
		}
		
		addSequential(new MoveFlag(0, true, 1.3));
		
	}
}