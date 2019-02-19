/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Vision;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class Vision {
    String arg;
    NetworkTableEntry centerX, xDist, distance, angle, isLeft;
    public Vision(NetworkTableEntry centerX, NetworkTableEntry xDist, NetworkTableEntry distance, NetworkTableEntry angle, NetworkTableEntry isLeft){
        this.centerX = centerX;
        this.xDist = xDist;
        this.distance = distance;
        this.angle = angle;
        this.isLeft = isLeft;
    }
    public double returnCenterX(){
        return centerX.getDouble(0.0);
    }
    public double returnAngle(){
        return angle.getDouble(0.0);
    }
    public double returnDistance(){
        return distance.getDouble(0.0);
    }
    public double returnXDist(){
        return xDist.getDouble(-1.0);
    }
    public boolean returnIsLeft(){
        return isLeft.getBoolean(false);
    }
}
