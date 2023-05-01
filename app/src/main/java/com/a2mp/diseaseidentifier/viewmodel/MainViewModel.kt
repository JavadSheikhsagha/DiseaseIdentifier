package com.a2mp.diseaseidentifier.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.*
import com.a2mp.diseaseidentifier.models.DiseaseResponseModel
import com.a2mp.diseaseidentifier.models.IdentifyModel
import com.a2mp.diseaseidentifier.models.PlantNetIdentifyPlantModel
import com.a2mp.diseaseidentifier.repos.LocalRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var imageBitmap: Bitmap? = null

class MainViewModel(application: Application) : AndroidViewModel(application) {


    val repository = LocalRepository()

    val identifyModel : MutableLiveData<PlantNetIdentifyPlantModel?> = MutableLiveData()
    val healthStatusForModel : MutableLiveData<DiseaseResponseModel?> = MutableLiveData()


    fun identify(file: Bitmap) {

        viewModelScope.launch {

            repository.identify(file)
                .enqueue(object : Callback<IdentifyModel?> {
                    override fun onResponse(call: Call<IdentifyModel?>, response: Response<IdentifyModel?>) {
                        Log.i("LOG23", "onResponse: DID GET identify")

                        response.body()?.let {
                            val plantNetModel = PlantNetIdentifyPlantModel(
                                name = it.bestMatch,
                                commonNames = it.bestMatch
                            )
                            identifyModel.postValue(plantNetModel)
                        }
                        getHealthStatusDirectFor()
                    }

                    override fun onFailure(call: Call<IdentifyModel?>, t: Throwable) {
                        Log.i("LOG23", "onResponse: DIDnt GET identify ${t.message}")
                        identifyModel.postValue(null)

                    }
                })

        }

    }

    fun getHealthStatusDirectFor() {

        viewModelScope.launch {

            repository.getHealthStatusDirectFor()
                .enqueue(object : Callback<DiseaseResponseModel?> {
                    override fun onResponse(call: Call<DiseaseResponseModel?>, response: Response<DiseaseResponseModel?>) {
                        Log.i("LOG24", "onResponse: DID getHealthStatusDirectFor")

                        response.body()?.let {
                            healthStatusForModel.postValue(it)
                        }
                    }

                    override fun onFailure(call: Call<DiseaseResponseModel?>, t: Throwable) {
                        Log.i("LOG24", "onResponse: DIDnt getHealthStatusDirectFor ${t.localizedMessage}")
                        healthStatusForModel.postValue(null)
                    }
                })
        }
    }

}