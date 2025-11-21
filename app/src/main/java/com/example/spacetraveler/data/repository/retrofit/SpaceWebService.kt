package com.example.spacetraveler.data.repository.retrofit

import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface SpaceWebService {

    @Headers("Content-Type: application/json")
    @POST("v1/travelers")
    suspend fun createTravel(@Body request: RemoteTravelRequest)

    @Headers("Content-Type: application/json")
    @GET("v1/travelers/{id}")
    suspend fun getTravelerById(
        @Path("id") id: Int
    ): RemoteTravelResponse

    @Headers("Content-Type: application/json")
    @GET("v1/travelers")
    suspend fun getListTraveler(): List<RemoteTravelResponse>
}