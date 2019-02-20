package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDOutput;

public class DrivePIDOutput implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		Robot.linDrive = output;
		// TODO Auto-generated method stub
		
	}

}