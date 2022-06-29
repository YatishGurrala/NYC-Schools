package com.nyc.school.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nyc.school.repository.MainRepository
import com.nyc.school.model.SchoolResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val schoolList = MutableLiveData<List<SchoolResponse>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllImages() {
        val response = repository.getAllImages()
        response.enqueue(object : Callback<List<SchoolResponse>> {
            override fun onResponse(
                call: Call<List<SchoolResponse>>,
                response: Response<List<SchoolResponse>>
            ) {
                schoolList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<SchoolResponse>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}