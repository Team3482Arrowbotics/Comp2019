package frc.robot.commands.VISION;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class AlignToTarget extends Command {
    
    // Called just before this Command runs the first time
    boolean isBalling;
    
    
    public AlignToTarget(boolean isBalling){
        this.isBalling = isBalling;
    }

    @Override
    protected void initialize() {
        //RobotMap.driveDiff.setTurning();
        if(isBalling){
            RobotMap.visionDistController.setSetpoint(0);
        } else {
            RobotMap.visionDistController.setSetpoint(140);
        }
        RobotMap.visionController.enable();
        RobotMap.visionDistController.enable();        
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
        if(RobotMap.visionDistController.onTarget()){
            RobotMap.visionDistController.disable();
            if(RobotMap.visionController.onTarget()){
                RobotMap.visionController.disable();
            }
        }
        System.out.println("Is targetting");
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        System.out.println("Is Over");
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