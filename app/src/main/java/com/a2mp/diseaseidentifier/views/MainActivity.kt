package com.a2mp.diseaseidentifier.views

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityMainBinding
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.views.camera.Camera2Activity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        isFirstTime()


        setupViews()

        if (!AppSharedPref.getIsPurchased(this)) {
            loadAd()
        }

    }

    private fun loadAd() {

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun setupViews() {

        binding.btnOnBoardingActivityLetsgo.setOnClickListener {
            startActivity(Intent(this, Camera2Activity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        if (AppSharedPref.getIsPurchased(this)) {
            binding.btnPremium.visibility = View.GONE
        }

        binding.btnPremium.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }

//        implementMainAnimation()
    }

    private fun implementMainAnimation() {


        CoroutineScope(Dispatchers.IO)
            .launch {
                val imageList = listOf(

                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp0),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp1),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp2),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp3),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp4),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp5),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp6),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp7),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp8),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp9),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp10),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp11),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp12),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp13),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp14),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp15),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp16),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp17),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp18),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp19),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp20),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp21),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp22),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp23),
                    BitmapFactory.decodeResource(this@MainActivity.resources, R.drawable.fp24),
                )
                imageList.forEach {
                    withContext(Dispatchers.Main) {
                        binding.imgMainActivityLogo.setImageBitmap(it)
                    }
                    delay(5)
                }

            }
    }

    private fun isFirstTime() {

        if (getIsFirstTime()) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            setIsFirstTimeTrue()
        } else {
            // do nothing
        }
    }

    private fun getIsFirstTime(): Boolean {

        return this.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
            .getBoolean("isFirstTime", true)
    }

    private fun setIsFirstTimeTrue() {
        this.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("isFirstTime", false)
            .apply()
    }

    override fun onResume() {
        super.onResume()
        if (AppSharedPref.getIsPurchased(this)) {
            binding.btnPremium.visibility = View.GONE
            binding.adView.visibility = View.GONE
        }
    }


}