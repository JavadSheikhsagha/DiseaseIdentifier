package com.a2mp.diseaseidentifier.views

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.a2mp.diseaseidentifier.databinding.ActivityLoadingBinding
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import java.io.FileInputStream


class LoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

        val bmp = getBitmapData()

        if (bmp != null) {

            // request

        } else {
            finish()
        }

    }

    private fun getBitmapData() : Bitmap? {

        val bmp: Bitmap?
        val filename = intent.getStringExtra("image")
        try {
            val `is`: FileInputStream = openFileInput(filename)
            bmp = BitmapFactory.decodeStream(`is`)
            return  bmp

            `is`.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun setupViews() {

        startOuter()
        startInner()
    }

    private fun startInner() {
        binding.imgLoadingInner.animate()
            .setDuration(800)
            .rotationBy(220f)
            .withEndAction {
                endInner()
            }
            .start()
    }

    private fun startOuter() {
        binding.imgLoadingOuter.animate()
            .setDuration(500)
            .rotationBy(180f)
            .withEndAction {
                endOuter()
            }
            .start()
    }

    private fun endInner() {
        binding.imgLoadingInner.animate()
            .setDuration(400)
            .rotationBy(70f)
            .withEndAction {
                startInner()
            }
            .start()
    }

    private fun endOuter() {
        binding.imgLoadingOuter.animate()
            .setDuration(800)
            .rotationBy(-30f)
            .withEndAction {
                startOuter()
            }
            .start()
    }


}