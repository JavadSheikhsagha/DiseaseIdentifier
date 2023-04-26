package com.a2mp.diseaseidentifier.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityPlantInfoBinding

class PlantInfoActivity : AppCompatActivity() {


    lateinit var binding : ActivityPlantInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

    }

    private fun setupViews() {

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}