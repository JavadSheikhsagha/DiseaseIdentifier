package com.a2mp.diseaseidentifier.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DiseaseResponseModel(
    val images: List<ImageModel>?,
    val is_plant_probability : Float,
    @SerializedName("health_assessment") val healthAssessment: HealthAssessmentModel?,
) : Parcelable

@Parcelize
data class ImageModel(
    @SerializedName("file_name") val fileName: String?, val url: String?
) : Parcelable

@Parcelize
data class HealthAssessmentModel(
    val diseases: List<DiseaseModel>?,
    val is_healthy : Boolean?,
    val is_healthy_probability : Float?
) : Parcelable

@Parcelize
data class DiseaseModel(
    val name: String?,
    val probability: Float?,
    val similar_images: List<DiseaseImageModel>?,
    val disease_details: DiseaseDetailsModel?,
) : Parcelable

@Parcelize
data class DiseaseImageModel(
    val url: String?, val url_small: String?
) : Parcelable

@Parcelize
data class DiseaseDetailsModel(
    val local_name: String?,
    val description: String?,
    val url: String?,
    val treatment: TreatmentModel?
) : Parcelable

@Parcelize
data class TreatmentModel(
    val biological: List<String>?, val prevention: List<String>?, val classification: List<String>?
) : Parcelable