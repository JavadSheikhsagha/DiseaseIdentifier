package com.a2mp.diseaseidentifier.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.a2mp.diseaseidentifier.databinding.ActivityLoadingBinding
import com.a2mp.diseaseidentifier.models.DiseaseResponseModel
import com.a2mp.diseaseidentifier.models.IdentifyModel
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.viewmodel.imageBitmap
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback


class LoadingActivity : AppCompatActivity() {

    private var isCanceled = false

    private lateinit var binding: ActivityLoadingBinding

    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setupViews()

        imageBitmap?.let {
            Log.i("LOG25", "onCreate: isnt null")

            viewModel.identify(it)

        }


        getHealthForPlant { plantName, disease ->

            if (viewModel.errorMessage == "") {
                Log.i("LOG28", "onCreate: ${disease?.is_plant_probability!!}")
                Log.i("LOG28", "onCreate: ${disease?.is_plant_probability!! < 0.60}")
                if (disease?.is_plant_probability!! < 0.60) {
                    startActivity(Intent(this, ErrorActivity::class.java)
                        .apply {
                            putExtra("msg","No Plant identified")
                        })
                    finish()
                    Log.i("LOG28", "onCreate: ${disease?.is_plant_probability!! < 0.60}")
                } else
                if (AppSharedPref.getIsPurchased(this)) {
                    if (disease?.images != null) {

                        val intent = Intent(this, PlantSingleActivity::class.java)
                        intent.putExtra("disease", disease)
                        intent.putExtra(
                            "plant_name",
                            getSingleStringFromCommonNames(plantName.results[0].species!!.commonNames)
                        )
                        if (!isCanceled) {
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Log.i("LOG28", "onCreate: ")
                        if (!isCanceled) {
                            startActivity(Intent(this, ErrorActivity::class.java)
                                .apply {
                                    putExtra("msg",viewModel.errorMessage)
                                })
                            finish()
                        }
                    }
                }

            } else {
                startActivity(Intent(this, ErrorActivity::class.java).apply {
                    putExtra("msg",viewModel.errorMessage)
                })
                finish()
            }

        }


    }

    private fun getHealthForPlant(function: (plantName:IdentifyModel, disease:DiseaseResponseModel?) -> Unit) {

        viewModel.identifyModel.observe(this) { identify ->


            if (viewModel.errorMessage == "") {
                if (identify?.bestMatch != null) {
                    viewModel.healthStatusForModel.observe(this) {
                        function(identify, it)
                    }
                } else {
                    Log.i("LOG27", "onCreate: ")
                    if (!isCanceled) {
                        startActivity(Intent(this, ErrorActivity::class.java))
                        finish()
                    }

                }
            } else {
                startActivity(Intent(this, ErrorActivity::class.java).apply {
                    putExtra("msg",viewModel.errorMessage)
                })
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

        binding.cancelButton.setOnClickListener {
            isCanceled = true
            finish()
        }

        val content = SpannableString("Cancel")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.cancelButton.text = content

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