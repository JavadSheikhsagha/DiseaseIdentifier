package com.a2mp.diseaseidentifier.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityLoadingBinding
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel

class LoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

    }

    private fun setupViews() {

        startOuter()
        startInner()
    }

    fun startInner() {
        binding.imgLoadingInner.animate()
            .setDuration(800)
            .rotationBy(220f)
            .withEndAction {
                endInner()
            }
            .start()
    }

    fun startOuter() {
        binding.imgLoadingOuter.animate()
            .setDuration(500)
            .rotationBy(180f)
            .withEndAction {
                endOuter()
            }
            .start()
    }

    fun endInner() {
        binding.imgLoadingInner.animate()
            .setDuration(400)
            .rotationBy(70f)
            .withEndAction {
                startInner()
            }
            .start()
    }

    fun endOuter() {
        binding.imgLoadingOuter.animate()
            .setDuration(800)
            .rotationBy(-30f)
            .withEndAction {
                startOuter()
            }
            .start()
    }


}