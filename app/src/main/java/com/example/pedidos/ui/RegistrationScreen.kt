package com.example.pedidos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pedidos.data.FakeUserRepository
import com.example.pedidos.viewmodel.RegistrationViewModel
import com.example.pedidos.viewmodel.RegistrationViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(onNavigateToLogin: () -> Unit) {
    val viewModel: RegistrationViewModel = viewModel(factory = RegistrationViewModelFactory(FakeUserRepository()))

    if (viewModel.registrationSuccess) {
        onNavigateToLogin()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Register", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.fullName,
            onValueChange = { viewModel.fullName = it },
            label = { Text("Full Name") },
            isError = viewModel.fullNameError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (viewModel.fullNameError != null) {
            Text(text = viewModel.fullNameError!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = viewModel.emailError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (viewModel.emailError != null) {
            Text(text = viewModel.emailError!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = viewModel.passwordError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (viewModel.passwordError != null) {
            Text(text = viewModel.passwordError!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.confirmPassword,
            onValueChange = { viewModel.confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = viewModel.confirmPasswordError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (viewModel.confirmPasswordError != null) {
            Text(text = viewModel.confirmPasswordError!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.generalError != null) {
            Text(text = viewModel.generalError!!, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = { viewModel.register() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.isLoading
        ) {
            if (viewModel.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Register")
            }
        }
    }
}