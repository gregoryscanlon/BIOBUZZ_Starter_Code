package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KestrelPollenLift {

	CRServo pollenLiftServo;
	double pollenLiftPower;

	public KestrelPollenLift(HardwareMap hardwareMap) {

		pollenLiftServo = hardwareMap.get(CRServo.class, "pollenLiftServo");
	}

	public void update(Gamepad gamepad2) {
		setPollenLiftPower(gamepad2);
		runPollenLift(pollenLiftPower);
	}

	public void setPollenLiftPower(Gamepad gamepad2) {
		if (gamepad2.left_trigger >= 0.3) {
			pollenLiftPower = gamepad2.left_trigger;
		} else {
			pollenLiftPower = 0;
		}
	}

	public void runPollenLift(double liftPower) {
		pollenLiftServo.setPower(liftPower);
	}
}
