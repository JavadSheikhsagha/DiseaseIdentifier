package com.a2mp.diseaseidentifier.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.a2mp.diseaseidentifier.databinding.ActivityPurchaseBinding
import com.a2mp.diseaseidentifier.models.DiseaseResponseModel
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.a2mp.diseaseidentifier.views.camera.userCameBack
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.BillingProcessor.IPurchasesResponseListener
import com.anjlab.android.iab.v3.BillingProcessor.ISkuDetailsResponseListener
import com.anjlab.android.iab.v3.PurchaseInfo
import com.anjlab.android.iab.v3.SkuDetails


class PurchaseActivity : AppCompatActivity(), BillingProcessor.IBillingHandler {

    var bp: BillingProcessor? = null


    private var DISEASE_MODEL: DiseaseResponseModel? = null

    private lateinit var binding: ActivityPurchaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userCameBack = true


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getDataOfHealth()

        bp = BillingProcessor(this, "YOUR LICENSE KEY FROM GOOGLE PLAY CONSOLE HERE", this)
        bp?.initialize()

        setupViews()

    }

    private fun getDataOfHealth() {

        DISEASE_MODEL = intent.extras?.getParcelable("disease")

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

        binding.imgClose.setOnClickListener {
            finish()
        }

        binding.btnFreeTrial.setOnClickListener {
            finish()
        }

        binding.cardLifetime.setOnClickListener {
            bp?.purchase(this, "YOUR SUBSCRIPTION ID FROM GOOGLE PLAY CONSOLE HERE");
        }

        binding.cardWeekly.setOnClickListener {
            bp?.subscribe(this, "plant.weekly.sub");
        }

        binding.cardYearly.setOnClickListener {
            bp?.subscribe(this, "YOUR SUBSCRIPTION ID FROM GOOGLE PLAY CONSOLE HERE");
        }

        binding.txtRestore.setOnClickListener {
            AppSharedPref.setIsPurchased(this@PurchaseActivity,true)

            bp?.loadOwnedPurchasesFromGoogleAsync(object : IPurchasesResponseListener {
                override fun onPurchasesSuccess() {
                    Log.i("LOG29", "onPurchasesSuccess: success restore")
                    finish()
                    DISEASE_MODEL?.let {
                        val intent = Intent(this@PurchaseActivity, PlantInfoActivity::class.java)
                        intent.putExtra("disease", DISEASE_MODEL)
                        startActivity(intent)
                    }
                }

                override fun onPurchasesError() {
                    Log.i("LOG29", "onPurchasesSuccess: error restore")
                    Toast.makeText(
                        this@PurchaseActivity,
                        "Restore didn't succeed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        purchaseDetails()
    }

    private fun purchaseDetails() {

        bp?.getPurchaseListingDetailsAsync("lifetime", object : ISkuDetailsResponseListener {
            override fun onSkuDetailsResponse(products: MutableList<SkuDetails>?) {
                binding.txtLifetimePrice.text = products?.get(0)?.priceText
            }
            override fun onSkuDetailsError(error: String?) {

            }
        })
        bp?.getSubscriptionListingDetailsAsync("yearly", object : ISkuDetailsResponseListener {
            override fun onSkuDetailsResponse(products: MutableList<SkuDetails>?) {
                binding.txtYearlyPrice.text = products?.get(0)?.priceText
            }
            override fun onSkuDetailsError(error: String?) {

            }
        })
        bp?.getSubscriptionListingDetailsAsync("weekly", object : ISkuDetailsResponseListener {
            override fun onSkuDetailsResponse(products: MutableList<SkuDetails>?) {
                binding.txtWeeklyPrice.text = products?.get(0)?.priceText
            }
            override fun onSkuDetailsError(error: String?) {

            }
        })
    }

    override fun onBillingInitialized() {}

    override fun onProductPurchased(productId: String, details: PurchaseInfo?) {
        Toast.makeText(this, "Did Purchase.", Toast.LENGTH_SHORT).show()
        AppSharedPref.setIsPurchased(this,true)
        finish()
        DISEASE_MODEL?.let {
            val intent = Intent(this@PurchaseActivity, PlantInfoActivity::class.java)
            intent.putExtra("disease", DISEASE_MODEL)
            startActivity(intent)
        }
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {

    }

    override fun onPurchaseHistoryRestored() {}

    override fun onDestroy() {
        if (bp != null) {
            bp!!.release()
        }
        super.onDestroy()
    }
}