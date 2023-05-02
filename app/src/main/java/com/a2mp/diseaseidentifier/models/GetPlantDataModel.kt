package com.a2mp.diseaseidentifier.models

class GetPlantDataModel(
    val is_weed : Boolean?,
    val climate: ClimateModel?
) {
}

data class ClimateModel(
    val difficulty:String? = "medium",
    val light : String? = "Full sun",
    val absolute_min_temp : String? = "4",
    val poison_type: String? = "None",
    val humidity : String? = "50%",
)