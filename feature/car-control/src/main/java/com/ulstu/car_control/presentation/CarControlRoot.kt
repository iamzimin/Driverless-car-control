package com.ulstu.car_control.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ulstu.car_control.presentation.mvi.CarControlViewModel

@Composable
fun CarControlRoot(
    viewModel: CarControlViewModel = hiltViewModel<CarControlViewModel>(),
) {
    CarControlScreen(

    )
}