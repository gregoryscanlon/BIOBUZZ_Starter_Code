package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KestrelNectarFlywheel {

	DcMotorEx nectarFlywheelMotor;
	boolean bumperIsPressed;
	boolean nectarFlywheelState;
	double nectarFlywheelSpeed;

	public KestrelNectarFlywheel(HardwareMap hardwareMap) {
		nectarFlywheelMotor = hardwareMap.get(DcMotorEx.class, "nectarFlywheelMotor");
		bumperIsPressed = false;
		nectarFlywheelState = false;
		nectarFlywheelSpeed =  1500;
	}

	public void update(Gamepad gamepad2, double nectarFlywheelSpeed, boolean nectarFlywheelState) {
		setNectarFlywheelState(gamepad2);
		changeNectarFlywheelSpeed(gamepad2);
		nectarFlywheelOnOff(nectarFlywheelSpeed, nectarFlywheelState);
	}

	public void setNectarFlywheelState(Gamepad gamepad2) {

		if (!bumperIsPressed) {
			if (gamepad2.right_bumper) {
				nectarFlywheelState = !nectarFlywheelState;
			}
		}

		bumperIsPressed = gamepad2.right_bumper;

	}

	public void changeNectarFlywheelSpeed(Gamepad gamepad2) {
		if (gamepad2.right_stick_y != 0) {
			nectarFlywheelSpeed += gamepad2.right_stick_y;
		}
	}

	public void nectarFlywheelOff() {
		nectarFlywheelMotor.setVelocity(0);
	}

	public void nectarFlywheelOn(double flywheelSpeed) {
		nectarFlywheelMotor.setVelocity(flywheelSpeed);
	}

	public void nectarFlywheelOnOff(double flywheelSpeed, boolean flywheelState) {
		if (flywheelState) {
			nectarFlywheelOn(flywheelSpeed);
		} else {
			nectarFlywheelOff();
		}

	}
}
