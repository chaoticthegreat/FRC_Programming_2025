// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.sim;

import static edu.wpi.first.units.Units.Degrees;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public final class SimMechs {

  public static final Mechanism2d mech = new Mechanism2d(5, 5);

  private static final MechanismRoot2d armRoot = mech.getRoot("Arm", 3.5, 0.2);
  private static final MechanismLigament2d armViz =
      armRoot.append(new MechanismLigament2d("Arm", 1, 0.0, 5.0, new Color8Bit(Color.kGreen)));

  public static void updatePivotShooter(Angle angle) {
    armViz.setAngle(angle.in(Degrees));
  }

  public static void init() {
    SmartDashboard.putData("RobotSim", mech);
  }
}
