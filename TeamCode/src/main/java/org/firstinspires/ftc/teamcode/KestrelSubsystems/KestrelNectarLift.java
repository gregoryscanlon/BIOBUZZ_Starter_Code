package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KestrelNectarLift {

	CRServo nectarLiftServo;
	double nectarLiftPower;

	public KestrelNectarLift(HardwareMap hardwareMap) {

		nectarLiftServo = hardwareMap.get(CRServo.class, "nectarLiftServo");
	}

	public void update(Gamepad gamepad2) {
		setNectarLiftPower(gamepad2);
		runNectarLift(nectarLiftPower);
	}

	public void setNectarLiftPower(Gamepad gamepad2) {
		if (gamepad2.right_trigger >= 0.3) {
			nectarLiftPower = gamepad2.right_trigger;
		} else {
			nectarLiftPower = 0;
		}
	}

	public void runNectarLift(double liftPower) {
		nectarLiftServo.setPower(liftPower);
	}
}
