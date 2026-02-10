package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//helper odometry class that consolodates all of the things odometry/position based
public class Odometry extends SubsystemBase{
    
    private double robotXPos;
    
    private double robotYPos;

    private Rotation2d robotRotation;

    private Translation2d robotTranslation;

    private Pose2d robotPose;

    public Vision vision;

    public Translation2d fakeHub;


    public Odometry(){
        
    }


    @Override 
    public void periodic(){
        SmartDashboard.putNumber("Robot XPos", robotXPos);
        SmartDashboard.putNumber("Robot YPos", robotYPos);
        

    }
    
    public void updatePoseLimelight(){}
    
}
