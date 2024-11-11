package com.tugbaolcer.foreignexchangeapp.domain.base

import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<R : Any> {
    suspend operator fun invoke(): Flow<Resource<R>>
}