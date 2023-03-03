package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

class Piston extends DoubleSolenoid {

    private boolean extended;

    public Piston(int forwardChannel, int reverseChannel) {
        super(forwardChannel, reverseChannel);
        setExtended();
    }

    /**
     * Toggles piston's state
     */
    public void fire() {
        if (extended) {
            retract();
        } else {
            extend();
        }
        setExtended();
    }

    /**
     * Retracts piston
     */
    public void retract() {
        if (extended) {
            set(DoubleSolenoid.Value.kReverse);
        }
        setExtended();
    }

    /**
     * Extends piston
     */
    public void extend() {
        if (!extended) {
            set(DoubleSolenoid.Value.kForward);
        }
        setExtended();
    }

    /**
     * Checks if piston is extended
     * @return Whether piston is extended or not
     */
    public boolean isExtended() {
        return extended;
    }

    /**
     * Sets private var extended to proper value
     */
    private void setExtended() {
        extended = (get() == DoubleSolenoid.Value.kForward);
    }
}