package com.example.spacetraveler.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import com.example.spacetraveler.domain.CreateTravelUseCase
import com.example.spacetraveler.domain.GetListTravelUseCase
import com.example.spacetraveler.domain.GetTravelerByIdUseCase
import com.example.spacetraveler.utils.DispatcherProvider
import com.example.spacetraveler.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TravelViewModel(
    private val createTravelUseCase: CreateTravelUseCase,
    private val getListTravelUseCase: GetListTravelUseCase,
    private val getTravelerByIdUseCase: GetTravelerByIdUseCase,
    private val dispatcher: DispatcherProvider,
) : ViewModel() {

    private val _spaceTravelFlow: MutableStateFlow<Result<Any>> =
        MutableStateFlow(Result.OnLoading())
    val spaceTravelFlow = _spaceTravelFlow.asStateFlow()

    private val _spaceListTravelFlow: MutableStateFlow<Result<List<RemoteTravelResponse>>> =
        MutableStateFlow(Result.OnLoading())
    val spaceListTravelFlow = _spaceListTravelFlow.asStateFlow()

    private val _getTravelerById: MutableStateFlow<Result<RemoteTravelResponse>> =
        MutableStateFlow(Result.OnLoading())
    val getTravelerById = _getTravelerById.asStateFlow()

    init {
        getListTravel()
    }

    private fun getListTravel() {
        viewModelScope.launch(dispatcher.io) {
            getListTravelUseCase.execute().collect { result ->
                _spaceListTravelFlow.value = result
            }
        }
    }

    fun createTravel(request: RemoteTravelRequest) {
        viewModelScope.launch(dispatcher.io) {
            createTravelUseCase.execute(request).collect { result ->
                _spaceTravelFlow.value = result
            }
        }
    }

        fun getTravelerById(request: Int) {
            viewModelScope.launch(dispatcher.io) {
                getTravelerByIdUseCase.execute(request).collect { result ->
                    _getTravelerById.value = result
                }
            }
        }
}