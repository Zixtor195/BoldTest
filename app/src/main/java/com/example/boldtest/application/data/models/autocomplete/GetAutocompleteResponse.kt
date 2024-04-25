package com.example.boldtest.application.data.models.autocomplete

import com.google.gson.annotations.SerializedName

class LocationResponse (
    @SerializedName("name") val locationName: String = "",
    @SerializedName("country") val locationCountry: String = "",
)