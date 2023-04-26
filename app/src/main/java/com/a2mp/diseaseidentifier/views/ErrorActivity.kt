package com.a2mp.diseaseidentifier.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {

    lateinit var binding : ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

    }

    private fun setupViews() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnTakesnap.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
            finish()
        }
    }
}