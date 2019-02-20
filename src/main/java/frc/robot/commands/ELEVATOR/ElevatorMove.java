/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ELEVATOR;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class ElevatorMove extends Command {
    double distance;
  public ElevatorMove(double dist) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_subsystem);

    // if(dist < 0){
    //     throw new IllegalArgumentException("Distance must be Positive");
    // }
    
    // if(dist > 180000){
    //     throw new IllegalArgumentException("Distance must be less than max height");
    // }

    distance = dist;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      RobotMap.elevatorTalonOne.set(ControlMode.Position, distance);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
