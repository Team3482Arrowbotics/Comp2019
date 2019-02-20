package frc.robot.Vision;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PIDSource;
import frc.robot.RobotMap;

public class VisionAngle implements PIDSource{
    double setpoint = 0;
    PIDSourceType type = PIDSourceType.kDisplacement;
    public VisionAngle(){

    }
    public double pidGet(){
        double angle = RobotMap.vision.returnCenterX();
        if(angle >= 0){
            return angle;
        } else {
            return setpoint;
        }
    }
    public void setPid(double setpoint){
        this.setpoint = setpoint;
        RobotMap.visionController.setSetpoint(setpoint);
    }
    public PIDSourceType getPIDSourceType(){
        return type;
    }
    public void setPIDSourceType(PIDSourceType type){
        this.type = type;
    }
}