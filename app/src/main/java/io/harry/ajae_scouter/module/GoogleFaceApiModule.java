package io.harry.ajae_scouter.module;

import dagger.Module;
import dagger.Provides;
import io.harry.ajae_scouter.faceapi.wrapper.CameraSourceBuilderWrapper;
import io.harry.ajae_scouter.faceapi.wrapper.FaceDetectorWrapper;
import io.harry.ajae_scouter.faceapi.wrapper.MultiProcessorWrapper;

@Module
public class GoogleFaceApiModule {
    @Provides
    public FaceDetectorWrapper provideFaceDetectorWrapper() {
        return new FaceDetectorWrapper();
    }

    @Provides
    public CameraSourceBuilderWrapper provideCameraSourceBuilderWrapper() {
        return new CameraSourceBuilderWrapper();
    }

    @Provides
    public MultiProcessorWrapper provideMultiProcessorBuilderWrapper() {
        return new MultiProcessorWrapper();
    }
}
