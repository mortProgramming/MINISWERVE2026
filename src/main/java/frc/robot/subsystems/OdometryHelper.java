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
<<<<<<< HEAD:src/main/java/frc/robot/subsystems/Odometry.java
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
=======
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
>>>>>>> 747d5c6606341e7dd4756db7d42db0edba10cda7:src/main/java/frc/robot/subsystems/OdometryHelper.java
    }

    SmartDashboard.putNumber("Robot X pos", robotPose.getX());
    SmartDashboard.putNumber("Robot Y Pos", robotPose.getY());
    }

    
<<<<<<< HEAD:src/main/java/frc/robot/subsystems/Odometry.java
    // public void updatePoseLimelight(){
    //     drivetrain.addVisionMeasurement(vision.getRobotPosition(), 0); 
    // }
=======
    public void updatePoseLimelight(){
        drivetrain.addVisionMeasurement(vision.getRelativeRobotPosition(), 0); 
    }
>>>>>>> 747d5c6606341e7dd4756db7d42db0edba10cda7:src/main/java/frc/robot/subsystems/OdometryHelper.java

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
