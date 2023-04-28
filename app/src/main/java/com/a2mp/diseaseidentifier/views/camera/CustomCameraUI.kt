package com.a2mp.diseaseidentifier.views.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.a2mp.diseaseidentifier.databinding.ActivityCustomCameraUiBinding
import io.reactivex.disposables.Disposable
import mobin.customcamera.core.Camera2
import mobin.customcamera.core.Converters

class CustomCameraUI : AppCompatActivity() {

    private  var camera2: Camera2? = null
    private var disposable: Disposable? = null

    private lateinit var binding : ActivityCustomCameraUiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomCameraUiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )

            initCamera2Api()
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 3)
            else initCamera2Api()

        }

    }

    private fun initCamera2Api() {

        camera2 = Camera2(this, binding.cameraView)
        camera2?.onResume()
        binding.ivRotateCamera.setOnClickListener {
            camera2?.switchCamera()
        }

        binding.ivCaptureImage.setOnClickListener { v ->
            camera2?.takePhoto {
                Toast.makeText(v.context, "Saving Picture", Toast.LENGTH_SHORT).show()
                disposable = Converters.convertBitmapToFile(it) { file ->
                    Toast.makeText(v.context, "Saved Picture Path ${file.path}", Toast.LENGTH_SHORT).show()
                }

            }


        }

        binding.ivCameraFlashOn.setOnClickListener {it ->
            camera2?.setFlash(Camera2.FLASH.ON)
            it.alpha = 1f
            binding.ivCameraFlashAuto.alpha = 0.4f
            binding.ivCameraFlashOff.alpha = 0.4f
        }


        binding.ivCameraFlashAuto.setOnClickListener { it ->
            binding.ivCameraFlashOff.alpha = 0.4f
            binding.ivCameraFlashOn.alpha = 0.4f
            it.alpha = 1f
            camera2?.setFlash(Camera2.FLASH.AUTO)
        }

        binding.ivCameraFlashOff.setOnClickListener {it ->
            camera2?.setFlash(Camera2.FLASH.OFF)
            it.alpha = 1f
            binding.ivCameraFlashOn.alpha = 0.4f
            binding.ivCameraFlashAuto.alpha = 0.4f

        }

    }


    override fun onPause() {
        //  cameraPreview.pauseCamera()
        camera2?.close()
        super.onPause()
    }

    override fun onResume() {
        // cameraPreview.resumeCamera()
        camera2?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        if (disposable != null)
            disposable!!.dispose()
        super.onDestroy()
    }


}
