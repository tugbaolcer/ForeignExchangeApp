package com.tugbaolcer.foreignexchangeapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import com.tugbaolcer.foreignexchangeapp.data.network.dto.UserDto
import com.tugbaolcer.foreignexchangeapp.domain.repository.AuthRepository
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun login(email: String, password: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result.user != null) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("Login failed, user not found."))
            }
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(Resource.Error("Invalid email or user does not exist."))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(Resource.Error("Invalid credentials. Please check your password."))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun registerUser(user: UserDto): Result<Unit> {
        return try {
            val task = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
            val userId = task.user?.uid ?: return Result.failure(Exception("User ID not found"))

            val userMap = mapOf(
                "name" to user.name,
                "surname" to user.surname,
                "email" to user.email,
                "userId" to userId
            )

            firestore.collection("users").document(userId).set(userMap).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
