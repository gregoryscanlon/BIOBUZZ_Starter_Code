package org.firstinspires.ftc.teamcode.VulcanSubsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class VulcanDrivebase {

	DcMotor bLMotor;
	DcMotor bRMotor;
	DcMotor fLMotor;
	DcMotor fRMotor;

	public VulcanDrivebase (HardwareMap hardwareMap){
		bLMotor = hardwareMap.get(DcMotor.class, "bLMotor");
		bRMotor = hardwareMap.get(DcMotor.class, "bRMotor");
		fLMotor = hardwareMap.get(DcMotor.class, "fLMotor");
		fRMotor = hardwareMap.get(DcMotor.class, "fRMotor");

		fRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
		bRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
	}

	public void update(Gamepad gamepad1) {

		double x = -gamepad1.left_stick_x;
		double y = gamepad1.right_stick_y;
		double z = -gamepad1.right_stick_x;
		double denom = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(z), 1);

		double fLPower = (y + x + z) / denom;
		double fRPower = (y - x - z) / denom;
		double bLPower = (y - x + z) / denom;
		double bRPower = (y + x - z) / denom;

		fLMotor.setPower(fLPower);
		fRMotor.setPower(fRPower);
		bLMotor.setPower(bLPower);
		bRMotor.setPower(bRPower);

	}

	public void drive(double power, int runTime) {

	}

	public void turn(double power, int runTime) {

	}

	public void strafe(double power, int runTime) {}
}
