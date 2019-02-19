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
public class ArmVator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void run()
  {
      int z = RobotMap.armTurn.getSelectedSensorPosition();

      if(RobotMap.armTurn.getSelectedSensorPosition() > 61500)
      {
          RobotMap.armTurn.set(ControlMode.Position, 60000);
      } 
      else if (RobotMap.armTurn.getSelectedSensorPosition() < 0)
      {
          RobotMap.armTurn.set(ControlMode.Position, 1000);
      }

      if(Math.abs(OI.flightStick.getRawAxis(3)) >= 0.1)
      {
           if(OI.flightStick.getRawAxis(3) < 0)
           {
              RobotMap.armTurn.set(OI.flightStick.getRawAxis(3) * 0.6);

              if(RobotMap.armTurn.getSelectedSensorPosition() > 61500)
              {
                RobotMap.armTurn.set(ControlMode.Position, 60000);
              } 
              else if (RobotMap.armTurn.getSelectedSensorPosition() < 0)
              {
                RobotMap.armTurn.set(ControlMode.Position, 1000);
              }
          }
          else
          {
              RobotMap.armTurn.set(OI.flightStick.getRawAxis(3) * 0.8);

              if(RobotMap.armTurn.getSelectedSensorPosition() > 61500)
              {
                RobotMap.armTurn.set(ControlMode.Position, 60000);
              } 
              else if (RobotMap.armTurn.getSelectedSensorPosition() < 0)
              {
                RobotMap.armTurn.set(ControlMode.Position, 1000);
              }
          }
      }
      else
      {
          RobotMap.armTurn.set(ControlMode.Position, z);
      }

  }
}
