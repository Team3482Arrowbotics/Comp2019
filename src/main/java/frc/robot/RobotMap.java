/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.subsystems.Elevator;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static WPI_TalonSRX elevatorTalonOne;
  public static WPI_TalonSRX elevatorTalonTwo;

  public static WPI_TalonSRX armIntake;

  public static WPI_TalonSRX armTurn;

  public static DoubleSolenoid fArm;
  public static DoubleSolenoid hatch;

  public static Elevator e;

  public static Compressor c;

  public static void init()
  {
    c = new Compressor(0);
    c.setClosedLoopControl(true);
    elevatorTalonOne = new WPI_TalonSRX(3); //3
    elevatorTalonOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,30);
    elevatorTalonOne.configAllowableClosedloopError(0,0,30);
    int absPos = elevatorTalonOne.getSensorCollection().getPulseWidthPosition();
    absPos &= 0xFFF;
    elevatorTalonOne.setSelectedSensorPosition(absPos, 0, 30);
    elevatorTalonOne.config_kP(0,0.05,30);
    elevatorTalonOne.config_kI(0,0,30);
    elevatorTalonOne.config_kD(0,10,30);
    elevatorTalonOne.setInverted(false); //ARMTURN TALON: SET TO FALSE UNDER NORMAL CIRCUMSTANCES (UP to DOWN)

    fArm = new DoubleSolenoid(6,7);
    hatch = new DoubleSolenoid(4,5);

    armIntake = new WPI_TalonSRX(4);

    elevatorTalonTwo = new WPI_TalonSRX(8); //8
    elevatorTalonTwo.setInverted(true);
    elevatorTalonTwo.follow(elevatorTalonOne);

    e = new Elevator();

    armTurn = new WPI_TalonSRX(5); //5
    armTurn.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    armTurn.configAllowableClosedloopError(0,0,30);
    int absPos4 = armTurn.getSensorCollection().getPulseWidthPosition();
    absPos4 &= 0xFFF;
    armTurn.setSelectedSensorPosition(absPos4, 0, 30);
    armTurn.config_kP(0,0.05,30);
    armTurn.config_kI(0,0,30);
    armTurn.config_kD(0,10,30);
    armTurn.setInverted(true);
  }
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
