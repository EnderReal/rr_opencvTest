package org.firstinspires.ftc.teamcode.ForzaHorizon7;

import com.acmerobotics.roadrunner.geometry.Pose2d;
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
                .lineTo( variabile.hub_vector )
                .addTemporalMarker(0, () ->{
                    drive.scula_power(drive.scula_power);
                })
                .addTemporalMarker( 0 + variabile.timp_ridicare_sus, ()->{
                    drive.scula_power(0);
                })
                .addTemporalMarker( 3, ()->{
                    drive.scula_power(0);
                    drive.arunca();
                })
                .waitSeconds(2)
                .lineToLinearHeading( variabile.start_spreHouse)
                .addTemporalMarker( 5, ()->{
                    drive.retrage_cuva();
                    drive.scula_power(-1);
                })
                .addTemporalMarker(5 + variabile.timp_ridicare_sus, ()->{
                    drive.scula_power(0);
                })
                .build();

        waitForStart();

        drive.followTrajectorySequence(drum);

    }

    //functie care returneaza distanta corecta cu tot cu multiplier ca face undershoot
    //si ampulea trebuie facuta si pentru frontal si lateral separat // have fun at strafes DIPSHIT

    double repair_frontal (double distance){ return distance*FRONTAL_MULTIPLIER; }

    public double repair_lateral (double distance){ return distance*LATERAL_MULTIPLIER; }

}
