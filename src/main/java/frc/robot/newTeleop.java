package frc.robot;

public class newTeleop extends Autonomous {

    boolean babyMode = false, twoHanded = true;

    /**
     * This function is called at the beginning of teleop.
     */
    @Override
    public void teleopInit() {
        System.out.println("Teleop started");
    }

    /**
     * This function is called periodically during teleop.
     */
    @Override
    public void teleopPeriodic() {

        if (twoHanded) {
            if (controller.getButton("a")) {
                setMotors(controller.getStickAxis("left","y"), controller.getStickAxis("right","y"));
            } else if (controller.getButton("b")) {
                setMotors(controller.getStickAxis("left","y") * 0.5, rightStick.getY() * 0.5);
            } else {
                setMotors(controller.getStickAxis("left","y") * 0.3, rightStick.getY() * 0.3);
            }

        } else {
            double forward = controller.getStickAxis("right","y");
            double adjust = controller.getStickAxis("right","x");

            if (controller.getButton("a")) {
                setMotors((forward - adjust), (forward + adjust));
            } else if (controller.getButton("b")) {
                setMotors((forward - adjust) * 0.4, (forward + adjust) * 0.4);
            } else {
                setMotors((forward - adjust) * 0.2, (forward + adjust) * 0.2);
            }
        }
        // Hold right trigger to rev shooter, hold right bumper to reverse shooter
        if (controller.getBumper("right")) {
            shooterMotor.setSpeed(-.4);
        } else if (controller.getTrigger("right")) {
            shooterMotor.setSpeed(controller.getTriggerAxis("right"));
        } else {
            shooterMotor.setSpeed(0);
        }

        // Hold left trigger to rev intake, hold left bumper to reverse intake
        if (controller.getBumper("left")) {
            intakeMotor.setSpeed(-.5);
        } else if (controller.getTrigger("left")) {
            intakeMotor.setSpeed(controller.getTriggerAxis("left"));
        } else {
            intakeMotor.setSpeed(0);
        }

        /* Press A on Xbox to retract large piston
        if (controller.getButton("a")) {
            largePiston.retract();
        } else {
            largePiston.extend();
        }
        */

        /* Press B on Xbox to retract small piston
        if (controller.getButton("b")) {
            smallPiston.retract();
        } else {
            smallPiston.extend();
        }
        */

        // Hold X on Xbox to aim, move, and shoot at the target
        if (controller.getButton("x")) {
            if (limelight.aimAtTarget("dist"))
                shootBall();
        } else {

        }

        // Press Y to shoot the ball
        if (controller.getButton("y")) {
            if (limelight.aimAtTarget("aim"))
                shootBall();
        } else {

        }

        // Press Start on Xbox to engage babyMode
        if (controller.getButton("start")) {
            limelight.toggleCamMode();
            limelight.toggleLED();
        } else {

        }

        // Press Back on Xbox to shoot ball
        if (controller.getButton("back")) {
            twoHanded = !twoHanded;
            System.out.println("Two Handed Toggled");
        } else {

        }
    }
}
