/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class Vision implements PIDSource{
    PIDSourceType type = PIDSourceType.kDisplacement;
    public Vision(String arg){

    }
    public void run(){

    }
    public double pidGet(){
        return 0;
    }
    public PIDSourceType getPIDSourceType(){
        return type;
    }
    public void setPIDSourceType(PIDSourceType type){
        this.type = type;
    }
}
