package io.harry.ajae_scouter.faceapi.wrapper;

import android.content.Context;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.face.FaceDetector;

public class CameraSourceBuilderWrapper {
    public CameraSource.Builder getCameraSourceBuilder(Context context, FaceDetector faceDetector) {
        return new CameraSource.Builder(context, faceDetector);
    }
}
