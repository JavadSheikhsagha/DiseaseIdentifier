package com.a2mp.diseaseidentifier.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.a2mp.diseaseidentifier.databinding.ActivityErrorBinding
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.a2mp.diseaseidentifier.views.camera.Camera2Activity

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
            startActivity(Intent(this, Camera2Activity::class.java))
            finish()
        }

        binding.btnPremium.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }

        if (AppSharedPref.getIsPurchased(this)) {
            binding.btnPremium.visibility = View.GONE
        }
    }
}