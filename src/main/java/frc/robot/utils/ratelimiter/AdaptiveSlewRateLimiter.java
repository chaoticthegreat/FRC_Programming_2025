// Copyright (c) 2025 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by a 
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.utils.ratelimiter;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.WPIUtilJNI;

public class AdaptiveSlewRateLimiter {
  private final double accelRateLimit;
  private final double decelRateLimit;
  private double prevVal;
  private double prevTime;

  public AdaptiveSlewRateLimiter(double accelRateLimit, double decelRateLimit) {
    this.accelRateLimit = Math.abs(accelRateLimit);
    this.decelRateLimit = Math.abs(decelRateLimit);

    prevVal = 0;
    prevTime = WPIUtilJNI.now() * 1e-6;
  }

  /**
   * Filters the input to limit its slew rate.
   *
   * @param input
   * @return
   */
  public double calculate(double input) {
    double currentTime = WPIUtilJNI.now() * 1e-6;
    double elapsedTime = currentTime - this.prevTime;

    double currRateLimit = (Math.abs(input) > Math.abs(prevVal) ? accelRateLimit : decelRateLimit);

    prevVal +=
        MathUtil.clamp(input - prevVal, -currRateLimit * elapsedTime, currRateLimit * elapsedTime);

    prevTime = currentTime;

    return prevVal;
  }

  public void reset(double value) {
    prevVal = value;
    prevTime = WPIUtilJNI.now() * 1e-6;
  }
}
