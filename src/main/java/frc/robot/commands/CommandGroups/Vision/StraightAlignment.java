package frc.robot.commands.CommandGroups.Vision;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.Move;
import frc.robot.commands.HATCH.HatchIn;
import frc.robot.commands.HATCH.HatchOut;
import frc.robot.commands.VISION.AlignToTarget;

public class StraightAlignment extends CommandGroup {
    public StraightAlignment(){
        addSequential(new AlignToTarget());
        if(RobotMap.hatch.get() == Value.kForward){
            addSequential(new HatchIn());
        } else {
            addSequential(new HatchOut());
        }
        addSequential(new Move(-575));
    }
}