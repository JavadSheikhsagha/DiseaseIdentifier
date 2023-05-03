package com.a2mp.diseaseidentifier.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.a2mp.diseaseidentifier.databinding.ActivityPlantInfoBinding
import com.a2mp.diseaseidentifier.models.DiseaseResponseModel
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.viewmodel.imageBitmap
import com.a2mp.diseaseidentifier.viewmodel.plant_name

class PlantInfoActivity : AppCompatActivity() {


    lateinit var binding: ActivityPlantInfoBinding

    private var isHealthy: Boolean = false

    private val viewModel by viewModels<MainViewModel>()

    private var DISEASE_MODEL: DiseaseResponseModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataOfHealth()

        setupViews()

        getPlantData()
    }

    private fun getPlantData() {

        viewModel.getSinglePlant(plant_name.toString())

        viewModel.getPlantDataLiveData.observe(this) {

            binding.txtPlantLighting.text = if (it?.get(0)?.climate?.light == null) "Part Sun" else it.get(0).climate?.light?.capitalize()
            binding.txtPlantHumidity.text = if (it?.get(0)?.climate?.humidity == null) "50%" else it.get(0).climate?.humidity
            binding.txtPlantTemperature.text = if (it?.get(0)?.climate?.absolute_min_temp == null) "4" else it.get(0).climate?.absolute_min_temp
            binding.txtPlantDifficulty.text = if (it?.get(0)?.climate?.difficulty == null) "MEDIUM" else it.get(0).climate?.difficulty?.capitalize()
            binding.txtPlantToxic.text = if (it?.get(0)?.climate?.poison_type == null) "NONE" else it.get(0).climate?.poison_type?.capitalize()
            binding.txtPlantIsWeed.text = it?.get(0)?.is_weed.toString().capitalize()

        }
    }

    private fun getDataOfHealth() {

        DISEASE_MODEL = intent.extras?.getParcelable("disease")

        if (DISEASE_MODEL == null) {
            startActivity(Intent(this, ErrorActivity::class.java))
            finish()
        }
    }

    private fun setupViews() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.rvDiseases.apply {

            layoutManager = if (DISEASE_MODEL!!.healthAssessment!!.diseases!!.size > 3) {
                GridLayoutManager(this@PlantInfoActivity, 3)
            } else if (DISEASE_MODEL!!.healthAssessment!!.diseases!!.size == 2) {
                GridLayoutManager(this@PlantInfoActivity, 2)
            } else {
                GridLayoutManager(this@PlantInfoActivity, 1)
            }
            adapter = RvDiseasesAdapter(DISEASE_MODEL!!.healthAssessment!!.diseases!!) {
                startActivity(
                    Intent(
                        this@PlantInfoActivity,
                        DiseaseSingleActivity::class.java
                    ).apply {
                        putExtra("disease", it)
                    })
            }
        }

        binding.imgPlantInfoImage.setImageBitmap(imageBitmap)
        if (imageBitmap == null) {
            Log.i("LOG31", "setupViews: its null")
        }

        if (AppSharedPref.getIsPurchased(this)) {
            binding.btnPremium.visibility = View.GONE
        }

        binding.btnPremium.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }

    }


}