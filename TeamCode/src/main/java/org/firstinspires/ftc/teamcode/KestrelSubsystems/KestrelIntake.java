package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class KestrelIntake {

	DcMotor intakeMotor;
	double intakeMotorPower;

	public KestrelIntake(HardwareMap hardwareMap) {
		intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

	}

	public void update(Gamepad gamepad1, Telemetry telemetry) {
		setIntakeMotorPower(gamepad1);
		runIntake(intakeMotorPower);
	}

	public void setIntakeMotorPower(Gamepad gamepad1) {
		if (gamepad1.right_trigger >= 0.2) {
			intakeMotorPower = gamepad1.right_trigger;
		} else if (gamepad1.left_trigger >= 0.2) {
			intakeMotorPower = -gamepad1.left_trigger;
		} else {
			intakeMotorPower = 0;
		}
	}

	public void runIntake(double motorPower) {
		intakeMotor.setPower(motorPower);
	}
}
