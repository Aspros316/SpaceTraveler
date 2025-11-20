package com.example.spacetraveler.data.repository.retrofit

import com.example.spacetraveler.data.repository.model.RemoteSpaceRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SpaceWebService {

    @Headers("Content-Type: application/json")
    @POST("v1/travelers")
    suspend fun createTraveler(@Body request: RemoteSpaceRequest)
}