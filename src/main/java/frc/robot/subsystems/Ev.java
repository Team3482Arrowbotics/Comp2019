/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Ev extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void run()
  {
      int z = RobotMap.elevatorTalonOne.getSelectedSensorPosition();

      if(RobotMap.elevatorTalonOne.getSelectedSensorPosition() > 170000)
      {
          RobotMap.elevatorTalonOne.set(ControlMode.Position, 155000);
      } 
      else if (RobotMap.elevatorTalonOne.getSelectedSensorPosition() < 0)
      {
          RobotMap.elevatorTalonOne.set(ControlMode.Position, 5000);
      }

      if(Math.abs(OI.flightStick.getRawAxis(1)) >= 0.1)
      {
           if(OI.flightStick.getRawAxis(1) < 0)
           {
              RobotMap.elevatorTalonOne.set(OI.flightStick.getRawAxis(1) * 0.6);

              if(RobotMap.elevatorTalonOne.getSelectedSensorPosition() > 170000)
              {
                RobotMap.elevatorTalonOne.set(ControlMode.Position, 155000);
              } 
              else if (RobotMap.elevatorTalonOne.getSelectedSensorPosition() < 0)
              {
                RobotMap.elevatorTalonOne.set(ControlMode.Position, 5000);
              }
          }
          else
          {
              RobotMap.elevatorTalonOne.set(OI.flightStick.getRawAxis(1) * 0.8);

              if(RobotMap.elevatorTalonOne.getSelectedSensorPosition() > 170000)
              {
                RobotMap.elevatorTalonOne.set(ControlMode.Position, 155000);
              } 
              else if (RobotMap.elevatorTalonOne.getSelectedSensorPosition() < 0)
              {
                RobotMap.elevatorTalonOne.set(ControlMode.Position, 5000);
              }
          }
      }
      else
      {
          RobotMap.elevatorTalonOne.set(ControlMode.Position, z+1000);
      }

  }
}

