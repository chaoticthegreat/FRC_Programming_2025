// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;

public interface ElevatorConstants {
  public static final int kMotorID = 0;
  public static final TalonFXConfiguration kMotorConfig = new TalonFXConfiguration();
  public static final boolean kUseMotionMagic = true;
  public static final double kStatusSignalUpdateFrequency = 50.0; // Hz

  public static final boolean kUseFOC = true;
  public static final int kFlashConfigRetries = 5;

  // Coral positions
  // Please tune
  public static final Angle[] kReefPositions = {
    Rotations.of(0.0), Rotations.of(0.0), Rotations.of(0.0), Rotations.of(0.0)
  };

  public static class SimulationConstants {
    // TODO: this is wrong
    private static double pivotToGroundAtStowInches = 7.7;
    // Elevator extension length
    public static final Distance[] kReefPositions = {
      Inches.of(18 - 7.07 - pivotToGroundAtStowInches),
      Inches.of(47.625 - 3 - pivotToGroundAtStowInches),
      Inches.of(31.875 - 3 - pivotToGroundAtStowInches),
      Inches.of(72 - 7.07 - pivotToGroundAtStowInches)
    };
    public static final double kGearRatio = 1.0;
    public static final Distance kWheelRadius = Inches.of(1.0);
  }
}
