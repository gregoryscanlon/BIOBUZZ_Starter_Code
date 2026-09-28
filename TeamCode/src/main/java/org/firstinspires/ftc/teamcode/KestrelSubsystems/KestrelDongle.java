package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class KestrelDongle {

	Servo dongleServo;
	boolean bumperIsPressed;
	boolean dongleExtendedState;
	double donglePosition;


	public KestrelDongle(HardwareMap hardwareMap) {
		dongleServo = hardwareMap.get(Servo.class, "dongleServo");
		bumperIsPressed = false;
		dongleExtendedState = false;
		donglePosition = 0;
	}

	public void update(Gamepad gamepad1) {
		setDongleExtendedState(gamepad1);
		setDonglePosition(dongleExtendedState);
		moveDongle(donglePosition);
	}

	public void setDongleExtendedState(Gamepad gamepad1) {
		if (!bumperIsPressed) {
			if (gamepad1.right_bumper) {
				dongleExtendedState = !dongleExtendedState;
			}
		}

		bumperIsPressed = gamepad1.right_bumper;
	}

	public void setDonglePosition(boolean extendedState) {
	//	TODO: Find values for extended and retracted positions
		if (extendedState) {
			donglePosition = 1;
		} else {
			donglePosition = 0;
		}
	}

	public void moveDongle(double position) {
		dongleServo.setPosition(position);
	}
}
