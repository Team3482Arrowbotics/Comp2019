/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ARM.*;
import frc.robot.commands.ELEVATOR.*;
import frc.robot.commands.HATCH.*;
import frc.robot.commands.*;
import frc.robot.commands.CommandGroups.Subsystems.*;
import frc.robot.commands.CommandGroups.Vision.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static Joystick flightStick;
  public static Joystick logitech;

  public static JoystickButton hIn;
  public static JoystickButton hOut;
  public static JoystickButton eDown;

  public static JoystickButton lb;
  public static JoystickButton rb;

  public static Joystick driverOne;
  public static Joystick driverTwo;

  public static JoystickButton o;
  public static JoystickButton t;

  public static JoystickButton x;
  public static JoystickButton a; 
  public static JoystickButton b;
  public static JoystickButton y;

  public static JoystickButton f8;
  public static JoystickButton f9;
  public static JoystickButton f7;
  public static JoystickButton f10;

  public static JoystickButton r1;
  public static JoystickButton r2;
  public static JoystickButton r3;
  public static JoystickButton r4;
  public static JoystickButton b1;
  public static JoystickButton b2;
  public static JoystickButton b3;
  public static JoystickButton b4;
  public static JoystickButton w1;
  public static JoystickButton w2;
  public static JoystickButton blue1;
  public static JoystickButton blue2;
  
  private static final double ticks_per_centimeter = 2000/317.5; //travels 125in in 2000 encoder ticks


  public OI()
  {
    flightStick = new Joystick(0);
    logitech = new Joystick(1);
    driverOne = new Joystick(3);
    driverTwo = new Joystick(4);

    r1 = new JoystickButton(driverTwo, 8);
    r2 = new JoystickButton(driverTwo, 7);
    r3 = new JoystickButton(driverTwo, 6);
    r4 = new JoystickButton(driverTwo, 5);
    b1 = new JoystickButton(driverOne, 1);
    b2 = new JoystickButton(driverOne, 4);
    b3 = new JoystickButton(driverOne, 3);
    b4 = new JoystickButton(driverOne, 2);
    w1 = new JoystickButton(driverOne, 8);
    w2 = new JoystickButton(driverOne, 5);
    blue1 = new JoystickButton(driverOne, 12);
    blue2 = new JoystickButton(driverOne, 9);


    f8 = new JoystickButton(flightStick, 8);
    f7 = new JoystickButton(flightStick, 7);
    f9 = new JoystickButton(flightStick, 9);
    f10 = new JoystickButton(flightStick, 10);

    // b.whenPressed(new Move(ticks_per_centimeter*RobotMap.lidar.getDistance()));

    a = new JoystickButton(logitech, 1); 
    b = new JoystickButton(logitech, 2);

    t = new JoystickButton(flightStick, 2);
    o = new JoystickButton(flightStick, 1);
   
    eDown = new JoystickButton(flightStick, 5);
    x = new JoystickButton(logitech, 3);
    y = new JoystickButton(logitech, 4);

    lb = new JoystickButton(logitech, 5);
    rb = new JoystickButton(logitech, 6);

    hIn = new JoystickButton(flightStick, 11);
    hOut = new JoystickButton(flightStick, 12);

    //a.whenPressed(new Move(2000)); 
    //a.whileHeld(new ArmMotion(0, RobotMap.armTurn));
    //b.whileHeld(new ArmMotion(0, RobotMap.armTurn));
    /*if(RobotMap.hatch.get() == Value.kForward)
    {
      x.whileHeld(new HatchIn());
    }
    else
    {
      x.whileHeld(new HatchOut());
    }

    if(RobotMap.fArm.get() == Value.kForward)
    {
      y.whileHeld(new ArmUp());
    }
    else
    {
      y.whileHeld(new ArmDown());
    }*/

    o.whileHeld(new TimedArmDown());
    t.whileHeld(new TimedArmUp());
    eDown.whileHeld(new EmergencyTalonZero());

    lb.whenPressed(new ClawSpintake());
    lb.whenReleased(new TalonZero(RobotMap.armIntake));

    rb.whenPressed(new ClawSpouttake());
    rb.whenReleased(new TalonZero(RobotMap.armIntake));

    hIn.whileHeld(new HatchIn());
    hOut.whileHeld(new HatchOut());

    /*r1.whileHeld(new HatchIntake());
    //r2.whileHeld(new HatchOuttakeOne());
    r3.whileHeld(new ElevatorMove(110500));
    r4.whileHeld(new ElevatorMove(180000));
    b1.whileHeld(new BallIntake());
    b2.whileHeld(new BallOuttakeOne());
    b3.whileHeld(new BallOuttakeTwo());
    b4.whileHeld(new BallOuttakeThree());
    w1.whileHeld(new ArmMotion(0,RobotMap.armTurn));
    w2.whileHeld(new ElevatorZero());
    blue1.whileHeld(new HatchIn());
    blue2.whileHeld(new HatchOut());*/
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by its isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by its isFinished method.
  // button.whenReleased(new ExampleCommand());
}
