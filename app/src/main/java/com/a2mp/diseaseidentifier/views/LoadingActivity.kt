package com.a2mp.diseaseidentifier.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityLoadingBinding
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel

class LoadingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoadingBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

    }

    private fun setupViews() {


    }
}