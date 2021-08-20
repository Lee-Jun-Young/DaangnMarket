package com.smparkworld.daangnmarket.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class LifeList(
    @SerializedName("no")
    var no: Int,

    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("content")
    var content: String,

    @SerializedName("category")
    var content_category: String,

    @SerializedName("location")
    var location: String,

    @SerializedName("certification_cnt")
    var certification_cnt: Int,

    @SerializedName("write_time")
    var write_time: Date
)