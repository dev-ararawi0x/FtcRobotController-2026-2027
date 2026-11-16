package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "TeleOp Drive", group = "TeleOp")
public class TeleOpDrive extends OpMode {

    private final Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        double throttle = -gamepad1.left_stick_y;
        double spin = gamepad1.right_stick_x;

        robot.getDriveTrain().arcadeDrive(throttle, spin);
        robot.update();
    }

    @Override
    public void stop() {
        robot.getDriveTrain().stop();
    }
}
