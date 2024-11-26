package com.tugbaolcer.foreignexchangeapp.domain.usecase

import android.util.Log
import com.tugbaolcer.foreignexchangeapp.data.network.dto.UserDto
import com.tugbaolcer.foreignexchangeapp.domain.repository.AuthRepository
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RegisterUseCase @Inject constructor( private val repository: AuthRepository) {

    suspend operator fun invoke(userDto: UserDto) = flow {
        emit(Resource.Loading())
        try {
            repository.registerUser(userDto)
            Log.d("LOG_FIRESTORE","$userDto")
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Registration failed"))
        }
    }
}