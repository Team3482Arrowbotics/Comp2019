package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HatchPlusArm extends CommandGroup
{
    public HatchPlusArm()
    {
        addSequential(new TimedArmDown());
        addSequential(new HatchIn());
    }
}