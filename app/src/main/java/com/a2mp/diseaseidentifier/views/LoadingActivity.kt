package com.a2mp.diseaseidentifier.views

import android.R.attr.bitmap
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.a2mp.diseaseidentifier.databinding.ActivityLoadingBinding
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.viewmodel.imageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*


class LoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding

    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()


        imageBitmap?.let {
            Log.i("LOG25", "onCreate: isnt null")

            viewModel.identify(it)

        }


        viewModel.identifyModel.observe(this,) { identify ->

            if (identify?.bestMatch != null) {
                viewModel.healthStatusForModel.observe(this) {
                    Log.i("LOG26", "onCreate: $it")
                    if (it?.images != null) {
                        val intent = Intent(this, PlantSingleActivity::class.java)
                        intent.putExtra("disease", it)
                        intent.putExtra("plant_name", getSingleStringFromCommonNames(identify.results[0].species!!.commonNames))
                        startActivity(intent)
                        finish()
                    } else {
                        Log.i("LOG28", "onCreate: ")
                        startActivity(Intent(this, ErrorActivity::class.java))
                        finish()
                    }
                }
            } else {
                Log.i("LOG27", "onCreate: ")
                startActivity(Intent(this, ErrorActivity::class.java))
                finish()
            }
        }



    }

    private fun getSingleStringFromCommonNames(commonNames: List<String>): String? {

        var finalCommonNames = ""

        commonNames.forEach {
            finalCommonNames += "$it, "
        }
        return finalCommonNames
    }

    private fun setupViews() {

        startOuter()
        startInner()
    }

    private fun startInner() {
        binding.imgLoadingInner.animate()
            .setDuration(1000)
            .rotationBy(220f)
            .withEndAction {
                endInner()
            }
            .start()
    }

    private fun startOuter() {
        binding.imgLoadingOuter.animate()
            .setDuration(700)
            .rotationBy(180f)
            .withEndAction {
                endOuter()
            }
            .start()
    }

    private fun endInner() {
        binding.imgLoadingInner.animate()
            .setDuration(600)
            .rotationBy(70f)
            .withEndAction {
                startInner()
            }
            .start()
    }

    private fun endOuter() {
        binding.imgLoadingOuter.animate()
            .setDuration(1000)
            .rotationBy(-30f)
            .withEndAction {
                startOuter()
            }
            .start()
    }


}