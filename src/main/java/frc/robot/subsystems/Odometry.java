package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.swerve.SwerveDrivetrain.SwerveDriveState;

//helper odometry class that consolodates all of the things odometry/position based
public class Odometry extends SubsystemBase{

    private SwerveDriveState robotState; //this object holds all things about the state of the drivetrain

    private Vision vision;

    private Translation2d fakeHub;

    private CommandSwerveDrivetrain drivetrain;
    
    private Pose2d robotPose;
    private Translation2d robotTranslation; //this translation is for calculating the distan
    private double xComponentOfTranslation;
    private Field2d field;

    private double distance;
    public double latency = LimelightHelpers.getLatency_Pipeline("") + LimelightHelpers.getLatency_Capture("");
    
               


    public Odometry(){
        field = new Field2d();
        
    }


    @Override
    public void periodic(){
    robotState = drivetrain.getState();
    robotPose = robotState.Pose;

    if (vision.hasTag()) {
        Pose2d visionPose = vision.getRobotPosition();

        double timestamp = edu.wpi.first.wpilibj.Timer.getFPGATimestamp()
        - (latency / 1000.0);

        drivetrain.addVisionMeasurement(visionPose, timestamp);

        SmartDashboard.putNumber("Vision X", visionPose.getX());
        SmartDashboard.putNumber("Vision Y", visionPose.getY());
    }

    SmartDashboard.putNumber("Robot X pos", robotPose.getX());
    SmartDashboard.putNumber("Robot Y Pos", robotPose.getY());
    }

    
    // public void updatePoseLimelight(){
    //     drivetrain.addVisionMeasurement(vision.getRobotPosition(), 0); 
    // }

    public double getHypToHub(){
        robotTranslation = robotState.Pose.getTranslation();
        return robotTranslation.getDistance(fakeHub);
    }

    // public double getXDistanceHub(){
    //      xComponentOfTranslation = robotState.Pose.getTranslation().getX();
    //      return xComponentOfTranslation make this do just x component of robot to the hub
    // }
    

}   
