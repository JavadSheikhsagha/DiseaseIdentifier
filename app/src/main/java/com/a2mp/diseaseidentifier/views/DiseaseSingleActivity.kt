package com.a2mp.diseaseidentifier.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityDiseaseSingleBinding

class DiseaseSingleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDiseaseSingleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseSingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

    }

    private fun setupViews() {

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}