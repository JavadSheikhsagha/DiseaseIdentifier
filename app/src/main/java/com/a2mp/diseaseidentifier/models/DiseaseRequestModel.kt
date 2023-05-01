package com.a2mp.diseaseidentifier.models

data class DiseaseRequestModel(
    var images: List<String>,
    var disease_details : List<String>,
    var modifiers : List<String>
) {
}