// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.rollers;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.DisableSubsystem;

public class RollerSubsystem extends DisableSubsystem {

  private final RollerSubsytemIO rollerIO;

  public RollerSubsystem(boolean disabled, RollerSubsytemIO rollerIO) {
    super(disabled);
    this.rollerIO = rollerIO;
  }

  public Command rollerIn() {
    return this.run(
        () -> {
          rollerIO.setIntakeVelocity(0);
        });
  }

  public Command setVoltage(double voltage, double passthroughVoltage) {
    return this.run(
            () -> {
              rollerIO.supplyIntakeVoltage(voltage);
            })
        .finallyDo(rollerIO::turnOff);
  }

  public Command setRollerVoltage(double voltage) {
    return this.run(() -> rollerIO.supplyIntakeVoltage(voltage)).finallyDo(rollerIO::turnOff);
  }
}
