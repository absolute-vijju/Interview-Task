package com.developer.hyperlink.api

import com.developer.hyperlink.models.MemesResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("get_memes")
    suspend fun getMemes(): Response<MemesResponse>
}