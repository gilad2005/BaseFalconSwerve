package frc.lib.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.SwerveModule;

public class SwerveSmartDashboard {
    
    SwerveModule[] mSwerveMods;

    public SwerveSmartDashboard(SwerveModule[] swerveMods){
        mSwerveMods = swerveMods;

        SmartDashboard.putBoolean("zeroDriveEncoders", false);
        SmartDashboard.putBoolean("mesureDriveEncoders", false);
        SmartDashboard.putNumber("distanceDone", 0);

        SmartDashboard.putBoolean("config swerve drive motor PIDF", false);
        SmartDashboard.putNumber("kP", 0);
        SmartDashboard.putNumber("kI", 0);
        SmartDashboard.putNumber("kD", 0);
        SmartDashboard.putNumber("kF", 0);
    }

    public void update(){
        if(SmartDashboard.getBoolean("config swerve drive motor PIDF", false)){
            for(SwerveModule mod : mSwerveMods){
                mod.configDriveMotorPIDF(SmartDashboard.getNumber("kP", 0), SmartDashboard.getNumber("kI", 0), SmartDashboard.getNumber("kD", 0), SmartDashboard.getNumber("kF", 0));
            }
        }

        if(SmartDashboard.getBoolean("zeroDriveEncoders", false)){
            for(SwerveModule mod : mSwerveMods){
                mod.zerosDriveEncoder();
            }
        }

        if(SmartDashboard.getBoolean("mesureDriveEncoders", false)){
            double arr[] = new double[4];
            int i = 0;
            for(SwerveModule mod : mSwerveMods){
                arr[i] = (SmartDashboard.getNumber("distanceDone", 0) * Constants.Swerve.driveGearRatio * 2048) / (mod.getDriveEncoderPosition() * Math.PI);
                i++;
            }
            SmartDashboard.putNumber("dim", (arr[0] + arr[1] + arr[2] + arr[3]) / 4);
        }

        for(SwerveModule mod : mSwerveMods){
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Cancoder", mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);    
        }
    }


}
