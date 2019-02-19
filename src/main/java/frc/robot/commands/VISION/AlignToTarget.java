package frc.robot.commands.VISION;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class AlignToTarget extends Command {
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        RobotMap.visionController.enable();
        RobotMap.visionDistController.enable();        
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
        if(RobotMap.visionDistController.onTarget()){
            RobotMap.visionDistController.disable();
            if(RobotMap.visionDistController.onTarget()){
                RobotMap.visionDistController.disable();
            }
        }
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return !RobotMap.visionController.isEnabled();
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