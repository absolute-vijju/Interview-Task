package com.developer.hyperlink.models

import com.google.gson.annotations.SerializedName

data class MemesResponse(
    @SerializedName("data")
    val memesData: Data,
    val success: Boolean
)

data class Data(
    val memes: List<Meme>
)

data class Meme(
    val box_count: Int,
    val height: Int,
    val id: String,
    val name: String,
    val url: String,
    val width: Int
)