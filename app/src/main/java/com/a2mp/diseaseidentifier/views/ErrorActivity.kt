package com.a2mp.diseaseidentifier.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.a2mp.diseaseidentifier.databinding.ActivityErrorBinding
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.a2mp.diseaseidentifier.viewmodel.imageBitmap
import com.a2mp.diseaseidentifier.views.camera.Camera2Activity

class ErrorActivity : AppCompatActivity() {

    lateinit var binding : ActivityErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setupViews()

    }

    private fun setupViews() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnTakesnap.setOnClickListener {
            finish()
        }

        binding.btnPremium.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }

        binding.textView5.text = intent.extras?.getString("msg", "")

        binding.imgPlantInfoImage.setImageBitmap(imageBitmap)

        if (AppSharedPref.getIsPurchased(this)) {
            binding.btnPremium.visibility = View.GONE
        }
    }
}