package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

public class SpinTurret extends Command{
    
    private static Turret turret;
    private double speed;

    public SpinTurret(double speed){
        this.speed = speed;

        turret = Turret.getInstance();
        addRequirements(turret);
    }
    
    @Override
    public void initialize(){}
    
    @Override
    public void execute(){}

    @Override 
    public void end(boolean interrupted){}
    
    @Override
    public boolean isFinished(){
        return false;
    }
}
