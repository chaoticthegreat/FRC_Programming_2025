// Copyright (c) 2024 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.arm;

import com.ctre.phoenix6.sim.ChassisReference;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.sim.SimMechs;
import org.littletonrobotics.junction.LoggedRobot;

public class ArmIOSim extends ArmIOTalonFX {

    private final SingleJointedArmSim armSimModel =
            new SingleJointedArmSim(
                    DCMotor.getKrakenX60(1),
                    ArmConstants.sim.simGearing,
                    ArmConstants.sim.jkGMetersSquared,
                    ArmConstants.sim.armLength,
                    ArmConstants.sim.minAngle.getRadians(),
                    ArmConstants.sim.maxAngle.getRadians(),
                    true,
                    ArmConstants.sim.startingAngle.getRadians());

    private TalonFXSimState armSimState;

    public ArmIOSim() {
        super();
        armSimState = super.getMotor().getSimState();
        armSimState.Orientation = ChassisReference.Clockwise_Positive;
    }

    @Override
    public void updateInputs(ArmIOInputs inputs) {

        armSimState = super.getMotor().getSimState();
        armSimState.setSupplyVoltage(RobotController.getBatteryVoltage());
        armSimModel.setInputVoltage(armSimState.getMotorVoltage());
        armSimModel.update(LoggedRobot.defaultPeriodSecs);
        armSimState.setRawRotorPosition(
                Units.radiansToRotations(armSimModel.getAngleRads())
                        * ArmConstants.sim.simGearing);
        armSimState.setRotorVelocity(
                Units.radiansToRotations(armSimModel.getVelocityRadPerSec())
                        * ArmConstants.sim.simGearing);
        RoboRioSim.setVInVoltage(
                BatterySim.calculateDefaultBatteryLoadedVoltage(armSimModel.getCurrentDrawAmps()));
        super.updateInputs(inputs);
        SimMechs.updateArm(Rotation2d.fromRadians(armSimModel.getAngleRads()));
    }
}