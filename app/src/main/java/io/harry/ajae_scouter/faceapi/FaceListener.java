package io.harry.ajae_scouter.faceapi;

import com.google.android.gms.vision.face.Face;

public interface FaceListener {
    void onFaceDetect(Face face);
}
