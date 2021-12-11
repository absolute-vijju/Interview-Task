package com.developer.hyperlink.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.hyperlink.models.MemesResponse
import com.developer.hyperlink.repositories.MemesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemesViewModel @Inject constructor(val memesRepository: MemesRepository) : ViewModel() {

    var response = MutableLiveData<MemesResponse>()

    fun callApiFromViewModel() {
        response = memesRepository.callApiFromRepo() as MutableLiveData<MemesResponse>
    }

}