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
import frc.robot.Vision.Vision;
import frc.robot.subsystems.DrivePIDOutput;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.util.WPILibVersion;
import frc.robot.subsystems.PWMLidar;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;


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

  public static Compressor c;

  public static WPI_TalonSRX frontLeft;
  public static WPI_TalonSRX frontRight;
  public static WPI_TalonSRX backLeft;
  public static WPI_TalonSRX backRight;

  public static SpeedControllerGroup left;
  public static SpeedControllerGroup right;
  public static DifferentialDrive drive;

  public static Encoder encoderRight; 
  public static Encoder encoderLeft; 

  public static PWMLidar lidar;
  public static AHRS navx; 

  public static Vision vision;

  public static PIDDifferentialDrive driveDiff; 
  public static DrivePIDOutput drivePID; 
  public static TwoEncoderPID encoders; 
  public static PIDController driveController;
  public static PIDController counteractDrift; 
  public static PIDController visionController;
  public static PIDController visionDistController;
  public static RotationAdjuster rotationAdjuster;
  public static PIDController rotationController;
  public static SpeedControllerGroup rightDiff; 

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

    frontLeft = new WPI_TalonSRX(9);
    backLeft = new WPI_TalonSRX(10);
    frontRight = new WPI_TalonSRX(2);
    backRight = new WPI_TalonSRX(1);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);
    rightDiff = new SpeedControllerGroup(frontRight, backRight);
    rightDiff.setInverted(true);
    drive = new DifferentialDrive(left,right);

    lidar = new PWMLidar(5);
    navx = new AHRS(Port.kUSB); 
    encoderLeft = new Encoder(8,9, true); // For left encoder it reads -ve values so reverse direction 
    encoderRight = new Encoder(0,1); 
    encoders = new TwoEncoderPID(encoderLeft, encoderRight);   

    rotationAdjuster = new RotationAdjuster();

    driveDiff = new PIDDifferentialDrive(left, rightDiff, rotationAdjuster, false);
    drivePID = new DrivePIDOutput();
    driveController = new PIDController(0.05, 0, 0, encoderLeft, driveDiff);
    driveController.setOutputRange(-.3, .3);
		drive.setDeadband(0.1);
		drive.setSafetyEnabled(false);
    driveController.setAbsoluteTolerance(2);
    
    visionController = new PIDController(0.1, 0, 0, driveDiff);
    visionController.setInputRange(-320, 320);
		visionController.setOutputRange(-.65, .65);
		visionController.setContinuous(true);
    visionController.setAbsoluteTolerance(5);

    visionDistController = new PIDController(0.1, 0, 0, source, driveDiff);
    visionDistController.setInputRange(-320, 320);
		visionDistController.setOutputRange(-.5, .5);
		visionDistController.setContinuous(true);
    visionDistController.setAbsoluteTolerance(5);

    rotationController = new PIDController(0.1, 0, 0, navx, driveDiff);
		rotationController.setInputRange(-180, 180);
		rotationController.setOutputRange(-.65, .65);
		rotationController.setContinuous(true);
    rotationController.setAbsoluteTolerance(2);
    
    counteractDrift = new PIDController(0.08, 0, 0, navx, rotationAdjuster);
		counteractDrift.setInputRange(-180, 180);
		counteractDrift.setOutputRange(-.7, .7);
		counteractDrift.setContinuous(true);
		counteractDrift.setAbsoluteTolerance(1);

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
