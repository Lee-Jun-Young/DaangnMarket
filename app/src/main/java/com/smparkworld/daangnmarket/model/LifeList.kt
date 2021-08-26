package com.smparkworld.daangnmarket.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class LifeList(
    // primary key
    @SerializedName("no")
    var no: Int,

    // 올린사람 아이디
    @SerializedName("id")
    var id: String,

    // 올린사람 닉네임
    @SerializedName("name")
    var name: String,

    // 올린 내용
    @SerializedName("content")
    var content: String,

    // 카테고리
    @SerializedName("category")
    var content_category: String,

    // 동네
    @SerializedName("location")
    var location: String,

    // 인증 횟수
    @SerializedName("certification_cnt")
    var certification_cnt: Int,

    // 작성날짜
    @SerializedName("write_time")
    var write_time: Date
)