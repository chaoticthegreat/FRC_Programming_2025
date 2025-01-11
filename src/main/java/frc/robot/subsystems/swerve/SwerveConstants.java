// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;

public final class SwerveConstants {

  public static final Transform2d robotToQuest =
      new Transform2d(new Translation2d(-0.026 / 2, 0.667 / 2), Rotation2d.fromDegrees(180));
  public static final double wheelRadiusMaxVelocity = 0.25; // Rad/Sec
  public static final double wheelRadiusMaxRampRate = 0.05; // Rad/Sec^2

  public static final Rotation2d sourceLeft1 = new Rotation2d(125.989);
  // source 1
  public static final Rotation2d sourceRight2 = new Rotation2d(-sourceLeft1.getDegrees());
  // source2
  public static final Rotation2d hang = new Rotation2d(180);
}
