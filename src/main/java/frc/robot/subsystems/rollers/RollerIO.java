// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.rollers;

import org.littletonrobotics.junction.AutoLog;

public interface RollerIO {
  @AutoLog // useful for telementary, debugging
  public static class RollerIOInputs {

    public double rollerMotorVoltage = 0.0;
    public double rollerMotorVelocity = 0.0;
    public double rollerMotorStatorCurrent = 0.0;
  }

  public default void updateInputs(RollerIOInputs inputs) {}

  public default void setVoltage(double voltage) {}

  public default void off() {}
}
