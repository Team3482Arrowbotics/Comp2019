package frc.robot.commands.CommandGroups.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.ARM.*;
import frc.robot.commands.CommandGroups.Vision.Alignment;
import frc.robot.commands.ELEVATOR.*;
import frc.robot.commands.HATCH.HatchIn;
import frc.robot.commands.HATCH.HatchOut;
import frc.robot.commands.VISION.*;
import frc.robot.commands.*;

public class HatchOuttakeOne extends CommandGroup
{
    public HatchOuttakeOne()
    {
        addSequential(new HatchOut());
    }
}