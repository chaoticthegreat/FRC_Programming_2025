// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.arm;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.utils.PhoenixUtil;

public class ArmIOTalonFX implements ArmIO {

  private final TalonFX armMotor = new TalonFX(ArmConstants.armMotorId);
  private final PositionVoltage positionRequest =
      new PositionVoltage(0).withSlot(0).withEnableFOC(ArmConstants.kUseFOC);
  private final MotionMagicVoltage motionMagicRequest =
      new MotionMagicVoltage(0).withSlot(0).withEnableFOC(ArmConstants.kUseFOC);
  private final VoltageOut voltageReq = new VoltageOut(0);

  private final StatusSignal<Voltage> armMotorVoltage = armMotor.getMotorVoltage();
  private final StatusSignal<AngularVelocity> armMotorVelocity = armMotor.getVelocity();
  private final StatusSignal<Angle> armMotorPosition = armMotor.getPosition();
  private final StatusSignal<Current> armMotorStatorCurrent = armMotor.getStatorCurrent();
  private final StatusSignal<Current> armMotorSupplyCurrent = armMotor.getSupplyCurrent();

  public ArmIOTalonFX() {
    PhoenixUtil.applyMotorConfigs(
        armMotor, ArmConstants.motorConfigs, ArmConstants.flashConfigRetries);

    BaseStatusSignal.setUpdateFrequencyForAll(
        ArmConstants.updateFrequency,
        armMotorVoltage,
        armMotorVelocity,
        armMotorPosition,
        armMotorStatorCurrent,
        armMotorSupplyCurrent);
    armMotor.optimizeBusUtilization();
  }

  @Override
  public void updateInputs(ArmIOInputs inputs) {
    BaseStatusSignal.refreshAll(
        armMotorVoltage,
        armMotorVelocity,
        armMotorPosition,
        armMotorStatorCurrent,
        armMotorSupplyCurrent);
    inputs.armMotorVoltage = armMotorVoltage.getValueAsDouble();
    inputs.armMotorVelocity = armMotorVelocity.getValueAsDouble();
    inputs.armMotorPosition = armMotorPosition.getValueAsDouble();
    inputs.armMotorStatorCurrent = armMotorStatorCurrent.getValueAsDouble();
    inputs.armMotorSupplyCurrent = armMotorSupplyCurrent.getValueAsDouble();
  }

  @Override
  public void setPosition(double position) {
    if (ArmConstants.kUseMotionMagic) {
      armMotor.setControl(motionMagicRequest.withPosition(position));
    } else {
      armMotor.setControl(positionRequest.withPosition(position));
    }
  }

  @Override
  public void setVoltage(double voltage) {
    armMotor.setVoltage(voltage);
  }

  @Override
  public void off() {
    armMotor.setControl(new NeutralOut());
  }

  @Override
  public void zero() {
    armMotor.setPosition(0);
  }

  @Override
  public TalonFX getMotor() {
    return armMotor;
  }
}
