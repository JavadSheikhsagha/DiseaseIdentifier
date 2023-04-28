package com.a2mp.diseaseidentifier.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.databinding.ActivityMainBinding
import com.a2mp.diseaseidentifier.views.camera.Camera2Activity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        isFirstTime()


        setupViews()

    }

    private fun setupViews() {

        binding.btnOnBoardingActivityLetsgo.setOnClickListener {
            startActivity(Intent(this, Camera2Activity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
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


}