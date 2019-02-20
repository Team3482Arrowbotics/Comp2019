package frc.robot.commands.CommandGroups.Vision;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.Move;
import frc.robot.commands.Turn;
import frc.robot.commands.HATCH.HatchIn;
import frc.robot.commands.HATCH.HatchOut;
import frc.robot.commands.VISION.AlignToTarget;
import frc.robot.commands.VISION.CenterToTarget;

public class StraightHatchAlignment extends CommandGroup {
    public StraightHatchAlignment(){
        // addSequential(new CenterToTarget());
        addSequential(new AlignToTarget(false));
        addSequential(new HatchOut());
        addSequential(new Move(-575));
        //testing eeeee
    }
}