package com.a2mp.diseaseidentifier.views

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityPlantInfoBinding
import com.a2mp.diseaseidentifier.models.DiseaseResponseModel
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import com.a2mp.diseaseidentifier.viewmodel.imageBitmap
import kotlinx.coroutines.*

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

            if (DISEASE_MODEL!!.healthAssessment!!.diseases!!.size > 3) {
                layoutManager = GridLayoutManager(this@PlantInfoActivity, 3)
            } else if (DISEASE_MODEL!!.healthAssessment!!.diseases!!.size == 2) {
                layoutManager = GridLayoutManager(this@PlantInfoActivity, 2)
            } else {
                layoutManager = GridLayoutManager(this@PlantInfoActivity, 1)
            }
            adapter = RvDiseasesAdapter(DISEASE_MODEL!!.healthAssessment!!.diseases!!)
        }

        binding.imgPlantInfoImage.setImageBitmap(imageBitmap)

    }


}