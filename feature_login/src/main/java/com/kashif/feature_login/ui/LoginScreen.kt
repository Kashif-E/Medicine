package com.kashif.feature_login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kashif.design_system.components.RoundedElevatedButton
import com.kashif.design_system.components.RoundedTextField
import com.kashif.feature_login.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onNavigate: () -> Unit, viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
        SnackbarHost(snackBarHostState)
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.headlineSmall
            )

            RoundedTextField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(R.string.email_username),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email,
            )

            RoundedTextField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                isPasswordField = true
            )


            RoundedElevatedButton(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
                text = stringResource(R.string.login),
                onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        scope.launch {
                            snackBarHostState.showSnackbar("Please enter email and password")
                        }
                    } else {
                        viewModel.fetchMedicines()
                        onNavigate()
                    }
                })
        }
    }
}
