package com.example.pedidos.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pedidos.data.User
import com.example.pedidos.data.UserRepository
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegistrationViewModel(private val userRepository: UserRepository) : ViewModel() {

    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var fullNameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var confirmPasswordError by mutableStateOf<String?>(null)
    var generalError by mutableStateOf<String?>(null)

    var isLoading by mutableStateOf(false)
    var registrationSuccess by mutableStateOf(false)

    fun register() {
        resetErrors()
        if (validateFields()) {
            viewModelScope.launch {
                isLoading = true
                val result = userRepository.registerUser(
                    User(fullName, email, password) // In a real app, hash the password!
                )
                isLoading = false
                result.onSuccess {
                    registrationSuccess = true
                }.onFailure {
                    generalError = it.message ?: "Registration failed"
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (fullName.isBlank()) {
            fullNameError = "Full name is required"
            isValid = false
        }

        if (email.isBlank()) {
            emailError = "Email is required"
            isValid = false
        } else if (!isValidEmail(email)) {
            emailError = "Invalid email format"
            isValid = false
        }

        if (password.isBlank()) {
            passwordError = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "Password must be at least 6 characters"
            isValid = false
        }

        if (confirmPassword.isBlank()) {
            confirmPasswordError = "Confirm password is required"
            isValid = false
        } else if (password != confirmPassword) {
            confirmPasswordError = "Passwords do not match"
            isValid = false
        }

        return isValid
    }

    private fun resetErrors() {
        fullNameError = null
        emailError = null
        passwordError = null
        confirmPasswordError = null
        generalError = null
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        val pattern = Pattern.compile(emailRegex)
        return pattern.matcher(email).matches()
    }
}