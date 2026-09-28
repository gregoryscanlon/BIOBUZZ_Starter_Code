package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanDrivebase;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanHood;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanIntake;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanLift;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanShooter;
import org.firstinspires.ftc.teamcode.VulcanSubsystems.VulcanTurretBlue;

@TeleOp
public class VulcanMainBlue extends OpMode {
	VulcanDrivebase vulcanDrivebase;
	VulcanHood vulcanHood;
	VulcanIntake vulcanIntake;
	VulcanLift vulcanLift;
	VulcanShooter vulcanShooter;
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
		vulcanTurretBlue = new VulcanTurretBlue(hardwareMap);

//		drive = new Drive(hardwareMap);
//		odometry = new Odometry(hardwareMap);

	}

	@Override
	public void loop() {

		vulcanDrivebase.update(gamepad1);
		vulcanIntake.update(gamepad1, telemetry);
		vulcanLift.update(gamepad1, telemetry);
		vulcanShooter.update(vulcanTurretBlue.shooterSpeedDecision(), gamepad1, telemetry);
		vulcanHood.update(gamepad2, telemetry);
		vulcanTurretBlue.update(gamepad2, gamepad1, telemetry);
	}
}


// TODO
//Automation:
//  Condense odometry for hood and turret into one automation file
// Leave comments on each function to understand code in the future