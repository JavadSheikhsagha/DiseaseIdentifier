package com.a2mp.diseaseidentifier.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.a2mp.diseaseidentifier.databinding.ActivityPurchaseBinding
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.BillingProcessor.IPurchasesResponseListener
import com.anjlab.android.iab.v3.BillingProcessor.ISkuDetailsResponseListener
import com.anjlab.android.iab.v3.PurchaseInfo
import com.anjlab.android.iab.v3.SkuDetails


class PurchaseActivity : AppCompatActivity(), BillingProcessor.IBillingHandler {

    var bp: BillingProcessor? = null


    private lateinit var binding: ActivityPurchaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bp = BillingProcessor(this, "YOUR LICENSE KEY FROM GOOGLE PLAY CONSOLE HERE", this)
        bp?.initialize()

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
            bp?.subscribe(this, "YOUR SUBSCRIPTION ID FROM GOOGLE PLAY CONSOLE HERE");
        }

        binding.cardYearly.setOnClickListener {
            bp?.subscribe(this, "YOUR SUBSCRIPTION ID FROM GOOGLE PLAY CONSOLE HERE");
        }

        binding.txtRestore.setOnClickListener {
            AppSharedPref.setIsPurchased(this@PurchaseActivity,true)

            finish()
            bp?.loadOwnedPurchasesFromGoogleAsync(object : IPurchasesResponseListener {
                override fun onPurchasesSuccess() {
                    Log.i("LOG29", "onPurchasesSuccess: success restore")
                }

                override fun onPurchasesError() {
                    Log.i("LOG29", "onPurchasesSuccess: error restore")
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

    override fun onBillingInitialized() {
        /*
    * Called when BillingProcessor was initialized and it's ready to purchase
    */
    }

    override fun onProductPurchased(productId: String, details: PurchaseInfo?) {

    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {

    }

    override fun onPurchaseHistoryRestored() {
        /*
    * Called when purchase history was restored and the list of all owned PRODUCT ID's
    * was loaded from Google Play
    */
    }

    override fun onDestroy() {
        if (bp != null) {
            bp!!.release()
        }
        super.onDestroy()
    }
}