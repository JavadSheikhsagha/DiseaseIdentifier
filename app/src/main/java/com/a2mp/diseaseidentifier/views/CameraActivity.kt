package com.a2mp.diseaseidentifier.views

import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityCameraBinding
import com.otaliastudios.cameraview.CameraView
import de.markusfisch.android.cameraview.widget.CameraView.findCameraId

class CameraActivity : AppCompatActivity() {


    lateinit var binding : ActivityCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

    }

    private fun setupViews() {


        binding.cameraView.setOnCameraListener(object : de.markusfisch.android.cameraview.widget.CameraView.OnCameraListener{
            override fun onConfigureParameters(parameters: Camera.Parameters?) {
                Log.i("LOG11", "onConfigureParameters: ERROR")
            }

            override fun onCameraError() {
                Log.i("LOG11", "onCameraError: ERROR")
            }

            override fun onCameraReady(camera: Camera?) {
                Log.i("LOG11", "onCameraReady: ERROR")

            }

            override fun onPreviewStarted(camera: Camera?) {
                Log.i("LOG11", "onPreviewStarted: ERROR")

            }

            override fun onCameraStopping(camera: Camera?) {
                Log.i("LOG11", "onCameraStopping: ERROR")

            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.cameraView.openAsync(
            findCameraId(
                Camera.CameraInfo.CAMERA_FACING_BACK
            )
        )
    }

    override fun onPause() {
        super.onPause()
        binding.cameraView.close()
    }
}