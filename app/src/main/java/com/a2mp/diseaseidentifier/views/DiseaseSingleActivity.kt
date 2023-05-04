package com.a2mp.diseaseidentifier.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityDiseaseSingleBinding
import com.a2mp.diseaseidentifier.models.DiseaseModel
import com.a2mp.diseaseidentifier.repos.AppSharedPref
import com.a2mp.diseaseidentifier.viewmodel.imageBitmap
import com.squareup.picasso.Picasso

class DiseaseSingleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDiseaseSingleBinding

    private var DISEASE_MODEL : DiseaseModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseSingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getHealthData()

        setupViews()



    }

    private fun getHealthData() {

        DISEASE_MODEL = intent.extras?.getParcelable("disease")


    }

    private fun setupViews() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.btnPremium.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }

        if (AppSharedPref.getIsPurchased(this)) {
            binding.btnPremium.visibility = View.GONE
        }

        binding.txtDiseaseDesc.text = DISEASE_MODEL?.disease_details?.description
        binding.txtDiseaseName.text = DISEASE_MODEL?.name

        if (DISEASE_MODEL?.disease_details?.treatment?.chemical == null) {
            binding.cardChemical.visibility = View.GONE
        } else {
            binding.txtChemical.text = getStringFromArray(DISEASE_MODEL?.disease_details?.treatment?.chemical)
        }

        if (DISEASE_MODEL?.disease_details?.treatment?.biological == null) {
            binding.cardBiological.visibility = View.GONE
        } else {
            binding.txtBiological.text = getStringFromArray(DISEASE_MODEL?.disease_details?.treatment?.biological)
        }

        if (DISEASE_MODEL?.disease_details?.treatment?.prevention == null) {
            binding.cardBiological.visibility = View.GONE
        } else {
            binding.txtBiological.text = getStringFromArray(DISEASE_MODEL?.disease_details?.treatment?.prevention)
        }


        Picasso.get().load(DISEASE_MODEL?.disease_details?.url).into(binding.imgPlantInfoImage)

    }

    private fun getStringFromArray(txt: List<String>?): CharSequence {

        var string = ""
        txt?.forEach {
            string += "$it\n"
        }

        return string
    }
}