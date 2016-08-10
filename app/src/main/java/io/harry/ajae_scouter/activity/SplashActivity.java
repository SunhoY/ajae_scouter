package io.harry.ajae_scouter.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.FaceDetector;

import io.harry.ajae_scouter.R;
import io.harry.ajae_scouter.factory.GraphicFaceTrackerFactory;
import io.harry.ajae_scouter.view.CameraPreview;

public class SplashActivity extends AppCompatActivity {

    private CameraSource cameraSource;
    private int smileCount = 0;

    CameraPreview cameraPreview;

    public interface FaceListener {
        void onSmile(float smile);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final TextView tempText = (TextView) findViewById(R.id.temp_text);
        final TextView smileCount = (TextView) findViewById(R.id.smile_count);
        FaceListener faceListener = new FaceListener() {
            @Override
            public void onSmile(final float smile) {
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(smile > .80f) {
                            Log.d("smile", String.valueOf(smile));
                            tempText.setVisibility(View.VISIBLE);
                            tempText.setText("웃었다!!! 아재발견!!");
                            smileCount.setVisibility(View.VISIBLE);
                            smileCount.setText(String.valueOf(++SplashActivity.this.smileCount) + " 번 웃었다!!");
                        }
                    }
                });
            }
        };

        cameraPreview = (CameraPreview) findViewById(R.id.camera_preview);

        FaceDetector faceDetector = new FaceDetector.Builder(this)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();
        faceDetector.setProcessor(new MultiProcessor.Builder<>(new GraphicFaceTrackerFactory(faceListener)).build());
        cameraSource = new CameraSource.Builder(this, faceDetector)
                .setRequestedPreviewSize(640, 480)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .setRequestedFps(30.0f)
                .build();

        cameraPreview.setCameraSource(cameraSource);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        cameraPreview.stop();
    }
}
