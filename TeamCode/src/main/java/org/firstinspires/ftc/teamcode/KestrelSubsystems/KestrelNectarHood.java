package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class KestrelNectarHood {

	Servo nectarHoodServo;
	double nectarHoodPosition;
	boolean yIsPressed;
	boolean aIsPressed;

	public KestrelNectarHood(HardwareMap hardwareMap) {
		nectarHoodServo = hardwareMap.get(Servo.class, "nectarHoodServo");
		nectarHoodPosition = 0;
		yIsPressed = false;
		aIsPressed = false;
	}

	public void update(Gamepad gamepad2) {
		changeNectarHoodPosition(gamepad2);
		moveNectarHood(nectarHoodPosition);
	}

	public void changeNectarHoodPosition(Gamepad gamepad2) {
		if (!yIsPressed) {
			if (gamepad2.y) {
				nectarHoodPosition += 0.1;
			}
		} else if (!aIsPressed) {
			if (gamepad2.a) {
				nectarHoodPosition -= 0.1;
			}
		}
		//	TODO: Find max and min positions as well as increment steps
		nectarHoodPosition = Math.min(nectarHoodPosition, 0.96);
		nectarHoodPosition = Math.max(nectarHoodPosition, 0.04);

		yIsPressed = gamepad2.y;
		aIsPressed = gamepad2.a;
	}

	public void moveNectarHood(double hoodPosition) {
		nectarHoodServo.setPosition(hoodPosition);
	}



}