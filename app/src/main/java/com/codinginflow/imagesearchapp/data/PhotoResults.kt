package com.codinginflow.imagesearchapp.data

import com.google.gson.annotations.SerializedName

data class PhotoResults(
    @field:SerializedName("results")
    val results: List<ResultsItem>
)