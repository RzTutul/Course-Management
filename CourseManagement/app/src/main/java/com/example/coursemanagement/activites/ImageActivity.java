package com.example.coursemanagement.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.coursemanagement.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 999;
    private static final int STORAGE_REQUEST_CODE = 123;
    private String currentPhotoPath;



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    public void SelectImageFromGallary(View view) {


        if (isStorageRequestAccepted()){
            dispatchCameraIntent();
        }

    }

    private void dispatchCameraIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
        // Ensure that there's a camera activity to handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.coursemanagement",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            }
        }

    }

    private boolean isStorageRequestAccepted(){
        String[] permissionList = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            requestPermissions(permissionList, STORAGE_REQUEST_CODE);
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case STORAGE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    dispatchCameraIntent();
                }else{
                    //show a dialog and explain user why camera permission is necessary
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
