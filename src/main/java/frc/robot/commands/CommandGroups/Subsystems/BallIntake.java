package frc.robot.commands.CommandGroups.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.ArmMotion;
import frc.robot.commands.ARM.*;
import frc.robot.commands.ELEVATOR.*;
import frc.robot.commands.HATCH.*;

public class BallIntake extends CommandGroup
{
    public BallIntake()
    {
        addParallel(new ElevatorMove(21000));
    }
}