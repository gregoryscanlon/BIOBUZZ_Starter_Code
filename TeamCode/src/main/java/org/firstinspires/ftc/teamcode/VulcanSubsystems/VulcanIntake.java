package org.firstinspires.ftc.teamcode.VulcanSubsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class VulcanIntake {

	DcMotor intakeMotor;
	double intakePower;

	public VulcanIntake(HardwareMap hardwareMap) {

		intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
		intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
		intakePower = 0;
	}

	public void update(Gamepad gamepad1, Telemetry telemetry) {
		setPower(gamepad1);
		runIntake();
		telemetry(telemetry);

	}

	public void setPower(Gamepad gamepad1) {
		if (gamepad1.right_bumper) {
			intakePower  = 0.65;
		} else if (gamepad1.right_trigger != 0) {
			intakePower = -gamepad1.right_trigger;
		}else {
			intakePower = 0;
		}
	}

	public void runIntake() {
		intakeMotor.setPower(intakePower);
	}

	public void telemetry(Telemetry telemetry) {
//		telemetry.addData("Intake Power", intakePower);
	}
}
