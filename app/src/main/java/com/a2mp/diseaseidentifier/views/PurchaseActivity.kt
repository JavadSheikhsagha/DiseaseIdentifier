package com.a2mp.diseaseidentifier.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.a2mp.diseaseidentifier.databinding.ActivityPurchaseBinding


class PurchaseActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPurchaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupViews()
    }

    private fun setupViews() {

        val mySpannableString = SpannableString("Privacy Policy")
        mySpannableString.setSpan(UnderlineSpan(), 0, mySpannableString.length, 0)
        binding.txtPrivacyPolicy.text = mySpannableString

        val mySpannableString2 = SpannableString("Terms Of Service")
        mySpannableString2.setSpan(UnderlineSpan(), 0, mySpannableString2.length, 0)
        binding.txtTermsOfService.text = mySpannableString2

        binding.txtPrivacyPolicy.setOnClickListener {
            val url = "https://pages.flycricket.io/plant-disease/privacy.html"
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                "http://$url"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

        binding.txtTermsOfService.setOnClickListener {
            val url = "https://pages.flycricket.io/plant-disease/terms.html"
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                "http://$url"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }
}