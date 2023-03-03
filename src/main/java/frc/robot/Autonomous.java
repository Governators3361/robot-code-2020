package frc.robot;

import edu.wpi.first.wpilibj.Timer;

class Autonomous extends Robot {

  boolean autoShot = false;
  int count = 0;

  /**
   * This function is called at the beginning of autonomous.
   */
  @Override
  public void autonomousInit() {
    setMotors(-.3, -.3);
    Timer.delay(1);

    setMotors(-.1, -.1);
    Timer.delay(.2);

    setMotors(0, 0);

    while (!limelight.targetVisible()) {
      setMotors(-0.1, 0.1);
    }
    setMotors(0, 0);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    count++;
    if (count >= (int) (5 / .02)) {
      System.out.println("Autonomous Routine Finished");
      return;
    }

    if (autoShot) {
      return;
    }
    autoShoot();
  }

  public void autoShoot() {
    // limelight.aimAtTarget();
    // limelight.aimMove();
    shootBall();
    autoShot = true;
  }
}