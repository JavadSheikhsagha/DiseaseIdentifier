package com.a2mp.diseaseidentifier.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityPlantSingleBinding

class PlantSingleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlantSingleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantSingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()


    }

    private fun setupViews() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSeeDiseases.setOnClickListener {
            startActivity(Intent(this, PlantInfoActivity::class.java))
        }
    }
}