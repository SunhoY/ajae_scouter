package io.harry.ajae_scouter.tracker;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import io.harry.ajae_scouter.faceapi.FaceListener;

public class GraphicFaceTracker extends Tracker<Face> {
    private final FaceListener faceListener;

    public GraphicFaceTracker(FaceListener faceListener) {
        this.faceListener = faceListener;
    }

    @Override
    public void onNewItem(int faceId, Face face) {
        faceListener.onFaceDetect(face);
    }

    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        faceListener.onFaceDetect(face);
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
