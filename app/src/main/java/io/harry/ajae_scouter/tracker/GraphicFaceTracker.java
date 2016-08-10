package io.harry.ajae_scouter.tracker;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import io.harry.ajae_scouter.activity.SplashActivity;

public class GraphicFaceTracker extends Tracker<Face> {
    private final SplashActivity.FaceListener faceListener;

    public GraphicFaceTracker(SplashActivity.FaceListener faceListener) {
        this.faceListener = faceListener;
    }

    @Override
    public void onNewItem(int faceId, Face face) {
        faceListener.onSmile(face.getIsSmilingProbability());
    }

    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        faceListener.onSmile(face.getIsSmilingProbability());
    }

    @Override
    public void onMissing(Detector.Detections<Face> detections) {
        super.onMissing(detections);
    }

    @Override
    public void onDone() {
        super.onDone();
    }
}
