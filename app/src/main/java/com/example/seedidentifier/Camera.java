package com.example.seedidentifier;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class Camera  extends AppCompatActivity {

    // Created the camera provider to be able to get an instance of a camera.
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    // Permission codes used for the app. Both STORAGE and CAMERA are required.
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    // Creating the button, preview and imageCapture use case.
    private PreviewView previewView;
    private Button snap;
    private ImageCapture imageCapture;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //Instance both the button and the preview.
        previewView = findViewById(R.id.previewView);
        previewView.setScaleType(PreviewView.ScaleType.FILL_START);
        snap = findViewById(R.id.button);

        // Run the check for the permissions required.
        // This is important because android 6+ now requires permissions to be set on the fly.
        hasPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
        hasPermission(Manifest.permission.CAMERA, STORAGE_PERMISSION_CODE);

        // Listener for the button, take a picture when button is clicked.
        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeImage();
            }
        });

        // Set up the camera provider to either start the camera or print the exceptions.
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
    //Function in charge of taking the photo and saving it.
    private void takeImage() {
        // Assign path to the folder the images will be stored in. If not there, create it.
        File imageDir = new File(getCodeCacheDir().getAbsolutePath() + "/images/");
        if(!imageDir.exists())
            if(!imageDir.mkdirs()){
                Toast.makeText(Camera.this,"Couldn't do path", Toast.LENGTH_SHORT).show();
            }

        // Set the name of the file plus the path that we created. In this case, a timestamp is used as a name.
        // temporary.
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        String newPath = imageDir.getAbsolutePath() +  "/" + timestamp + ".jpg";

        // Create a photo file with the new name and path.
        File photo = new File(newPath);

        // Take the picture and save it in the assigned path.
        imageCapture.takePicture(
                new ImageCapture.OutputFileOptions.Builder(photo).build(),
                getExecutor(),
                new ImageCapture.OnImageSavedCallback(){
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults){
                        Toast.makeText(Camera.this,"Photo saved successfully" , Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(newPath);  // Create bitmap of the image taken.
                        Bitmap correctBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getHeight(),bitmap.getHeight()); //Convert image from 4:3 to 1:1
                        Bitmap FinalBitmap = Bitmap.createScaledBitmap(correctBitmap,224,224,true); // Downscale image to 224,224.

                        /* Used to save the resulting bitmap.
                        FileOutputStream out = null;
                        try {
                            out = new FileOutputStream( imageDir.getAbsolutePath() +  "/" + timestamp + ".png");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        FinalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                         */

                        getResults(FinalBitmap);
                    }
                    @Override
                    public void onError(@NonNull ImageCaptureException e){
                        Toast.makeText(Camera.this,"Couldn't save photo" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    // Override to ask for the appropiate permission when needed.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check if the permission are available or not.

        //Check for CAMERA permission.
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(Camera.this, "Camera permission granted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Camera.this, "Camera permission denied", Toast.LENGTH_SHORT).show();
        }

        //Check for STORAGE permission.
        else if (requestCode == STORAGE_PERMISSION_CODE)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(Camera.this, "Storage permission granted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Camera.this, "Storage permission granted", Toast.LENGTH_SHORT).show();
    }
    // Function in charge of starting the camera, using the cameraProvider created before.
    private void startCameraX(ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll(); // Unbind all the use cases, to start from 0.

        // Assign the back camera using a cameraSelector.
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        // Create a preview its surface to the previewView instanced before.
        // Preview use case.
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        // Create the imageCapture that will be used to take the pictures.
        // ImageCapture use case.
        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();

        // Bind all the new use cases to the cameraProvider, this will start the camera.
        cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview,imageCapture);
    }
    // Function required to check if the app has the appropiate permissions to use the camera.
    private void hasPermission(String permission, int code) {
        // if the app has the permission name sent granted, then return its already granted.
        // Otherwise, request the permission.
        if (ContextCompat.checkSelfPermission(Camera.this, permission) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(Camera.this, new String[]{permission}, code);
        //else
            //Toast.makeText(Camera.this, "Permission already granted", Toast.LENGTH_SHORT).show();
    }
    // Executor, required for both the camera preview and the imageCapture use case.
    private Executor getExecutor() {
        return ContextCompat.getMainExecutor(Camera.this);
    }
    private void getResults(Bitmap bitmap){

            //Print progress
            Toast.makeText(Camera.this,"Started processing" , Toast.LENGTH_SHORT).show();
            // Instance HomeFragment in order to pass its seed_database to ImageAnalyzer.
            Seed_Database seeds = new Seed_Database();
            SeedPopulator populator = new SeedPopulator();
            populator.populate(seeds);
            ImageAnalyzer imageAnalyzer = new ImageAnalyzer(Camera.this, seeds);
            Seed seed = imageAnalyzer.analyzeImage(bitmap);

            if(seed != null){
                Intent intent = new Intent(Camera.this,Seed_Info.class);
                intent.putExtra("SEED_IMAGE", seed.getImage());
                intent.putExtra("SEED_NAME",seed.getSeedName());
                intent.putExtra("SEED_DESCRIPTION", seed.getDescription());
                startActivity(intent);
            }
            else{
                Toast.makeText(Camera.this,"Image not recognized" , Toast.LENGTH_SHORT).show();
            }
    }
}
