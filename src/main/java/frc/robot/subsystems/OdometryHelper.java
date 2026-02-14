package frc.robot.subsystems;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.configs.constants.TunerConstants;

import com.ctre.phoenix6.swerve.SwerveDrivetrain.SwerveDriveState;

//helper odometry class that consolodates all of the things odometry/position based
public class OdometryHelper extends SubsystemBase{

    private static OdometryHelper odometryInstance;

    private SwerveDriveState robotState; //this object holds all things about the state of the drivetrain

    private Vision vision;

    private Translation2d fakeHub;

    private CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
    
    private Pose2d robotPose;
    private Translation2d robotTranslation; //this translation is for calculating the distan
    private Field2d field;

    // private double currentTime = Timer.getFPGATimestamp();
    // private double latency = (result.getLatencyCapture() + result.getLatencyPipeline()) / 1000.0;
    // private double timestamp = currentTime - latency;


    public OdometryHelper(){
        vision = Vision.getInstance();
        field = new Field2d();
        fakeHub = new Translation2d(2, 2);
        robotState = drivetrain.getState();
    }


    @Override 
    public void periodic(){ 
        robotState = drivetrain.getState();
        this.robotPose = robotState.Pose;
        this.robotTranslation = robotState.Pose.getTranslation();      
        SmartDashboard.putData("Field",field);
        SmartDashboard.putNumber("onmewrf",1245);
        SmartDashboard.putNumber("Robot X pos",robotState.Pose.getX());
        SmartDashboard.putNumber("Robot Y Pos",robotState.Pose.getY());
        SmartDashboard.putNumber("Robot Rotation",robotState.Pose.getRotation().getDegrees());
        SmartDashboard.putNumber("Distance to hub",getHypToHub());
        
        //stuff below here is for troubleshooting
        // SmartDashboard.putNumber("Vision XPose",);
        SmartDashboard.putNumber("RobotX from Tag Robot position",vision.getRobotPosition().getX());
        SmartDashboard.putNumber("RobotX from Tag RobotRelativeposition",vision.getRelativeRobotPosition().getX());

        
        field.setRobotPose(robotPose);  
    }
    
    public void updatePoseLimelight(){
        drivetrain.addVisionMeasurement(vision.getRelativeRobotPosition(), 0); 
    }

    public double getHypToHub(){
        robotTranslation = robotState.Pose.getTranslation();
        return robotTranslation.getDistance(fakeHub);
    }

    // public double getXDistanceHub(){
    //      xComponentOfTranslation = robotState.Pose.getTranslation().getX();
    //      return xComponentOfTranslation make this do just x component of robot to the hub
    // }

    public static OdometryHelper getInstance() {
        if (odometryInstance == null) {
            odometryInstance = new OdometryHelper();
        }
        return odometryInstance;
    }


    

}   
