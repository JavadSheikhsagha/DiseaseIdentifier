package com.a2mp.diseaseidentifier.views

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.a2mp.diseaseidentifier.R
import com.a2mp.diseaseidentifier.databinding.ActivityPlantInfoBinding
import com.a2mp.diseaseidentifier.viewmodel.MainViewModel
import kotlinx.coroutines.*

class PlantInfoActivity : AppCompatActivity() {


    lateinit var binding: ActivityPlantInfoBinding

    private var isHealthy: Boolean = false

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

        getDataOfHealth()
    }

    private fun getDataOfHealth() {

        viewModel.healthStatusForModel.observe(this) {
            Log.i("LOG28", "getDataOfHealth: ${it?.healthAssessment?.diseases}")
        }
    }

    private fun setupViews() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.cardDisease1.setOnClickListener {
            startActivity(Intent(this, DiseaseSingleActivity::class.java))
        }

        binding.cardDisease2.setOnClickListener {
            startActivity(Intent(this, DiseaseSingleActivity::class.java))
        }

        binding.cardDisease3.setOnClickListener {
            startActivity(Intent(this, DiseaseSingleActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        implementAnimation()
    }

    private fun implementAnimation() {

        CoroutineScope(Dispatchers.IO).launch {
            var imageList: List<Bitmap>
            if (!isHealthy) {
                imageList = listOf(
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy0
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy1
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy2
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy3
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy4
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy5
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy6
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy7
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy8
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy9
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy10
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy11
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy12
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy13
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy14
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy15
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy16
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy17
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy18
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy19
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy20
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy21
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy22
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imgunhealthy23
                    ),
                )
            } else {
                imageList = listOf(
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy0
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy1
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy2
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy3
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy4
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy5
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy6
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy7
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy8
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy9
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy10
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy11
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy12
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy13
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy14
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy15
                    ),
                    BitmapFactory.decodeResource(
                        this@PlantInfoActivity.resources, R.drawable.imghealthy16
                    ),
                )
            }
            imageList.forEach {
                withContext(Dispatchers.Main) {
                    binding.imgPlantInfoImage.setImageBitmap(it)
                }
                delay(5)
            }
        }
    }
}