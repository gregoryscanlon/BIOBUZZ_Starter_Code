package org.firstinspires.ftc.teamcode.VulcanSubsystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.trailblazer.drivebase.Drive;
import org.fotmrobotics.trailblazer.Pose2D;

public class VulcanTurretRed {
	// TeleOp Turret
	DcMotorEx turretMotor;
	DigitalChannel turretLimitSwitch;
	double turretTargetPosition = 0;
	double turretTargetAngle;
	boolean startupCompleted;

	boolean aIsPressed;
	boolean manualOverride;

	// Auto Aim Turret
	double vulkanXPosition;
	double vulkanYPosition;
	double xOffsetFromTarget;
	double yOffsetFromTarget;
	double angleOffset;
	double autoAimTurretTargetAngle;
	Drive drive;
	boolean xIsPressed;
	double xStartPosition;
	double yStartPosition;
	double xTargetPosition;
	double yTargetPosition;

	double driver1TurretCorrection;

	GoBildaPinpointDriver pinpoint;
	double distanceFromGoal;



	public VulcanTurretRed(HardwareMap hardwareMap) {
		turretMotor = hardwareMap.get(DcMotorEx.class, "turretMotor");
		turretLimitSwitch = hardwareMap.get(DigitalChannel.class, "turretLimitSwitch");
		turretMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		turretMotor.setTargetPosition((int)turretTargetPosition);
		startupCompleted = false;

		aIsPressed = false;
		manualOverride = false;

		xStartPosition = 89.5;
		yStartPosition = 8.75;
		xTargetPosition = 138;
		yTargetPosition = 138;
		driver1TurretCorrection = 0;

		drive = new Drive(hardwareMap);

		pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "odo");

		turretMotor.setPositionPIDFCoefficients(10);
	}

	public void update (Gamepad gamepad2, Gamepad gamepad1, Telemetry telemetry) {
		if (!startupCompleted) {
			startupReset();
			telemetryPrintout(telemetry);
		} else {
			if (aimingManualOverride(gamepad2)) {
				setTargetAngle(gamepad2);
				toAngle(turretTargetAngle);
				telemetryPrintout(telemetry);
				calculateDistanceFromGoal();
			} else {
				setAutoAimTurretTargetAngle(xStartPosition, yStartPosition, xTargetPosition, yTargetPosition);
				driver1TurretCorrection(gamepad1);
				toAngle(autoAimTurretTargetAngle + 13 + driver1TurretCorrection);
				odometryReset(gamepad2);
				autoTurretTelemetryPrintout(telemetry);
				calculateDistanceFromGoal();
			}
		}

	}



	public double encoderToAngle() {
		return 90 + ((-turretMotor.getCurrentPosition()) / 145.1 / 61 * 12) * 360;
	}

	public double angleToEncoder(double angle) {
		return angle / 360 * 61 / 12 * 145.1;
	}

	public void toAngle(double angle) {
		turretMotor.setTargetPosition((int)angleToEncoder(-angle) + 10);
		turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		turretMotor.setPower(0.8);
	}

	public void startupReset() {
		boolean startupCompleted = false;
		if (!startupCompleted) {
			turretMotor.setPower(0.2);
			if (!turretLimitSwitch.getState()) {
				turretMotor.setPower(0);
//			turretMotor.setTargetPosition((int) angleToEncoder(turretTargetAngle));
				turretMotor.setTargetPosition(turretMotor.getCurrentPosition());
				turretTargetPosition = 0;
				turretTargetAngle = 0;
				turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
				startupCompleted = true;
				this.startupCompleted = true;
			}
		}
	}


	public void setTargetAngle(Gamepad gamepad2) {
		if (gamepad2.right_stick_x != 0) {
			turretTargetAngle -= gamepad2.right_stick_x * 8;
		}
		turretTargetAngle = Math.max(turretTargetAngle, -90);
		turretTargetAngle = Math.min(turretTargetAngle, 270);
//		return turretTargetAngle;
	}

	public void telemetryPrintout (Telemetry telemetry) {
//		telemetry.addData("Limit Switch State", turretLimitSwitch.getState());
//		telemetry.addData("Turret Encoder Position", turretMotor.getCurrentPosition());
//		telemetry.addData("Encoder to Angle", encoderToAngle());
//		telemetry.addData("Angle to Encoder", angleToEncoder(turretTargetAngle));
//		telemetry.addData("Target Angle", turretTargetAngle);
//		telemetry.addData("turret target position", turretTargetPosition);
		telemetry.addData("startup completed", startupCompleted);

	}

	//AUTO AIM FUNCTIONS

	public double setAutoAimTurretTargetAngle(double xStartPosition, double yStartPosition, double xTargetPosition, double yTargetPosition) {
		Pose2D position = drive.odometry.getPosition();

		vulkanXPosition = xStartPosition + position.getX();
		vulkanYPosition = yStartPosition + position.getY();
		xOffsetFromTarget = xTargetPosition - vulkanXPosition;
		yOffsetFromTarget = yTargetPosition - vulkanYPosition;
		angleOffset = 90 - Math.atan2(yOffsetFromTarget, xOffsetFromTarget) * 180 / Math.PI;

		autoAimTurretTargetAngle = 180 + angleOffset + position.getH();
		if (autoAimTurretTargetAngle > 270) {
			autoAimTurretTargetAngle = ((autoAimTurretTargetAngle + 90) % 360) - 90;
		}
		return autoAimTurretTargetAngle;
	}

	public void driver1TurretCorrection(Gamepad gamepad1) {
		if (gamepad1.dpad_right) {
			driver1TurretCorrection += 0.25;
		}
		if (gamepad1.dpad_left) {
			driver1TurretCorrection -= 0.25;
		}
	}

	public boolean aimingManualOverride(Gamepad gamepad2) {
		if (!aIsPressed && gamepad2.a) {
			manualOverride = !manualOverride;
		}
		aIsPressed = gamepad2.a;
		return manualOverride;
	}

	public void odometryReset(Gamepad gamepad2) {
		if (!xIsPressed && gamepad2.x) {
			pinpoint.resetPosAndIMU();
			xStartPosition = 6.625;
			yStartPosition = 8.75;
			xTargetPosition = 138;
			yTargetPosition = 138;
		}

		xIsPressed = gamepad2.x;

	}

	public void calculateDistanceFromGoal() {
		distanceFromGoal = Math.sqrt(Math.pow(xTargetPosition - vulkanXPosition, 2) + Math.pow(yTargetPosition - vulkanYPosition, 2));
	}

//	public double hoodAutoLevelFunction() {
//		return (-0.0194*distanceFromGoal + 2.9927);
//	}

	public int shooterSpeedDecision() {
		if (distanceFromGoal > 115) {
			return 2100;
		} else if (85 < distanceFromGoal && distanceFromGoal < 115) {
			return 1800;
		} else {
			return 1500;
		}
	}
		
	public void autoTurretTelemetryPrintout(Telemetry telemetry) {
	//	telemetry.addData("X", position.getX());
	//	telemetry.addData("Y", position.getY());
	//	telemetry.addData("Angle", position.getH());

	//	telemetry.addData("Vulkan X Position", vulkanXPosition);
	//	telemetry.addData("Vulkan Y Position", vulkanYPosition);
	//	telemetry.addData("💥💥💥Angle Offset💥💥💥", angleOffset);
	//	telemetry.addData("Auto Aim Turret Target Angle", autoAimTurretTargetAngle);
		telemetry.addData("Odometry X", drive.odometry.getPosition().getX());
		telemetry.addData("Odometry y", drive.odometry.getPosition().getY());
		telemetry.addData("Distance From Goal", distanceFromGoal);
	}
}


