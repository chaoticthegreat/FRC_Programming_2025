// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.arm;

import com.ctre.phoenix6.hardware.TalonFX;
import org.littletonrobotics.junction.AutoLog;

public interface ArmIO {
  @AutoLog
  public static class ArmIOInputs {
    public double armMotorVoltage = 0.0;
    public double armMotorVelocity = 0.0;
    public double armMotorPosition = 0.0;
    public double armMotorStatorCurrent = 0.0;
    public double armMotorSupplyCurrent = 0.0;
  }

  public default void updateInputs(ArmIOInputs inputs) {}

  public default void setPosition(double position) {}

  public default void setVoltage(double voltage) {}

  public default TalonFX getMotor() {
    return new TalonFX(0);
  }

  public default void off() {}

  public default void zero() {}
}
