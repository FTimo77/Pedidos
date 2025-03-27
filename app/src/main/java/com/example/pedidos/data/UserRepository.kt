package com.example.pedidos.data

interface UserRepository {
    suspend fun registerUser(user: User): Result<Unit>
    suspend fun getUserByEmail(email: String): User?
}