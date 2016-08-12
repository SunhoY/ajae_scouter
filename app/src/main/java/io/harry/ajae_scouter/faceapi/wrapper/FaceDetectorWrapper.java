package io.harry.ajae_scouter.faceapi.wrapper;

import android.content.Context;

import com.google.android.gms.vision.face.FaceDetector;

public class FaceDetectorWrapper {
    public FaceDetector getFaceDetector(Context context, int classificationType) {
        return new FaceDetector.Builder(context)
                .setClassificationType(classificationType).build();
    }
}
