package com.example.seedidentifier;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.core.TorchState;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.nio.file.FileSystem;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class Camera  extends AppCompatActivity {

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    private PreviewView previewView;
    private Button snap;
    private ImageCapture imageCapture;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        previewView = findViewById(R.id.previewView);
        snap = findViewById(R.id.button);

        hasPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
        hasPermission(Manifest.permission.CAMERA, STORAGE_PERMISSION_CODE);

        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeImage();
            }
        });

        cameraProviderFuture = ProcessCameraProvider.getInstance(Camera.this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                startCameraX(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        }, getExecutor());
    }

    private void takeImage() {
        File imageDir = new File(getCodeCacheDir().getAbsolutePath() + "/images/");
        if(!imageDir.exists())
            if(!imageDir.mkdirs()){
                Toast.makeText(Camera.this,"Couldn't do path", Toast.LENGTH_SHORT).show();
            }
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        String newPath = imageDir.getAbsolutePath() +  "/" + timestamp + ".jpg";

        File photo = new File(newPath);

        imageCapture.takePicture(
                new ImageCapture.OutputFileOptions.Builder(photo).build(),
                getExecutor(),
                new ImageCapture.OnImageSavedCallback(){
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults){
                        Toast.makeText(Camera.this,"Photo saved successfully at " + photo.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(@NonNull ImageCaptureException e){
                        Toast.makeText(Camera.this,"Couldn't save photo" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Checking whether user granted the permission or not.

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(Camera.this, "Camera Permission Granted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Camera.this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
        } else if (requestCode == STORAGE_PERMISSION_CODE)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(Camera.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Camera.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
    }
    private void startCameraX(ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();

        cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview,imageCapture);
    }
    private void hasPermission(String permission, int code) {
        if (ContextCompat.checkSelfPermission(Camera.this, permission) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(Camera.this, new String[]{permission}, code);
        else
            Toast.makeText(Camera.this, "Permission already granted", Toast.LENGTH_SHORT).show();
    }

    private Executor getExecutor() {
        return ContextCompat.getMainExecutor(Camera.this);
    }
}
