package frc.robot.commands.CommandGroups.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.ARM.*;
import frc.robot.commands.CommandGroups.Vision.Alignment;
import frc.robot.commands.ELEVATOR.*;
import frc.robot.commands.*;

public class BallOuttakeOne extends CommandGroup
{
    public BallOuttakeOne()
    {
        addSequential(new Alignment());
        addSequential(new ArmMotion(0,RobotMap.armTurn));
        addSequential(new ElevatorMove(90000));
        addSequential(new ArmDown());
        addSequential(new ClawSpouttake(),0.5);
    }
}