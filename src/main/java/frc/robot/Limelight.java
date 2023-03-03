package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    boolean ledStatus = true, driveCamMode = false;
    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    public Limelight() {
        
    }

    /**
     * @return Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
     */
    public double getX() {
        return table.getEntry("tx").getDouble(0.0);
    }

    /**
     * @return Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
     */
    public double getY() {
        return table.getEntry("ty").getDouble(0.0);
    }

    /**
     * 
     * @return 	Whether the limelight has any valid targets
     */
    public boolean targetVisible() {
        return (table.getEntry("tv").getDouble(0) == 1);
    }

    /**
     * Toggles limelight’s operation mode (Vision Processor/Driver Camera)
     */
    public void toggleCamMode() {
        if (driveCamMode)
            table.getEntry("camMode").setNumber(1);
        else
            table.getEntry("camMode").setNumber(0);
        driveCamMode = !driveCamMode;
        System.out.println("Cam Mode Toggled");
    }

    /**
     * Toggles limelight’s led mode (On/Off)
     */
    public void toggleLED() {
        if (ledStatus)
            table.getEntry("ledMode").setNumber(3);
        else
            table.getEntry("ledMode").setNumber(1);
        ledStatus = !ledStatus;
        System.out.println("LED Toggled");
    }

    /**
     * Aims robot at target
     * @param leftMotors
     * @param rightMotors
     * @param shooterMotor
     * @param piston2
     * @return Moves robot in range of target
     */
    public boolean aimAtTarget(String dist) {
        if (!targetVisible())
            return false;
        double xOffset = getX(), adjust;
        boolean finished = false;
        if (xOffset > 3) {
            adjust = .01 * xOffset + .05;
        } else if (xOffset < -3) {
            adjust = .01 * xOffset - .05;
        } else {
            adjust = 0;
            finished = true;
        }
        Robot.setMotors(-adjust, adjust);

        double d;
        if (dist == "dist")
            d = getDist();
        else
            d = aimDistance();

        if (finished)
            moveToTarget(d);
        return finished;
    }

    /**
     * Moves robot in range of target
     * @param leftMotors
     * @param rightMotors
     * @param shooterMotor
     * @param piston2
     * @return Fires ball into target
     * @see Robot.ShootBall()
     */
    public boolean moveToTarget(double dist) {
        if (!targetVisible())
            return false;
        double offset = dist, adjust;
        boolean finished = false;
        offset -= 0;
        if (offset > 10) {
            adjust = .01 * offset - .05;
            if (Math.abs(offset) - 10 < 1) {
                adjust = 1;
            }
        } else if (offset < -10) {
            adjust = .01 * offset + .05;
            if (Math.abs(offset) - 10 < 1) {
                adjust = 1;
            }
        } else {
            adjust = 0;
            finished = true;
        }
        Robot.setMotors(-adjust, -adjust);

        return finished;
    }

    /**
     * Automatically aim and move robot in range of target
     * <p>
     * Comes from limelight website (In testing)
     */
    public void aimMove() {
        double KpAim = -0.1;
        double KpDistance = -0.1;
        double min_aim_command = 0.05;

        double tx = getX();
        double ty = getY();
        double heading_error = -tx;
        double distance_error = -ty;
        double steering_adjust = 0.0;

        if (tx > 1.0) {
            steering_adjust = KpAim * heading_error - min_aim_command;
        } else if (tx < 1.0) {
            steering_adjust = KpAim * heading_error + min_aim_command;
        }

        double distance_adjust = KpDistance * distance_error;

        Robot.setMotors(steering_adjust + distance_adjust, steering_adjust + distance_adjust);
    }

    /**
     * Only for use in FRC Infinite Recharge game
     * <p>
     * Done using a trendline
     * @return Distance from target
     */
    public double getDist() {
        return (-3.4094 * getY()) + 133.15;
    }

    /**
     * Comes from limelight website
     * <p> h1 is the height of the camera
     * <p> h2 is the height of the target
     * <p> a1 is the angle from level to where the camera is looking
     * <p> a2 is the angle from where the camera is looking to where the target is
     * @return Distance from target
     */
    public double aimDistance() {
        int h1  = 16;
        int h2 = 34;
        //int h2 = 98;
        int a1 = 65;
        int a2 = (int) getY();

        double d = Math.abs((h2 - h1) / Math.tan(a1 + a2));
        System.out.println(d);
        return d;
    }
}