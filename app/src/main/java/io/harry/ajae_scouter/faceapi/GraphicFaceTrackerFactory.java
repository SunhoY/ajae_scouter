package io.harry.ajae_scouter.faceapi;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import io.harry.ajae_scouter.tracker.GraphicFaceTracker;

public class GraphicFaceTrackerFactory implements MultiProcessor.Factory<Face> {
    private final FaceListener faceListener;

    public GraphicFaceTrackerFactory(FaceListener faceListener) {
        this.faceListener = faceListener;
    }

    @Override
    public Tracker<Face> create(Face face) {
        return new GraphicFaceTracker(faceListener);
    }
}
