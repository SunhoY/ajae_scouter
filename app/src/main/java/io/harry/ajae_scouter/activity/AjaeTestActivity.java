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
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import javax.inject.Inject;

import io.harry.ajae_scouter.R;
import io.harry.ajae_scouter.component.DaggerAjaeScouterComponent;
import io.harry.ajae_scouter.faceapi.wrapper.CameraSourceBuilderWrapper;
import io.harry.ajae_scouter.faceapi.wrapper.FaceDetectorWrapper;
import io.harry.ajae_scouter.faceapi.FaceListener;
import io.harry.ajae_scouter.faceapi.GraphicFaceTrackerFactory;
import io.harry.ajae_scouter.faceapi.wrapper.MultiProcessorWrapper;
import io.harry.ajae_scouter.view.CameraPreview;

public class AjaeTestActivity extends AppCompatActivity {

    private int smileCount = 0;

    CameraPreview cameraPreview;

    @Inject
    FaceDetectorWrapper faceDetectorWrapper;
    @Inject
    CameraSourceBuilderWrapper cameraSourceBuilderWrapper;
    @Inject
    MultiProcessorWrapper multiProcessorWrapper;

    private CameraSource.Builder cameraSourceBuilder;
    private CameraSource cameraSource;
    private FaceDetector faceDetector;
    private MultiProcessor<Face> multiProcessor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajae_test);

        DaggerAjaeScouterComponent.builder().build().inject(this);

        final TextView tempText = (TextView) findViewById(R.id.temp_text);
        final TextView smileCount = (TextView) findViewById(R.id.smile_count);
        FaceListener faceListener = new FaceListener() {
            @Override
            public void onFaceDetect(final Face face) {
                final float smile = face.getIsSmilingProbability();

                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(smile > .80f) {
                            Log.d("smile", String.valueOf(smile));
                            tempText.setVisibility(View.VISIBLE);
                            tempText.setText("웃었다!!! 아재발견!!");
                            smileCount.setVisibility(View.VISIBLE);
                            smileCount.setText(String.valueOf(++AjaeTestActivity.this.smileCount) + " 번 웃었다!!");
                        }
                    }
                });
            }
        };

        cameraPreview = (CameraPreview) findViewById(R.id.camera_preview);
        multiProcessor = multiProcessorWrapper.getMultiProcessorBuilder(new GraphicFaceTrackerFactory(faceListener));

        faceDetector = faceDetectorWrapper.getFaceDetector(this, FaceDetector.ALL_CLASSIFICATIONS);
        faceDetector.setProcessor(multiProcessor);

        cameraSourceBuilder = cameraSourceBuilderWrapper.getCameraSourceBuilder(this, faceDetector);
        cameraSource = cameraSourceBuilder
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
