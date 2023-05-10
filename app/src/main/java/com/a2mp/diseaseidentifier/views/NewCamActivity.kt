package com.a2mp.diseaseidentifier.views


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityNewCamBinding


class NewCamActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewCamBinding
//    private var picFrame: ImageView? = null
//    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        supportFragmentManager.beginTransaction().add(R.id.container,CameraFragment()).commit()



    }
//
//    override fun onPostCreate(@Nullable savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//        camera = Camera.Builder()
//            .setDirectory("pics")
//            .setName("ali_" + System.currentTimeMillis())
//            .setImageFormat(Camera.IMAGE_JPEG)
//            .setCompression(75)
//            .setImageHeight(1000)
//            .build(this)
//        try {
//            camera!!.takePicture()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == Camera.REQUEST_TAKE_PHOTO) {
//            val bitmap = camera!!.getCameraBitmap()
//            if (bitmap != null) {
//                picFrame?.setImageBitmap(bitmap)
//            } else {
//                Toast.makeText(this.applicationContext, "Picture not taken!", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        camera!!.deleteImage()
//    }
}
