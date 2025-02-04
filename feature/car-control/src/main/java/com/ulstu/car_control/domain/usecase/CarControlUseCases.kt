package com.ulstu.car_control.domain.usecase

import javax.inject.Inject

class CarControlUseCases @Inject constructor(
    val carControlData: GetControlData,
)