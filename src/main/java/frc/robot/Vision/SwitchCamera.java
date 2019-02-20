package frc.robot.Vision;

import frc.robot.RobotMap;

public class SwitchCamera{

    static int TOGGLE_HEIGHT = 90000; //replace 
    public SwitchCamera(){

    }

    public void run(){
        if(RobotMap.elevatorTalonOne.getSelectedSensorPosition() < TOGGLE_HEIGHT){
            
        } else {
            
        }        
    }
    public void toggle(){

    }
}