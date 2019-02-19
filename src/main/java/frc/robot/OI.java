/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static Joystick flightStick;
  public static Joystick logitech;
  public static JoystickButton fK;
  public static JoystickButton tK;
  public static JoystickButton fTK;
  public static JoystickButton eMax;
  public static JoystickButton eMin;

  public static JoystickButton hIn;
  public static JoystickButton hOut;
  public static JoystickButton eDown;

  public static JoystickButton lb;
  public static JoystickButton rb;

  public static Joystick driverOne;
  public static Joystick driverTwo;

  public static JoystickButton o;
  public static JoystickButton t;

  public static JoystickButton s;
  public static JoystickButton n;
  public static JoystickButton x;

  private static final double ticks_per_centimeter = 2000/317.5; //travels 125in in 2000 encoder ticks


  public OI()
  {
    flightStick = new Joystick(0);
    logitech = new Joystick(1);
    driverOne = new Joystick(3);
    driverTwo = new Joystick(4);

    // b.whenPressed(new Move(ticks_per_centimeter*RobotMap.lidar.getDistance()));

    fK = new JoystickButton(driverOne, 1);
    tK = new JoystickButton(driverOne, 4);
    fTK = new JoystickButton(driverOne, 3);
    eMax = new JoystickButton(driverOne, 2);
    eMin = new JoystickButton(driverOne, 8);

    t = new JoystickButton(flightStick, 2);
    o = new JoystickButton(flightStick, 1);
    s = new JoystickButton(flightStick, 3);
    n = new JoystickButton(flightStick, 4);
    eDown = new JoystickButton(flightStick, 5);
    x = new JoystickButton(logitech, 3);

    lb = new JoystickButton(logitech, 5);
    rb = new JoystickButton(logitech, 6);

    hIn = new JoystickButton(flightStick, 11);
    hOut = new JoystickButton(flightStick, 12);

    fK.whileHeld(new FiveK());
    tK.whileHeld(new TenK());
    fTK.whileHeld(new FifteenK());
    eMax.whileHeld(new ElevatorZero());
    eMin.whileHeld(new ElevatorMax());

    s.whileHeld(new ArmUp());
    n.whileHeld(new ArmDown());

    o.whileHeld(new TimedArmDown());
    t.whileHeld(new TimedArmUp());
    eDown.whileHeld(new EmergencyTalonZero());

    lb.whenPressed(new ClawSpintake());
    lb.whenReleased(new TalonZero(RobotMap.armIntake));

    rb.whenPressed(new ClawSpouttake());
    rb.whenReleased(new TalonZero(RobotMap.armIntake));

    hIn.whileHeld(new HatchIn());
    hOut.whileHeld(new HatchOut());
    x.whileHeld(new HatchPlusArm());
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
