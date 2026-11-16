package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.constants.DevicesName;


public class DriveTrain {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public void init(HardwareMap hardwareMap) {
        // its for checking if the hardwareMap is not null (not required but good practice)
        Objects.requireNonNull(hardwareMap, "hardwareMap must not be null");

        leftMotor = hardwareMap.get(DcMotor.class, DevicesName.LEFT_MOTOR);
        rightMotor = hardwareMap.get(DcMotor.class, DevicesName.RIGHT_MOTOR);

        leftMotor.setDirection(DriveConstants.LEFT_MOTOR_DIRECTION);
        rightMotor.setDirection(DriveConstants.RIGHT_MOTOR_DIRECTION);

        leftMotor.setZeroPowerBehavior(DriveConstants.ZERO_POWER_BEHAVIOR);
        rightMotor.setZeroPowerBehavior(DriveConstants.ZERO_POWER_BEHAVIOR);

        leftMotor.setMode(DriveConstants.MOTOR_RUN_MODE);
        rightMotor.setMode(DriveConstants.MOTOR_RUN_MODE);

        stop();
    }

    public void arcadeDrive(double throttle, double spin) {
        ensureInitialized(); // its for checking if the motors are initialized (not required but good practice)

        double leftPower = throttle + spin;
        double rightPower = throttle - spin;

        double maxMagnitude = Math.max(Math.abs(leftPower), Math.abs(rightPower));
        if (maxMagnitude > 1.0) {
            leftPower /= maxMagnitude;
            rightPower /= maxMagnitude;
        }

        setMotorPowers(leftPower, rightPower);
    }

    public void stop() {
        ensureInitialized();
        setMotorPowers(0.0, 0.0);
    }

    private void setMotorPowers(double leftPower, double rightPower) {
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }

    private void ensureInitialized() {
        if (leftMotor == null || rightMotor == null) {
            throw new IllegalStateException("DriveTrain must be initialized before use.");
        }
    }
}
