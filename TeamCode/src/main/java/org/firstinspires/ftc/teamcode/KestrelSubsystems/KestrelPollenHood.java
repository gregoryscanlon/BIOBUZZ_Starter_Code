package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class KestrelPollenHood {

	Servo pollenHoodServo;
	double pollenHoodPosition;
	boolean dpadUpIsPressed;
	boolean dpadDownIsPressed;

	public KestrelPollenHood(HardwareMap hardwareMap) {
		pollenHoodServo = hardwareMap.get(Servo.class, "pollenHoodServo");
		pollenHoodPosition = 0;
		dpadUpIsPressed = false;
		dpadDownIsPressed = false;
	}

	public void update(Gamepad gamepad2) {
		changePollenHoodPosition(gamepad2);
		movePollenHood(pollenHoodPosition);
	}

	public void changePollenHoodPosition(Gamepad gamepad2) {
		if (!dpadUpIsPressed) {
			if (gamepad2.dpad_up) {
				pollenHoodPosition += 0.1;
			}
		} else if (!dpadDownIsPressed) {
			if (gamepad2.dpad_down) {
				pollenHoodPosition -= 0.1;
			}
		}
	//	TODO: Find max and min positions as well as increment steps
		pollenHoodPosition = Math.min(pollenHoodPosition, 0.96);
		pollenHoodPosition = Math.max(pollenHoodPosition, 0.04);

		dpadUpIsPressed = gamepad2.dpad_up;
		dpadDownIsPressed = gamepad2.dpad_down;
	}

	public void movePollenHood(double hoodPosition) {
		pollenHoodServo.setPosition(hoodPosition);
	}



}
