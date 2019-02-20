package frc.robot.commands.CommandGroups.Vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class Alignment extends CommandGroup {
    public Alignment(){
        double angle = RobotMap.vision.returnAngle();
        if(angle < 20){
            //addSequential(new StraightHatchAlignment());
        } else {
            double distance = RobotMap.vision.returnDistance();
            boolean isLeft = RobotMap.vision.returnIsLeft();
            addSequential(new TrigAlignment(distance, angle, isLeft));
        }
    }
}