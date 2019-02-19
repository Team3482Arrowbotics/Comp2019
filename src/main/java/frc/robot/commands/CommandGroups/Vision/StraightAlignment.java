package frc.robot.commands.CommandGroups.Vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ARM.*;
import frc.robot.commands.HATCH.*;

public class StraightAlignment extends CommandGroup{
    public TrigAlignment(double distance, double angle, boolean isLeft)
    {
        addSequential(new AlignToTarget());
        addSequential(new HatchOut());
        addSequential(new Move(-5));
    }
}