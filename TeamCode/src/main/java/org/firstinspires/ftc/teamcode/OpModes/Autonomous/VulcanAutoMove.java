package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanDrivebase;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanHood;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanIntake;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanLift;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanShooter;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanTurretRed;

@Autonomous
public class VulcanAutoMove extends LinearOpMode {

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


//		gDrivebase = new GDrivebase(hardwareMap);
//		waitForStart();

		waitForStart();

		//while (opModeIsActive()) {
			bLMotor.setPower(-0.3);
			bRMotor.setPower(-0.3);
			fLMotor.setPower(-0.3);
			fRMotor.setPower(-0.3);

			sleep(1000);

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