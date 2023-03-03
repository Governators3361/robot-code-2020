package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

class Motor extends VictorSPX {

    public Motor(int deviceNumber) {
        super(deviceNumber);
    }

    /**
     * Sets the appropriate output on the motor.
     * @param speed Double between -1.0 and 1.0, with 0.0 as stopped.
     */
    public void setSpeed(double speed) {
        set(ControlMode.PercentOutput, speed);
    }
}