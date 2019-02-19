package frc.robot.Vision;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PIDSource;
import frc.robot.RobotMap;

public class VisionDistance implements PIDSource{
    double setpoint = 0;
    PIDSourceType type = PIDSourceType.kDisplacement;
    public VisionDistance(){

    }
    public double pidGet(){
        double dist = RobotMap.vision.returnXDist();
        if(dist >= 0){
            return dist;
        } else {
            return setpoint;
        }
    }
    public void setPid(double setpoint){
        this.setpoint = setpoint;
        RobotMap.visionDistController.setSetpoint(setpoint);
    }
    public PIDSourceType getPIDSourceType(){
        return type;
    }
    public void setPIDSourceType(PIDSourceType type){
        this.type = type;
    }
}