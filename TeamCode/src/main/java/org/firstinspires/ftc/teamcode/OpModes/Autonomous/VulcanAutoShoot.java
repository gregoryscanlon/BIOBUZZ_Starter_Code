package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanDrivebase;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanHood;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanIntake;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanLift;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanShooter;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanTurretRed;

@Autonomous
public class VulcanAutoShoot extends LinearOpMode {

	VulcanDrivebase vulcanDrivebase;
	VulcanHood vulcanHood;
	VulcanIntake vulcanIntake;
	VulcanLift vulcanLift;
	VulcanShooter vulcanShooter;
	VulcanTurretRed vulcanTurret;


	DcMotor bLMotor;
	DcMotor bRMotor;
	DcMotor fLMotor;
	DcMotor fRMotor;
	DcMotorEx shooterMotor1;
	DcMotorEx shooterMotor2;
	Servo hoodServo;
	DcMotorEx turretMotor;
	Servo liftMotor;


	@Override
	public void runOpMode() throws InterruptedException {

//		timer = new ElapsedTime();

		vulcanDrivebase = new VulcanDrivebase(hardwareMap);
		vulcanHood = new VulcanHood(hardwareMap);
		vulcanIntake = new VulcanIntake(hardwareMap);
		vulcanLift = new VulcanLift(hardwareMap);
		vulcanShooter = new VulcanShooter(hardwareMap);
		vulcanTurret = new VulcanTurretRed(hardwareMap);

		bLMotor = hardwareMap.get(DcMotor.class, "bLMotor");
		bRMotor = hardwareMap.get(DcMotor.class, "bRMotor");
		fLMotor = hardwareMap.get(DcMotor.class, "fLMotor");
		fRMotor = hardwareMap.get(DcMotor.class, "fRMotor");
		shooterMotor1 = hardwareMap.get(DcMotorEx.class, "shooterMotor1");
		shooterMotor2 = hardwareMap.get(DcMotorEx.class, "shooterMotor2");
		hoodServo = hardwareMap.get(Servo.class, "hoodServo");
		turretMotor = hardwareMap.get(DcMotorEx.class, "turretMotor");
		turretMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		liftMotor = hardwareMap.get(Servo.class, "liftMotor");



		waitForStart();

		//while (opModeIsActive()) {
		bLMotor.setPower(0.35);
		bRMotor.setPower(-0.3);
		fLMotor.setPower(-0.3);
		fRMotor.setPower(0.35);

		sleep(375);

		bLMotor.setPower(0);
		bRMotor.setPower(0);
		fLMotor.setPower(0);
		fRMotor.setPower(0);

		sleep(3000);

		shooterMotor1.setVelocity(2500);
		shooterMotor2.setVelocity(2500);
		hoodServo.setPosition(0.56);

		turretMotor.setTargetPosition(0);
		turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		turretMotor.setPower(0.8);

		sleep(3000);

		liftMotor.setPosition(0.6);

		sleep(3000);

		shooterMotor1.setVelocity(0);
		shooterMotor2.setVelocity(0);
		turretMotor.setPower(0);

		sleep(2000);

		bLMotor.setPower(0.35);
		bRMotor.setPower(-0.3);
		fLMotor.setPower(-0.3);
		fRMotor.setPower(0.35);

		sleep(800);

		bLMotor.setPower(0.3);
		bRMotor.setPower(0.3);
		fLMotor.setPower(0.3);
		fRMotor.setPower(0.3);

		sleep(2000);

		bLMotor.setPower(0);
		bRMotor.setPower(0);
		fLMotor.setPower(0);
		fRMotor.setPower(0);


//			telemetry.addData("X Offset", );
//			telemetry.addData("Y Offset", yOffset);
//			telemetry.update();
		//}

	}
}