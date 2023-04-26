package com.a2mp.diseaseidentifier.views

import android.content.Intent
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

        binding.cardDisease1.setOnClickListener {
            startActivity(Intent(this, DiseaseSingleActivity::class.java))
        }

        binding.cardDisease2.setOnClickListener {
            startActivity(Intent(this, DiseaseSingleActivity::class.java))
        }

        binding.cardDisease3.setOnClickListener {
            startActivity(Intent(this, DiseaseSingleActivity::class.java))
        }

    }
}