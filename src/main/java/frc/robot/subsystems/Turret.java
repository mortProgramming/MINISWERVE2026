//TODO fix naming for PID method and add odometry theta calculations with distance formula
//TODO add feedforward to turret and figure out how to use it with PID
package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
// import com.revrobotics.spark.SparkBase.PersistMode;
// import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.ClosedLoopSlot;

// import static frc.robot.Constants.PidConstants.*;  commented out for now make later

public class Turret extends SubsystemBase{
    
    private SparkMax motor;
    
    private SparkMaxConfig config;
    
    private double speed;

    private ProfiledPIDController profiledPIDController;
    
    private SparkClosedLoopController sparkClosedLoopController; 

    private SimpleMotorFeedforward simpleMotorFeedforward; 

    private static Turret turret;

    public Turret(){
        motor = new SparkMax(14,MotorType.kBrushless);
        
        config = new SparkMaxConfig();
        // config.closedLoop.p(KP).i(KI).d(KD).outputRange(kMinOutput, kMaxOutput); //figure out where to put kMinOutput and kMaxOutput and what vals should be
        
        speed = 0;

        simpleMotorFeedforward = null; 
        sparkClosedLoopController = motor.getClosedLoopController();   
        // profiledPIDController = new ProfiledPIDController(KP,KI,KD, null);
        PersistMode persistMode = PersistMode.kNoPersistParameters;
        
        ResetMode resetMode = ResetMode.kNoResetSafeParameters;
        motor.configure(config, resetMode, persistMode);

    }
    
    
    @Override
    public void periodic(){
        SmartDashboard.putNumber("Turrent Angle", getEncoderPosition());
    }


    public void setSetPoint(){
        // sparkClosedLoopController.setSetpoint(setPoint, ControlType.kPosition); figure out why isnt fully working (revlib 2026 vender? or other thing)
        }

    public double getEncoderPosition(){
        return motor.getEncoder().getPosition();
    }

    public void rotateTurret(double speed){
        motor.set(speed);
    }

    public void rotateToPosition(){
        //todo use PIDS to rotate to a certain angle based on pose calculation and theta
    }

    public static Turret getInstance() {
		if (turret == null) {
			turret = new Turret();
		}
		return turret;
	}
}
