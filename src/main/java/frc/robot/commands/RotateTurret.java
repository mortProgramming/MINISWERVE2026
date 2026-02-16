package frc.robot.commands;

import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.Command;

public class RotateTurret extends Command{
    

    private SparkClosedLoopController pidLoop;

    private SimpleMotorFeedforward feedforward;

    

    public RotateTurret(){
        
    }

    
}
