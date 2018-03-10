package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.subsystems.Conveyor;
import org.usfirst.frc.team4308.robot.subsystems.Conveyor.ConveyorState;

import edu.wpi.first.wpilibj.command.Command;

public class PullConveyor extends Command {

	private boolean reversed;

	public PullConveyor(boolean reverse) {
		reversed = reverse;
	}

	@Override
	protected void execute() {
		super.execute();

		if (!reversed) {
			Robot.conveyor.moveConveyor(1);
			Robot.conveyor.conveyorState = ConveyorState.DOWN;
		} else {
			Robot.conveyor.moveConveyor(-1);
			Robot.conveyor.conveyorState = ConveyorState.UP;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		Robot.conveyor.stopConveyor();
		Conveyor.conveyorState = ConveyorState.OFF;
	}

	protected void interrupted() {
		Robot.conveyor.stopConveyor();
		Conveyor.conveyorState = ConveyorState.OFF;
	}

}
