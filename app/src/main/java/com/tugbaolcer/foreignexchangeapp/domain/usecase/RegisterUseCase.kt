package com.tugbaolcer.foreignexchangeapp.domain.usecase

import com.tugbaolcer.foreignexchangeapp.data.dto.UserDto
import com.tugbaolcer.foreignexchangeapp.domain.repository.AuthRepository


class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(user: UserDto): Result<Unit> {
        if (isValidForm(userName = user.name, surname = user.surname, email = user.email, password = user.password ).not()) {
            return Result.failure(Exception("All fields must be filled"))
        }
        return repository.registerUser(user)
    }

    fun isValidForm(
        userName: String,
        surname: String,
        email: String,
        password: String
    ): Boolean {
        return userName.isNotEmpty() &&
                surname.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty()
    }
}
