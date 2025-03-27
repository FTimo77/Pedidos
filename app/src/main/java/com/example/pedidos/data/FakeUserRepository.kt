package com.example.pedidos.data

import kotlinx.coroutines.delay

class FakeUserRepository : UserRepository {
    private val users = mutableListOf<User>()

    override suspend fun registerUser(user: User): Result<Unit> {
        delay(1000) // Simulate network delay
        if (users.any { it.email == user.email }) {
            return Result.failure(Exception("Email already exists"))
        }
        users.add(user)
        return Result.success(Unit)
    }

    override suspend fun getUserByEmail(email: String): User? {
        return users.find { it.email == email }
    }
}