package com.a2mp.diseaseidentifier.views

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityPlantSingleBinding
import com.a2mp.diseaseidentifier.models.DiseaseResponseModel
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.viewmodel.imageBitmap
import com.a2mp.diseaseidentifier.viewmodel.plant_name
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class PlantSingleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlantSingleBinding

    private val viewModel by viewModels<MainViewModel>()

    private var DISEASE_MODEL : DiseaseResponseModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantSingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getDataOfHealth()

        setupViews()

        getPlantData()
    }

    private fun getPlantData() {

        viewModel.getSinglePlant(plant_name.toString())

        viewModel.getPlantDataLiveData.observe(this) {

            try {

                binding.txtPlantLighting.text = if (it?.get(0)?.climate?.light == null) "Part Sun" else it[0]?.climate?.light?.capitalize()
                binding.txtPlantDifficulty.text = if (it?.get(0)?.climate?.difficulty == null) "Medium%" else it[0]?.climate?.difficulty?.capitalize()
                binding.txtPlantTempreture.text = if (it?.get(0)?.climate?.absolute_min_temp == null) "4" else it[0]?.climate?.absolute_min_temp?.capitalize()
            }catch (e:java.lang.Exception) {

            }

        }
    }

    private fun getDataOfHealth() {

        DISEASE_MODEL = intent.extras?.getParcelable("disease")

        binding.txtPlantCommonName.text = intent.extras?.getString("plant_name")
        binding.txtPlantName.text = plant_name

    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {

        binding.profileImage.setImageBitmap(imageBitmap)

        binding.btnBack.setOnClickListener {
            imageBitmap = null
            finish()
        }

        binding.btnSeeDiseases.setOnClickListener {
            if (AppSharedPref.getIsPurchased(this)) {
                val intent = Intent(this, PlantInfoActivity::class.java)
                intent.putExtra("disease", DISEASE_MODEL)
                startActivity(intent)
            } else {
                startActivity(Intent(this, PurchaseActivity::class.java)
                    .apply {
                        putExtra("disease", DISEASE_MODEL)
                    })
            }
        }

        DISEASE_MODEL?.let {

            binding.txtAccuracy.text =
                "${((it.is_plant_probability.times(100)).toInt())}% Accuracy"

            if (it.healthAssessment!!.is_healthy == false) {

                binding.txtPlantCondition.text = "Your plant has a disease."
                Picasso.get().load(R.drawable.imgunhealthy0).into(binding.imgPlantCondition)
            } else {
                binding.btnSeeDiseases.visibility = View.GONE
            }


            if (AppSharedPref.getIsPurchased(this)) {
                binding.btnPremium.visibility = View.GONE
            }
        }

        binding.btnTakesnap.setOnClickListener {
            finish()
        }

        binding.btnPremium.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        if (AppSharedPref.getIsPurchased(this)) {
            binding.btnPremium.visibility = View.GONE
        }
    }


    private fun implementAnimation(isHealthy:Boolean) {

        CoroutineScope(Dispatchers.IO).launch {
            val imageList: List<Bitmap>
            if (!isHealthy) {
                imageList = listOf(
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy0
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy1
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy2
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy3
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy4
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy5
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy6
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy7
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy8
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy9
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy10
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy11
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy12
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy13
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy14
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy15
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy16
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy17
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy18
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy19
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy20
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy21
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy22
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imgunhealthy23
                    ),
                )
            } else {
                imageList = listOf(
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy0
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy1
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy2
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy3
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy4
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy5
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy6
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy7
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy8
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy9
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy10
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy11
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy12
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy13
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy14
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy15
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantSingleActivity.resources, R.drawable.imghealthy16
                    ),
                )
            }
            imageList.forEach {
                withContext(Dispatchers.Main) {
                    binding.imgPlantCondition.setImageBitmap(it)
                }
                delay(5)
            }
        }
    }
}