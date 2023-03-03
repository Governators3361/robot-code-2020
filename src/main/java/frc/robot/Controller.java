package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class Controller extends XboxController {

    public Controller(int port) {
        super(port);
    }

    /**
     * Read the value of the bumper button on the controller.
     * @param side Side of controller whose value should be returned (left/right).
     * @return true if Pressed, false if Released
     */
    public boolean getBumper(String side) {
        side = side.toLowerCase().trim();
        switch (side) {
            case ("right"):
                return (getBumper(Hand.kRight) && getTriggerAxis(Hand.kRight) < .05);
            case ("left"):
                return (getBumper(Hand.kLeft) && getTriggerAxis(Hand.kLeft) < .05);
            default:
                return false;
        }
    }

    /**
     * Read the value of the trigger button on the controller.
     * @param side Side of controller whose value should be returned (left/right).
     * @return true if Pressed, false if Released
     */
    public boolean getTrigger(String side) {
        side = side.toLowerCase().trim();
        switch (side) {
            case ("right"):
                return (getTriggerAxis(Hand.kRight) > 0.2);
            case ("left"):
                return (getTriggerAxis(Hand.kLeft) > 0.2);
            default:
                return false;
        }
    }

    /**
     * Get the trigger axis value of the controller.
     * @param side Side of controller whose value should be returned (left/right).
     * @return The trigger axis value of the controller.
     */
    public double getTriggerAxis(String side) {
        side = side.toLowerCase().trim();
        switch (side) {
            case ("right"):
                return getTriggerAxis(Hand.kRight);
            case ("left"):
                return getTriggerAxis(Hand.kLeft);
            default:
                return 0;
        }
    }

    /**
     * Read the value of a button on the controller.
     * @param button Input name of button to check (a, b, x, y, start, back, lstick, rstick)
     * @return true if Pressed, false if Released
     */
    public boolean getButton(String button) {
        button = button.toLowerCase().trim();
        switch (button) {
            case ("a"):
                return getAButton();
            case ("b"):
                return getBButton();
            case ("x"):
                return getXButton();
            case ("y"):
                return getYButton();
            case ("start"):
                return getStartButton() && getStartButtonReleased();
            case ("back"):
                return getBackButton() && getBackButtonReleased();
            case ("lstick"):
                return getStickButton(Hand.kLeft);
            case ("rstick"):
                return getStickButton(Hand.kRight);
            default:
                return false;
        }
    }

    /**
     * Get the stick axis value of the controller.
     * @param side Side of controller whose value should be returned (left/right).
     * @param axis Axis of stick whose value should be returned (X/Y).
     * @return The stick axis value of the controller.
     */
    public double getStickAxis(String side, String axis) {
        side = side.toLowerCase().trim();
        axis = axis.toUpperCase().trim();
        switch (side + axis) {
            case ("leftX"):
                return getX(Hand.kLeft);
            case ("leftY"):
                return getY(Hand.kLeft);
            case ("rightX"):
                return getX(Hand.kRight);
            case ("rightY"):
                return getY(Hand.kRight);
            default:
                return 0;
        }
    }
}
