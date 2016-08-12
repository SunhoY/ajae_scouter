package io.harry.ajae_scouter.faceapi.wrapper;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.Face;

import io.harry.ajae_scouter.faceapi.GraphicFaceTrackerFactory;

public class MultiProcessorWrapper {
    public MultiProcessor<Face> getMultiProcessorBuilder(GraphicFaceTrackerFactory multiProcessorFactory) {
        return new MultiProcessor.Builder<>(multiProcessorFactory).build();
    }
}
