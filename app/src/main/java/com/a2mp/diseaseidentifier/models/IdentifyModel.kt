package com.a2mp.diseaseidentifier.models


class IdentifyModel(
    val statusCode:Int?,
//    val results:JSONArray?,
    val bestMatch : String?,
    val results : List<IdentifyResultModel>
) {
}

data class PlantNetIdentifyPlantModel(
    val commonNames: String?,
    val name:String?
)

data class IdentifyResultModel(
    val species : SpeciesModel?
)

data class SpeciesModel(
    val commonNames : List<String>
)