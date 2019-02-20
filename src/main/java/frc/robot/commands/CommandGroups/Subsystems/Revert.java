package frc.robot.commands.CommandGroups.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.ArmMotion;
import frc.robot.commands.Move;
import frc.robot.commands.ARM.*;
import frc.robot.commands.ELEVATOR.*;
import frc.robot.commands.HATCH.*;
import frc.robot.commands.VISION.AlignToTarget;
import frc.robot.commands.VISION.CenterToTarget;

public class Revert extends CommandGroup
{
    public Revert()
    {
        addParallel(new ElevatorZero());
        addParallel(new ArmMotion(0, RobotMap.armTurn));
        addSequential(new HatchOut());
    }
}