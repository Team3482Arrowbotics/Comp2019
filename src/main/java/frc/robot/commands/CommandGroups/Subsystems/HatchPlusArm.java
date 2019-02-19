package frc.robot.commands.CommandGroups.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ARM.*;
import frc.robot.commands.HATCH.*;

public class HatchPlusArm extends CommandGroup
{
    public HatchPlusArm()
    {
        addSequential(new TimedArmDown());
        addSequential(new HatchIn());
    }
}