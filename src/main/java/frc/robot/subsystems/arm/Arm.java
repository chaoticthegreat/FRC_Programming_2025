// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.DisableSubsystem;
import org.littletonrobotics.junction.Logger;

public class Arm extends DisableSubsystem {

  private final ArmIO armIO;
  private final ArmIOInputsAutoLogged armIOAutoLogged = new ArmIOInputsAutoLogged();

  public Arm(boolean disabled, ArmIO armIO) {
    super(disabled);

    this.armIO = armIO;
  }

  @Override
  public void periodic() {
    super.periodic();
    armIO.updateInputs(armIOAutoLogged);
    Logger.processInputs(this.getClass().getSimpleName(), armIOAutoLogged);
  }

  public Command setPosition(double position) {
    return this.run(() -> armIO.setPosition(position));
  }

  public Command setVoltage(double voltage) {
    return this.run(() -> armIO.setVoltage(voltage)).finallyDo(armIO::off);
  }

  public Command off() {

    return this.runOnce(armIO::off);
  }
}
