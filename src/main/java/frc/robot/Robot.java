/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ArmVator;
import frc.robot.subsystems.Ev;
import frc.robot.subsystems.ExampleSubsystem;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory; 
import jaci.pathfinder.followers.EncoderFollower;


import edu.wpi.first.wpilibj.Notifier;
import frc.robot.commands.Move;
import frc.robot.commands.Turn;
import frc.robot.commands.HATCH.HatchIn;
import frc.robot.commands.HATCH.HatchOut;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  public static boolean driveEnabled;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  public SendableChooser<String> pathChooser; 
  public SendableChooser<String> hatchOrCargo; 

  public static boolean isEMovingUp, isEMovingDown;

  public static NetworkTableEntry centerX;
  public static NetworkTableEntry xDist;
  public static NetworkTableEntry distance;
  public static NetworkTableEntry angle;
  public static NetworkTableEntry isLeft;
  public static NetworkTableEntry visionCam;

  private EncoderFollower l_enc_follower; 
  private EncoderFollower r_enc_follower; 

  private static final int k_ticks_per_rev = 360; 

  private static final double k_wheel_diameter = 0.19431; //7.6 inches in meters; 
  private static final double k_max_velocity = 0.25; 
  //left to right wheel space 0.5715 m; 
  private static String pathname = "";
  private static final String pathnamee = "LeftTurn";

  private Notifier follower_notifier; 
  private int state; 
  private boolean hatch; 

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    RobotMap.init();
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    isEMovingUp = false;
    isEMovingDown = false;
    RobotMap.c.start();

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable vision = inst.getTable("Vision");
    centerX = vision.getEntry("centerX");
    xDist = vision.getEntry("distX");
    distance = vision.getEntry("distance");
    angle = vision.getEntry("angle");
    isLeft = vision.getEntry("isLeft");
    visionCam = vision.getEntry("visionCam");

    driveEnabled = true; 
    pathChooser = new SendableChooser<String>(); 
    hatchOrCargo = new SendableChooser<String>(); 

    pathChooser.setDefaultOption("Default", "????");
    pathChooser.addOption("LtoFrontRight", "LtoFrontRight");
    pathChooser.addOption("RtoFrontRight", "RtoFrontRight");
    pathChooser.addOption("MtoFrontRight", "MtoFrontRight");
    pathChooser.addOption("LtoFrontLeft", "LtoFrontLeft");
    pathChooser.addOption("RtoFrontLeft", "RtoFrontLeft");
    pathChooser.addOption("MtoFrontLeft", "MtoFrontLeft");
    pathChooser.addOption("LtoFrontRocket", "LtoFrontRocket");
    pathChooser.addOption("BacktoRocket", "BackToRocket");
    pathChooser.addOption("Leftturn", "LeftTurn");
    pathChooser.addOption("Straight", "newre");

    hatchOrCargo.setDefaultOption("Hatch", "Hatch");
    hatchOrCargo.addOption("Cargo", "Cargo");

    SmartDashboard.putData("Path Chooser", pathChooser); 
    SmartDashboard.putData("Hatch or Cargo", hatchOrCargo);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Lidar", RobotMap.lidar.getDistance());
    SmartDashboard.putData("Navx", RobotMap.navx);
    SmartDashboard.putNumber("Left Encoder",  RobotMap.encoderLeft.get());
    SmartDashboard.putNumber("Right Encoder",  RobotMap.encoderRight.get());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    pathname = pathChooser.getSelected(); 
    SmartDashboard.putString("Selected Path", pathname);

    /*if(hatchOrCargo.getSelected().equals("Hatch"))
    {
      hatch = true;
    }
    else
    {
      hatch = false; 
    }*/
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
    RobotMap.navx.reset(); 
    RobotMap.encoderLeft.reset();
    RobotMap.encoderRight.reset();     

    Trajectory left_trajectory = PathfinderFRC.getTrajectory(pathname + ".left");
    Trajectory right_trajectory = PathfinderFRC.getTrajectory(pathname+ ".right");

    l_enc_follower = new EncoderFollower(left_trajectory);
    r_enc_follower = new EncoderFollower(right_trajectory);
    l_enc_follower.configureEncoder(RobotMap.encoderLeft.get(), k_ticks_per_rev, k_wheel_diameter);
    l_enc_follower.configurePIDVA(0.8, 0.0, 0.0, 1 / k_max_velocity, 0);
    r_enc_follower.configureEncoder(RobotMap.encoderRight.get(), k_ticks_per_rev, k_wheel_diameter);
    r_enc_follower.configurePIDVA(0.8, 0.0, 0.0, 1 / k_max_velocity, 0);
    follower_notifier = new Notifier(this::followPath);
    follower_notifier.startPeriodic(left_trajectory.get(0).dt);
    //intake 
    //new path 
  }

  private void followPath() {
    if (l_enc_follower.isFinished() || r_enc_follower.isFinished()) {
      follower_notifier.stop();
      RobotMap.left.set(0); 
      RobotMap.right.set(0); 
      System.out.println("Stop Notifier");
    } else {
      double left_speed = l_enc_follower.calculate(RobotMap.encoderLeft.get());
      double right_speed = r_enc_follower.calculate(RobotMap.encoderRight.get());
      double heading = RobotMap.navx.getAngle();
      double desired_heading = Pathfinder.r2d(l_enc_follower.getHeading());
      //check to see if + or - heading difference 
      double heading_difference = Pathfinder.boundHalfDegrees(desired_heading + heading);
      double turn =  0.8 * (-1.0/80.0) * heading_difference;
      RobotMap.left.set(0.6*(left_speed + turn));
      RobotMap.right.set(0.6*(right_speed - turn));
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
/*
    if(hatch)
    {
      if(state == 1) //start path 1
      {
        Trajectory left_trajectory = PathfinderFRC.getTrajectory(pathname + ".right");
        Trajectory right_trajectory = PathfinderFRC.getTrajectory(pathname+ ".left");
    
        l_enc_follower = new EncoderFollower(left_trajectory);
        r_enc_follower = new EncoderFollower(right_trajectory);
        l_enc_follower.configureEncoder(RobotMap.encoderLeft.get(), k_ticks_per_rev, k_wheel_diameter);
        l_enc_follower.configurePIDVA(0.8, 0.0, 0.0, 1 / k_max_velocity, 0);
        r_enc_follower.configureEncoder(RobotMap.encoderLeft.get(), k_ticks_per_rev, k_wheel_diameter);
        r_enc_follower.configurePIDVA(0.8, 0.0, 0.0, 1 / k_max_velocity, 0);
        follower_notifier = new Notifier(this::followPath);
        follower_notifier.startPeriodic(left_trajectory.get(0).dt);

        state = 2; 
      }
      else if(state ==2) //start vision
      {

        state = 3; 
      }
      else if(state ==3) //hatch outtake 
      {
        new HatchOut(); 

        state = 4; 
      }
      else if (state ==4) //back up and turn around
      {
        new Move(-500); 
        new Turn(180);
        state = 5; 
      }
      else if(state ==5) //start path 2
      {
        if(pathname.equals("LtoRocket")||pathname.equals("MtoRocket")||pathname.equals("RtoRocket"))
        {
          Trajectory left_trajectory = PathfinderFRC.getTrajectory("RocketBack" + ".right");
          Trajectory right_trajectory = PathfinderFRC.getTrajectory("RocketBack"+ ".left");
      
          l_enc_follower = new EncoderFollower(left_trajectory);
          r_enc_follower = new EncoderFollower(right_trajectory);
          l_enc_follower.configureEncoder(RobotMap.encoderLeft.get(), k_ticks_per_rev, k_wheel_diameter);
          l_enc_follower.configurePIDVA(0.8, 0.0, 0.0, 1 / k_max_velocity, 0);
          r_enc_follower.configureEncoder(RobotMap.encoderLeft.get(), k_ticks_per_rev, k_wheel_diameter);
          r_enc_follower.configurePIDVA(0.8, 0.0, 0.0, 1 / k_max_velocity, 0);
          follower_notifier = new Notifier(this::followPath);
          follower_notifier.startPeriodic(left_trajectory.get(0).dt);
        }
        state = 6; 
      }
      else if(state == 6) //hatch intake
      {
        new HatchIn(); 
      }
    }*/
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("EPOS", RobotMap.elevatorTalonOne.getSelectedSensorPosition());
    // oi answer
    Ev.run();
    SmartDashboard.putNumber("APOS", RobotMap.armTurn.getSelectedSensorPosition());
    
    double speed = OI.logitech.getRawAxis(1);
    double turnSpeed = OI.logitech.getRawAxis(4);

    if (driveEnabled) {
			RobotMap.drive.arcadeDrive(turnSpeed, -speed);
		}
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
