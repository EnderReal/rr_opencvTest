package org.firstinspires.ftc.teamcode.ForzaHorizon7;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class NordStream2PipeLine extends OpenCvPipeline {

    Mat globalM;
    Rect LeftROI = new Rect(
            new Point(0,60),
            new Point(100 ,160)
    );
    Rect CenterROI = new Rect(
            new Point(100,60),
            new Point(210,160)
    );
    Rect RightROI = new Rect(
            new Point(210,60),
            new Point(360,160)
    );

    double fValue;
    @Override
    public Mat processFrame(Mat frame){

        Mat mask = new Mat();
        Scalar low = new Scalar(0,0,0);
        Scalar high = new Scalar(255,255,255);
        Imgproc.cvtColor(frame, mask, Imgproc.COLOR_RGB2HSV);
        Core.inRange(mask, low, high, mask);
        globalM = mask;
        fValue = (double) Core.sumElems(mask).val[0];
        Imgproc.rectangle(frame, CenterROI, new Scalar(100,100,100), 2);
        Imgproc.rectangle(frame, LeftROI, new Scalar(100,100,100), 2);
        Imgproc.rectangle(frame, RightROI, new Scalar(100,100,100), 2);
        return mask;
    }

    public Mat getGlobal(){
        return globalM;
    }

    public double getValue(){return fValue;}

}
