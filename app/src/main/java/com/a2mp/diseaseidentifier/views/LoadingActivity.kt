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

    private var rewardedAd: RewardedAd? = null
    private var isCanceled = false

    private lateinit var binding: ActivityLoadingBinding

    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setupViews()

        if (!AppSharedPref.getIsPurchased(this)) {
            loadRewardedAd {

            }
        }

        imageBitmap?.let {
            Log.i("LOG25", "onCreate: isnt null")

            viewModel.identify(it)

        }


        getHealthForPlant { plantName, disease ->
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
                        startActivity(Intent(this, ErrorActivity::class.java))
                        finish()
                    }
                }
            } else {
                rewardedAd?.let { ad ->
                    ad.show(this) { rewardItem ->
                        Log.d("LOG35", "User earned the reward.")
                        Log.i("LOG26", "onCreate: $disease")
                        adCallback(plantName, disease)
                    }
                } ?:
                run {
                    Log.d("LOG35", "The rewarded ad wasn't ready yet.")
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
                            startActivity(Intent(this, ErrorActivity::class.java))
                            finish()
                        }

                    }
                }
            }

        }


    }

    private fun adCallback(plantName: IdentifyModel, disease: DiseaseResponseModel?) {

        rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d("LOG37", "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d("LOG37", "Ad dismissed fullscreen content.")
                if (disease?.images != null) {

                    val intent = Intent(this@LoadingActivity, PlantSingleActivity::class.java)
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
                        startActivity(Intent(this@LoadingActivity, ErrorActivity::class.java))
                        finish()
                    }

                }
                rewardedAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d("LOG37", "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d("LOG37", "Ad showed fullscreen content.")
            }
        }
    }

    private fun getHealthForPlant(function: (plantName:IdentifyModel, disease:DiseaseResponseModel?) -> Unit) {

        viewModel.identifyModel.observe(this) { identify ->


            if (identify?.bestMatch != null) {
                viewModel.healthStatusForModel.observe(this) {
                    function(identify, it)
                }
            } else {
                Log.i("LOG27", "onCreate: ")
                if (!isCanceled)
                    startActivity(Intent(this, ErrorActivity::class.java))
                finish()
            }
        }
    }

    private fun loadRewardedAd(function: () -> Unit) {

        var adRequest = AdRequest.Builder().build()
        RewardedAd.load(this,"ca-app-pub-6545436330357450/1685792548", adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("LOG34", adError.toString())
                rewardedAd = null
            }

            override fun onAdLoaded(ad: RewardedAd) {
                Log.d("LOG34", "Ad was loaded.")
                rewardedAd = ad
            }
        })
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