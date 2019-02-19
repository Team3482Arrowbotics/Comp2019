package frc.robot.commands.CommandGroups.Vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ARM.*;
import frc.robot.commands.HATCH.*;

public class TrigAlignment extends CommandGroup{
    public TrigAlignment(double distance, double angle, boolean isLeft)
    {
        int turnCoEff;
        if(isLeft){
            turnCoEff = -1;
        } else {
            turnCoEff = 1;
        }
        addSequential(new Turn(90 - angle));
        addSequential(new Move(Math.sin(Math.toRadians(angle) * distance));
        addSequential(new Turn(90 * turnCoEff));
        addSequential(new AlignToTarget());
        addSequential(hatch)
    }
}