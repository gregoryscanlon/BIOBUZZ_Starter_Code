package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanDrivebase;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanHood;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanIntake;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanLift;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanShooter;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanTurretBlue;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanTurretRed;

@TeleOp
public class VulcanMainRed extends OpMode {
	VulcanDrivebase vulcanDrivebase;
	VulcanHood vulcanHood;
	VulcanIntake vulcanIntake;
	VulcanLift vulcanLift;
	VulcanShooter vulcanShooter;
	VulcanTurretRed vulcanTurretRed;
	VulcanTurretBlue vulcanTurretBlue;

//	Drive drive;
//	DriveValues driveValues;
//	Odometry odometry;
//	OTOS_tuning otosTuning;
//	Event event;
//	Path path;
//	PathBuilder pathBuilder;

	@Override
	public void init() {
		vulcanDrivebase = new VulcanDrivebase(hardwareMap);
		vulcanHood = new VulcanHood(hardwareMap);
		vulcanIntake = new VulcanIntake(hardwareMap);
		vulcanLift = new VulcanLift(hardwareMap);
		vulcanShooter = new VulcanShooter(hardwareMap);
		vulcanTurretRed = new VulcanTurretRed(hardwareMap);
		vulcanTurretBlue = new VulcanTurretBlue(hardwareMap);

//		drive = new Drive(hardwareMap);
//		odometry = new Odometry(hardwareMap);

	}

	@Override
	public void loop() {

		vulcanDrivebase.update(gamepad1);
		vulcanIntake.update(gamepad1, telemetry);
		vulcanLift.update(gamepad1, telemetry);
		vulcanShooter.update(vulcanTurretRed.shooterSpeedDecision(),gamepad1, telemetry);
		vulcanHood.update(gamepad2, telemetry);
		vulcanTurretRed.update(gamepad2, gamepad1, telemetry);
	}
}

