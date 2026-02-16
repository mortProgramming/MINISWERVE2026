package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Turret;

public class SpinTurret extends Command{
    
    private static Turret turret;
    private double speed;
    private CommandXboxController controller;

    public SpinTurret(double speed){
        this.speed = speed;

        turret = Turret.getInstance();
        addRequirements(turret);
    }

    public SpinTurret(double speed, CommandXboxController controller){
        this.speed = speed;
        this.controller = controller;

        turret = Turret.getInstance();
        addRequirements(turret);
    }
    
    @Override
    public void initialize(){
    }
    
    @Override
    public void execute(){
        turret.rotateTurret(speed);
    }

    @Override 
    public void end(boolean interrupted){
        turret.rotateTurret(0);
    }
    
    @Override
    public boolean isFinished(){
        return false;
    }
}
