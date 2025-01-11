package frc.robot.subsystems.rollers;

import org.littletonrobotics.junction.AutoLog;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

public interface RollerSubsytemIO {
@AutoLog // useful for telementary, debugging
  public static class RollerInputs {

    public double rollerMOtorTemperature = 0.0;
    public double rollerMotorVoltage = 0.0;
    public double rollerMotorVelocity = 0.0;
    public double rollerMotorStatorCurrent = 0.0;
    public double rollerMotorTotalCurrent = 0.0;

  }

  public default void updateInputs(RollerInputs inputs) {}

  public default void supplyIntakeVoltage(double voltage) {}

  public default void setIntakeVelocity(double velocity) {}


  // returns motor controller objects
  public default TalonFX getRollerMotor() {
    return new TalonFX(0);
  }


  // returns voltage request objects
  public default VoltageOut getRollerVoltageRequest() {
    return new VoltageOut(0);
  }


  // powers off all motors
  public default void turnOff() {}
}

    

    
    

