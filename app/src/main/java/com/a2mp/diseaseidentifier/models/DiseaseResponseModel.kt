package com.a2mp.diseaseidentifier.models

import com.google.gson.annotations.SerializedName

class DiseaseResponseModel(
    val images:List<ImageModel>?,
    @SerializedName("health_assessment")
    val healthAssessment : HealthAssessmentModel?,
) {
}

data class ImageModel(
    @SerializedName("file_name")
    val fileName:String?,
    val url:String?
)

data class HealthAssessmentModel(
    val diseases : List<DiseaseModel>?
)

data class DiseaseModel(
    val name:String?,
    val probability : Float?,
    val similar_images : List<DiseaseImageModel>?,
    val disease_details : DiseaseDetailsModel?,
)

data class DiseaseImageModel(
    val url: String?,
    val url_small : String?
)

data class DiseaseDetailsModel(
    val local_name:String?,
    val description : String?,
    val url:String?,
    val treatment:TreatmentModel?
)

data class TreatmentModel(
    val biological:List<String>?,
    val prevention : List<String>?,
    val classification : List<String>?
)