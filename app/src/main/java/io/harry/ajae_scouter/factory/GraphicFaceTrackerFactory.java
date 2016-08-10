package io.harry.ajae_scouter.factory;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import io.harry.ajae_scouter.activity.SplashActivity;
import io.harry.ajae_scouter.tracker.GraphicFaceTracker;

public class GraphicFaceTrackerFactory implements MultiProcessor.Factory<Face> {
    private final SplashActivity.FaceListener faceListener;

    public GraphicFaceTrackerFactory(SplashActivity.FaceListener faceListener) {
        this.faceListener = faceListener;
    }

    @Override
    public Tracker<Face> create(Face face) {
        return new GraphicFaceTracker(faceListener);
    }
}
