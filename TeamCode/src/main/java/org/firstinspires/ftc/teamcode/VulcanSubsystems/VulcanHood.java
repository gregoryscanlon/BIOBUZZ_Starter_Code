package org.firstinspires.ftc.teamcode.VulcanSubsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class VulcanHood {
		Servo hoodServo;
		double hoodPosition;
		boolean dpadUpIsPressed;
		boolean dpadDownIsPressed;


	public VulcanHood (HardwareMap hardwareMap) {
		hoodServo = hardwareMap.get(Servo.class, "hoodServo");
		hoodPosition = 0.04;
		dpadDownIsPressed = false;
		dpadUpIsPressed = false;
	}

	public void update(Gamepad gamepad2, Telemetry telemetry) {
		setHoodPosition(gamepad2);
//		moveHood(autoHoodLevel);
		moveHood(hoodPosition);
		telemetry.addData("Target Position", hoodPosition);
	}

	public void setHoodPosition(Gamepad gamepad2) {
		if (!dpadUpIsPressed) {
			if (gamepad2.dpad_up) {
			//	hoodPosition += 0.23;
				hoodPosition += 0.05;
			}
		}

		if (!dpadDownIsPressed) {
			if (gamepad2.dpad_down) {
			//	hoodPosition -= 0.23;
				hoodPosition -= 0.05;
			}
		}

		hoodPosition = Math.min(hoodPosition, 0.96);
		hoodPosition = Math.max(hoodPosition, 0.04);

		dpadUpIsPressed = gamepad2.dpad_up;
		dpadDownIsPressed = gamepad2.dpad_down;
	}

	public void moveHood(double hoodLevel) {
		hoodServo.setPosition(hoodLevel);
	}
}
