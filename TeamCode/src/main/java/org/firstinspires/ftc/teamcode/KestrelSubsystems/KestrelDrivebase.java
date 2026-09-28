package org.firstinspires.ftc.teamcode.KestrelSubsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class KestrelDrivebase {

	DcMotorEx bLMotor;
	DcMotorEx bRMotor;
	DcMotorEx fLMotor;
	DcMotorEx fRMotor;

//	TODO: Find reccomended max ticks/second for drivetrain motors to avoid burnout
	private static final double MAX_TICKS_PER_SECOND = 2800;

	public KestrelDrivebase(HardwareMap hardwareMap) {
		bLMotor = hardwareMap.get(DcMotorEx.class, "bLMotor");
		bRMotor = hardwareMap.get(DcMotorEx.class, "bRMotor");
		fLMotor = hardwareMap.get(DcMotorEx.class, "fLMotor");
		fRMotor = hardwareMap.get(DcMotorEx.class, "fRMotor");

//		TODO: Set motors that need to be reversed
//		bLMotor.setDirection(DcMotorSimple.Direction.REVERSE);

		//Make motors use encoders built in to DcMotorEx including ticks per second and distance tracking
		bLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		bRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		fLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		fRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

		//Set motors to resist movement when not given a power input
		bLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		bRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		fLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		fRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	public void update(Gamepad gamepad1, Telemetry telemetry) {
//		TODO: Set variable constants to correct off center strafing
		double x = gamepad1.left_stick_x;
		double y = -gamepad1.left_stick_y;
		double r = gamepad1.right_stick_x;
		double denom = Math.abs(x) + Math.abs(y) + Math.abs(r);

		double bLPower = (y - x + r) / denom;
		double bRPower = (y + x - r) / denom;
		double fLPower = (y + x + r) / denom;
		double fRPower = (y - x - r) / denom;

		bLMotor.setVelocity(bLPower * MAX_TICKS_PER_SECOND);
		bRMotor.setVelocity(bRPower * MAX_TICKS_PER_SECOND);
		fLMotor.setVelocity(fLPower * MAX_TICKS_PER_SECOND);
		fRMotor.setVelocity(fRPower * MAX_TICKS_PER_SECOND);


	}
}
