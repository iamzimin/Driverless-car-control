package com.ulstu.car_control.domain.usecase

import com.ulstu.car_control.domain.repository.CarControlRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetControlData @Inject constructor(
    private val carControlRepository: CarControlRepository,
) {
    suspend fun invoke(): Flow<Unit> {
        TODO("Stub")
    }
}