package com.example.newstestwork.service

import com.example.newstestwork.model.DataJson
import retrofit2.http.GET

interface ApiService {
    @GET(" ")
    suspend fun getData(
    ): List<DataJson>
}