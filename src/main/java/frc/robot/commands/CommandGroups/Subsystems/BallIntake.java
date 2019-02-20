package frc.robot.commands.CommandGroups.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ARM.*;
import frc.robot.commands.ELEVATOR.*;
import frc.robot.commands.HATCH.*;

public class BallIntake extends CommandGroup
{
    public BallIntake()
    {
        addSequential(new ElevatorZero());
        addSequential(new TimedArmDown());
        addSequential(new ClawSpintake(),0.5);
        addSequential(new TimedArmUp());
    }
}