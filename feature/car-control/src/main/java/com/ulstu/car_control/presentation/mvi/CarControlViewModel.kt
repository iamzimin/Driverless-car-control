package com.ulstu.car_control.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulstu.car_control.domain.usecase.CarControlUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CarControlViewModel @Inject constructor(
    private val carControlUseCases: CarControlUseCases,
): ContainerHost<CarControlState, CarControlSideEffect>, ViewModel() {
    override val container = container<CarControlState, CarControlSideEffect>(CarControlState())

    fun startControl() = intent {

        viewModelScope.launch {
            reduce { state.copy(isCarControlLoading = false) }

            carControlUseCases.carControlData.invoke().collect { data ->
                //reduce { state.copy( )}
            }

            reduce { state.copy(isCarControlLoading = false) }
        }
    }

}