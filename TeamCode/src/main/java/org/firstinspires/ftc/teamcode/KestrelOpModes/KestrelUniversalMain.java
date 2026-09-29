package org.firstinspires.ftc.teamcode.KestrelOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelDongle;
import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelDrivebase;
import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelIntake;
import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelNectarFlywheel;
import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelNectarHood;
import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelNectarLift;
import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelPollenFlywheel;
import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelPollenHood;
import org.firstinspires.ftc.teamcode.KestrelSubsystems.KestrelPollenLift;

@TeleOp(name = "Kestrel Ri5W Main")
public class KestrelUniversalMain extends OpMode {

	KestrelDongle kestrelDongle;
	KestrelDrivebase kestrelDrivebase;
	KestrelIntake kestrelIntake;
	KestrelNectarFlywheel kestrelNectarFlywheel;
	KestrelNectarHood kestrelNectarHood;
	KestrelNectarLift kestrelNectarLift;
	KestrelPollenFlywheel kestrelPollenFlywheel;
	KestrelPollenHood kestrelPollenHood;
	KestrelPollenLift kestrelPollenLift;

	@Override
	public void init() {
		kestrelDongle = new KestrelDongle(hardwareMap);
		kestrelDrivebase = new KestrelDrivebase(hardwareMap);
		kestrelIntake = new KestrelIntake(hardwareMap);
		kestrelNectarFlywheel = new KestrelNectarFlywheel(hardwareMap);
		kestrelNectarHood = new KestrelNectarHood(hardwareMap);
		kestrelNectarLift = new KestrelNectarLift(hardwareMap);
		kestrelPollenFlywheel = new KestrelPollenFlywheel(hardwareMap);
		kestrelPollenHood = new KestrelPollenHood(hardwareMap);
		kestrelPollenLift = new KestrelPollenLift(hardwareMap);

	}

	@Override
	public void loop() {
		kestrelDongle.update(gamepad1);
		kestrelDrivebase.update(gamepad1, telemetry);
		kestrelIntake.update(gamepad1, telemetry);
		kestrelNectarFlywheel.update(gamepad2, kestrelNectarFlywheel.changeNectarFlywheelSpeed(gamepad2), kestrelNectarFlywheel.setNectarFlywheelState(gamepad2));
		kestrelNectarHood.update(gamepad2);
		kestrelNectarLift.update(gamepad2);
		kestrelPollenFlywheel.update(gamepad2, kestrelPollenFlywheel.changePollenFlywheelSpeed(gamepad2), kestrelPollenFlywheel.setPollenFlywheelState(gamepad2));;
		kestrelPollenHood.update(gamepad2);
		kestrelPollenLift.update(gamepad2);
	}
}
