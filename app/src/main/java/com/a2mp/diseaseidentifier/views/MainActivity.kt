package com.a2mp.diseaseidentifier.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        isFirstTime()

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