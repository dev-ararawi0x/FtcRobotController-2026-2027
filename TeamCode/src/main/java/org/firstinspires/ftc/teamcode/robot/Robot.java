package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveTrain;

public class Robot {

    private final DriveTrain driveTrain = new DriveTrain();

    public void init(HardwareMap hardwareMap) {
        driveTrain.init(hardwareMap);
    }

    // this method is called every cycle to update the robot's state
    public void update() {
    }

    public DriveTrain getDriveTrain() {
        return driveTrain;
    }
}
