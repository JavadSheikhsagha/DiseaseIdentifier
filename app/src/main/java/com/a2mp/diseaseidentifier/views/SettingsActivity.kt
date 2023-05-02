package com.a2mp.diseaseidentifier.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.a2mp.diseaseidentifier.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        binding.iconBack.setOnClickListener {
            finish()
        }

        binding.constPrivacy.setOnClickListener {
            val url = "https://pages.flycricket.io/plant-disease/privacy.html"
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                "http://$url"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

        binding.constTerms.setOnClickListener {
            val url = "https://pages.flycricket.io/plant-disease/terms.html"
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                "http://$url"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

        binding.constContactus.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("a2mpTeam@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Plant Companion")
            intent.putExtra(Intent.EXTRA_TEXT, "Write your idea for us...\n\n")
            startActivity(Intent.createChooser(intent, ""))
        }

        binding.btnPurchase.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }


    }
}