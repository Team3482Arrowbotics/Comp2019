package frc.robot.commands.CommandGroups.Vision;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.Move;
import frc.robot.commands.Turn;

public class TrigAlignment extends CommandGroup {
    public TrigAlignment(double distance, double angle, boolean isLeft) {
        int isReallyLeft;
        if(isLeft){
            isReallyLeft = -1;
        } else {
            isReallyLeft = 1;
        }
        addSequential(new Turn((90 - angle) * isReallyLeft * -1));
        addSequential(new Move(Math.sin(Math.toRadians(angle)) * distance));
        addSequential(new Turn(90 * isReallyLeft));
        addSequential(new StraightHatchAlignment());
    }
}