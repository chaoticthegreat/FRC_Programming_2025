// Copyright (c) 2024 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.SignalLogger;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.subsystems.vision.Vision;
import frc.robot.utils.DisableSubsystem;
import org.littletonrobotics.junction.Logger;

public class Arm extends DisableSubsystem {

    private final ArmIO armIO;
    private final ArmIOInputsAutoLogged armIOAutoLogged =
            new ArmIOInputsAutoLogged();

    private final InterpolatingDoubleTreeMap aprilTagMap =
            new InterpolatingDoubleTreeMap() {
                {
                    put(0.0, 0.0);
                    put(1.0, 1.0);
                }
            };

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