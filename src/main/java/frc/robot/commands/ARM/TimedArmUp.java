/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ARM;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class TimedArmUp extends Command {
    static long startTime;
    static final double MAX_TIME = 1000 * 4;
    static final double RESET_POS = 0;
    static boolean isFinished = true;
  public TimedArmUp() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      startTime = System.currentTimeMillis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      double elapsedTime = System.currentTimeMillis() - startTime;
      if(RobotMap.armTurn.getSelectedSensorPosition() >= RESET_POS)
      {
        double newPosition = RobotMap.armTurn.getSelectedSensorPosition() - 300 * (TimedArmDown.MAX_POS / MAX_TIME);
        if(newPosition < RESET_POS)
        {
            newPosition = RESET_POS;
        }
        RobotMap.armTurn.set(ControlMode.Position, newPosition);
      }
      SmartDashboard.putNumber("New Pos 1: ", (int) RobotMap.armTurn.getSelectedSensorPosition()); //debugging
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
