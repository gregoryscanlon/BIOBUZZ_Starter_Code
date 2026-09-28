package org.firstinspires.ftc.teamcode.VulcanSubsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class VulcanShooter {
	DcMotorEx shooterMotor1;
	DcMotorEx shooterMotor2;
	Boolean xIsPressed;
	Boolean shooterState;
	double shooterSpeed;

	public VulcanShooter (HardwareMap hardwareMap) {
		shooterMotor1 = hardwareMap.get(DcMotorEx.class, "shooterMotor1");
		shooterMotor2 = hardwareMap.get(DcMotorEx.class, "shooterMotor2");
		shooterState = false;
		xIsPressed = false;
		shooterMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
		shooterSpeed = 2050;
	}

	public void update(int shooterSpeed, Gamepad gamepad1, Telemetry telemetry) {

		changeShooterSpeed(gamepad1);
		setShooterState(gamepad1);
		shooterOnOff(shooterSpeed);
		telemetry(telemetry);

	}

	public void setShooterState(Gamepad gamepad1) {
		if (!xIsPressed) {
			if (gamepad1.x) {
				shooterState = !shooterState;
			}
		}
		xIsPressed = gamepad1.x;
	}

	public void shooterOnOff(int shooterSpeed) {
		if (this.shooterState) {
			shooterOn(shooterSpeed);
		} else {
			shooterOff();
		}
	}

	public void shooterOn(double shooterSpeed) {
		shooterMotor1.setVelocity(shooterSpeed);
		shooterMotor2.setVelocity(shooterSpeed);
	}

	public void shooterOff() {
		shooterMotor1.setVelocity(0);
		shooterMotor2.setVelocity(0);
	}

	public void changeShooterSpeed(Gamepad gamepad1) {
		if (gamepad1.dpad_up) {
			shooterSpeed += 1;
		} else if (gamepad1.dpad_down) {
			shooterSpeed -= 1;
		}
	}


	public void telemetry(Telemetry telemetry) {
//		telemetry.addData("shooterState", shooterState);

//		telemetry.addData("xIsPressed", xIsPressed);
		telemetry.addData("Shooter Motor Difference", shooterMotor1.getCurrentPosition() - shooterMotor2.getCurrentPosition());
		telemetry.addData("Actual Motor Speed", shooterMotor1.getVelocity());
		telemetry.addData("Intended Motor Speed", shooterSpeed);
	}
}
