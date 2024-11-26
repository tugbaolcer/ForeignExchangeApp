package com.tugbaolcer.foreignexchangeapp.domain.repository

import com.tugbaolcer.foreignexchangeapp.data.network.dto.UserDto
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Resource<Unit>>
    suspend fun registerUser(user: UserDto): Result<Unit>
}