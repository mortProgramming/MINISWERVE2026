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

    private Field2d field;

    public Odometry(){
        
         
        field = new Field2d();
    }


    @Override 
    public void periodic(){
        
        robotState = drivetrain.getState();
        this.robotPose = robotState.Pose;

        SmartDashboard.putData("Field",field);
        field.setRobotPose(robotPose);


    }
    
    public void updatePoseLimelight(){
        drivetrain.addVisionMeasurement(vision.getRobotPosition(), 0); 
    }
    

}   
