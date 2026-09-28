package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KestrelPollenFlywheel {

	DcMotorEx pollenFlywheelMotor;
	boolean bumperIsPressed;
	boolean pollenFlywheelState;
	double pollenFlywheelSpeed;

	public KestrelPollenFlywheel(HardwareMap hardwareMap) {
		pollenFlywheelMotor = hardwareMap.get(DcMotorEx.class, "pollenFlywheelMotor");
		bumperIsPressed = false;
		pollenFlywheelState = false;
		pollenFlywheelSpeed =  1500;
	}

	public void update(Gamepad gamepad2, double pollenFlywheelSpeed, boolean pollenFlywheelState) {
		setPollenFlywheelState(gamepad2);
		pollenFlywheelOnOff(pollenFlywheelSpeed, pollenFlywheelState);
	}

	public void setPollenFlywheelState(Gamepad gamepad2) {

		if (!bumperIsPressed) {
			if (gamepad2.right_bumper) {
				pollenFlywheelState = !pollenFlywheelState;
			}
		}

		bumperIsPressed = gamepad2.right_bumper;

	}

	public void changePollenFlywheelSpeed(Gamepad gamepad2) {
		if (gamepad2.right_stick_y != 0) {
			pollenFlywheelSpeed += gamepad2.right_stick_y;
		}
	}

	public void pollenFlywheelOff() {
		pollenFlywheelMotor.setVelocity(0);
	}

	public void pollenFlywheelOn(double flywheelSpeed) {
		pollenFlywheelMotor.setVelocity(flywheelSpeed);
	}

	public void pollenFlywheelOnOff(double flywheelSpeed, boolean flywheelState) {
		if (flywheelState) {
			pollenFlywheelOn(flywheelSpeed);
		} else {
			pollenFlywheelOff();
		}

	}
}
