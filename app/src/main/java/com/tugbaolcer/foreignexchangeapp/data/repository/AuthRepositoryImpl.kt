package com.tugbaolcer.foreignexchangeapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tugbaolcer.foreignexchangeapp.data.dto.UserDto
import com.tugbaolcer.foreignexchangeapp.domain.repository.AuthRepository
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
    override suspend fun login(email: String, password: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.success(Unit)) // İşlemin başladığını göstermek için isteğe bağlı bir değer.
            val task = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (task.user != null) {
                emit(Result.success(Unit)) // Başarılı
            } else {
                emit(Result.failure(Exception("Login failed")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e)) // Hata durumu
        }
    }.flowOn(Dispatchers.IO) // Ağ işlemleri için IO thread kullanılır.

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
