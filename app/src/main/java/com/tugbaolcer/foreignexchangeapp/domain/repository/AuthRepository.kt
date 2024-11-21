package com.tugbaolcer.foreignexchangeapp.domain.repository

import com.tugbaolcer.foreignexchangeapp.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Result<Unit>>
    suspend fun registerUser(user: UserDto): Result<Unit>
}