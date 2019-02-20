package frc.robot.commands.CommandGroups.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.ARM.*;
import frc.robot.commands.CommandGroups.Vision.Alignment;
import frc.robot.commands.ELEVATOR.*;
import frc.robot.commands.HATCH.HatchOut;
import frc.robot.commands.VISION.AlignToTarget;
import frc.robot.commands.VISION.CenterToTarget;
import frc.robot.commands.*;

public class HatchOuttakeThree extends CommandGroup
{
    public HatchOuttakeThree()
    {
        addSequential(new ElevatorMove(180000));
    }
}