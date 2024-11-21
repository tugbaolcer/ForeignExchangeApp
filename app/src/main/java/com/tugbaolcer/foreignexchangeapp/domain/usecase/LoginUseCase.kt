package com.tugbaolcer.foreignexchangeapp.domain.usecase

import com.tugbaolcer.foreignexchangeapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<Result<Unit>> {
        if (email.isBlank() || password.isBlank()) {
            return flow {
                emit(Result.failure(Exception("Email or password cannot be empty")))
            }
        }
        return authRepository.login(email, password)
    }
}
