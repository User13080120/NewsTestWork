package com.example.newstestwork.model

import com.google.gson.annotations.SerializedName

data class DataJson(
    @SerializedName("title")
    var title: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("img")
    var img: String?,
    @SerializedName("click_url")
    var click_url: String?,
    @SerializedName("time")
    var time: String?,
    @SerializedName("top")
    var top: String?
)
