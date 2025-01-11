package frc.robot.subsystems.rollers;

import com.ctre.phoenix6.SignalLogger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
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
  }}


  