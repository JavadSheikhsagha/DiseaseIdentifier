package com.a2mp.diseaseidentifier.models


class IdentifyModel(
    val statusCode:Int?,
//    val results:JSONArray?,
    val bestMatch : String?
) {
}

data class PlantNetIdentifyPlantModel(
    val commonNames: String?,
    val name:String?
)