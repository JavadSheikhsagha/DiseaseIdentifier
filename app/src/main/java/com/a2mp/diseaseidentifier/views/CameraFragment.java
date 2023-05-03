package com.a2mp.diseaseidentifier.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.a2mp.diseaseidentifier.R;
import com.a2mp.diseaseidentifier.views.camera.Camera;


public class CameraFragment extends Fragment {

    private ImageView picFrame;
    private Camera camera;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera_new, container, false);
        picFrame = (ImageView) rootView.findViewById(R.id.picFrame);

        picFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    camera.takePicture();
                } catch (IllegalAccessException e) {
                    Log.i("LOG33", "onCreateView: Did Create");

                }
            }
        });
        Log.i("LOG32", "onCreateView: Did Create");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        camera = new Camera.Builder()
                .resetToCorrectOrientation(true)
                .setTakePhotoRequestCode(1)
                .setDirectory("pics")
                .setName("ali_" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(75)
                .setImageHeight(1000)
                .build(this);
        try {
            camera.takePicture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Camera.REQUEST_TAKE_PHOTO) {
            Bitmap bitmap = camera.getCameraBitmap();
            if (bitmap != null) {
                picFrame.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Picture not taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        camera.deleteImage();
    }
}