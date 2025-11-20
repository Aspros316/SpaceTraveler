package com.example.spacetraveler.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacetraveler.data.repository.model.RemoteSpaceRequest
import com.example.spacetraveler.domain.CreateTravelUseCase
import com.example.spacetraveler.utils.DispatcherProvider
import com.example.spacetraveler.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceViewModel(
    private val createTravelUseCase: CreateTravelUseCase,
    private val dispatcher: DispatcherProvider,
):ViewModel() {

    private val _createContractStateFlow: MutableStateFlow<Result<Any>> =
        MutableStateFlow(Result.OnLoading())
    val createQrFlow = _createContractStateFlow.asStateFlow()

    fun sendContract(request: RemoteSpaceRequest) {
        viewModelScope.launch(dispatcher.io) {
            createTravelUseCase.execute(request).collect { result ->
                _createContractStateFlow.value = result
            }
        }
    }
}