package com.developer.hyperlink.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developer.hyperlink.api.WebService
import com.developer.hyperlink.models.MemesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemesRepository @Inject constructor(private val webServices: WebService) {

    fun callApiFromRepo(): LiveData<MemesResponse> {

        val apiResponse = MutableLiveData<MemesResponse>()

        CoroutineScope(Dispatchers.IO).launch {

            val response = webServices.getMemes()
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    apiResponse.value = response.body()
                }
            }
        }
        return apiResponse
    }
}