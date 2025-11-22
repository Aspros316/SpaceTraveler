package com.example.spacetraveler.data.repository

import com.example.spacetraveler.data.mapper.toCacheList
import com.example.spacetraveler.data.mapper.toRemoteList
import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import com.example.spacetraveler.data.repository.retrofit.SpaceWebService
import com.example.spacetraveler.data.source.TravelCache
import com.example.spacetraveler.data.source.TravelRepository
import com.example.spacetraveler.utils.NetworkHandler

class TravelRepositoryImpl(
    private val webService: SpaceWebService,
    private val cache: TravelCache,
    private val networkHandler: NetworkHandler
) : TravelRepository {

    override suspend fun getListTravel(): List<RemoteTravelResponse> {
        if (networkHandler.isOnline()) {
            val response = webService.getListTraveler()
            if (response.isSuccessful) {
                val responseBody = response.body()
                cache.saveListTravel(responseBody?.toCacheList() ?: emptyList())
                return responseBody ?: throw IllegalStateException("Respuesta vacía")
            }
            else {
                when (response.code()) {
                    403 -> throw IllegalArgumentException("Solicitud inválida, ha ocurrido un error con el servicio")
                    400 -> throw IllegalArgumentException("Solicitud inválida (400)")
                    401 -> throw SecurityException("No autorizado (401)")
                    else -> throw Exception("Error desconocido: ${response.code()}")
                }
            }
        }
        else {
            val cachedList = cache.getListTravel()
            return cachedList.toRemoteList()
        }
    }

        override suspend fun saveTravel(request: RemoteTravelRequest) {
            return webService.createTravel(request)
        }

        override suspend fun getTravelerById(id: Int): RemoteTravelResponse {
            return webService.getTravelerById(id)
        }
    }