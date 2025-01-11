package frc.robot.subsystems.rollers;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.utils.PhoenixUtil;

public class RollerSubsystemIOTalonFX implements RollerSubsytemIO {

    final TalonFX rollerMotor = new TalonFX(RollerSubsystemConstants.kRollerMotorID);
    // establishing control requests for INTAKE MOTOR, with desired output and pid
    // slot parameters
    final VelocityVoltage velocityRequestIntake = new VelocityVoltage(0).withSlot(0);
    final MotionMagicVelocityVoltage motionMagicRequestIntake = new MotionMagicVelocityVoltage(0).withSlot(0);
    final VoltageOut voltageRequestIntake = new VoltageOut(0);

  private final StatusSignal<Current> rollerMotorSupplyCurrent = rollerMotor.getSupplyCurrent();
    private final StatusSignal<Voltage> rollerMotorVoltage = rollerMotor.getMotorVoltage();
    private final StatusSignal<AngularVelocity> rollerMotorVelocity = rollerMotor.getVelocity();
    private final StatusSignal<Temperature> rollerMotorTemperature = rollerMotor.getDeviceTemp();

    public RollerSubsystemIOTalonFX() {
        var rollerMotorConfig = RollerSubsystemConstants.rollerMotorConfig;
        PhoenixUtil.applyMotorConfigs(rollerMotor, RollerSubsystemConstants.rollerMotorConfig, 3);
        // TalonUtil.applyAndCheckConfiguration(rollerMotor,rollerMotorConfig);
    }

    @Override
    public void supplyIntakeVoltage(double voltage) {
        rollerMotor.setVoltage(voltage);
    }

    @Override
    public void setIntakeVelocity(double velocity) {
        rollerMotor.setControl(velocityRequestIntake.withVelocity(velocity));
    }

    @Override
    public void turnOff() {
        rollerMotor.setControl(new NeutralOut());
    }

    @Override
    public TalonFX getRollerMotor() {
        return rollerMotor;
    }

    @Override
    public void updateInputs(RollerInputs inputs) {
        BaseStatusSignal.refreshAll(
                rollerMotorVoltage,
                rollerMotorVelocity,
                rollerMotorSupplyCurrent,
                rollerMotorTemperature)

        ;
        inputs.rollerMotorVoltage = rollerMotorVoltage.getValueAsDouble();
        inputs.rollerMotorVelocity = rollerMotorVelocity.getValueAsDouble();

    }
}

