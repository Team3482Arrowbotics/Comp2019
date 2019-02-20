package frc.robot.Vision;

import edu.wpi.first.wpilibj.PIDOutput;
import frc.robot.RobotMap; 
 
public class LinearDrive implements PIDOutput{
    public LinearDrive(){
    
    }
    public void pidWrite(double output){
        RobotMap.driveDiff.arcadeDrive(0, output);
    }
}