package org.firstinspires.ftc.teamcode.ForzaHorizon7;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class AutoTest extends LinearOpMode {

    SampleMecanumDrive drive;

    public static double FRONTAL_MULTIPLIER = 0.951254565; // DIPSHIT
    public static double LATERAL_MULTIPLIER = 1;

    @Override
    public void runOpMode(){

        drive = new SampleMecanumDrive(hardwareMap);

        Pose2d pose = new Pose2d(0,0,0);

        drive.setPoseEstimate(pose);

        TrajectorySequence drum = drive.trajectorySequenceBuilder( pose )
                .lineTo( Variabile.hub_vector )
                .addDisplacementMarker(20, () -> {
                    drive.
                })
                .waitSeconds(4)
                .lineToLinearHeading( Variabile.start_spreHouse)
                .resetConstraints()
                .build();

        waitForStart();

        drive.followTrajectorySequence(drum);

    }

    //functie care returneaza distanta corecta cu tot cu multiplier ca face undershoot
    //si ampulea trebuie facuta si pentru frontal si lateral separat // have fun at strafes DIPSHIT

    double repair_frontal (double distance){ return distance*FRONTAL_MULTIPLIER; }

    public double repair_lateral (double distance){ return distance*LATERAL_MULTIPLIER; }

}
