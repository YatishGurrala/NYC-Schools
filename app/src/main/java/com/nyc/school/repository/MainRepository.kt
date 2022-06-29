package com.nyc.school.repository

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllImages() = retrofitService.getAllImages()
}