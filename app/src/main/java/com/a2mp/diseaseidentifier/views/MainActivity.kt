package com.a2mp.diseaseidentifier.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.R

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isFirstTime()

    }

    private fun isFirstTime() {

        viewModel.getIsFirstTime().observe(this) {
            if (it) {
                startActivity(Intent(this, OnBoardingActivity::class.java))
                viewModel.setIsFirstTime()
            } else {
                // do nothing
            }
        }
    }


}