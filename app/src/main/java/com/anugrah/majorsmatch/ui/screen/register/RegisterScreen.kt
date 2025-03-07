package com.anugrah.majorsmatch.ui.screen.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anugrah.majorsmatch.ui.components.PasswordField
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme

@Composable
fun RegisterScreen(
  navHostController: NavController,
  registerViewModel: RegisterViewModel = hiltViewModel(),
  modifier: Modifier = Modifier
) {
  val uiState by registerViewModel.uiState.collectAsState()
  Box(
    modifier = modifier.fillMaxSize()
  ) {
    RegisterContent(
      registerUiState = uiState,
      navController = navHostController,
      onUsernameChange = { registerViewModel.setUsername(it) },
      onEmailChange = { registerViewModel.setEmail(it) },
      onPasswordChange = { registerViewModel.setPassword(it) },
      onConfirmPasswordChange = { registerViewModel.setConfirmPassword(it) },
      onRegister = {
        registerViewModel.register()
      },
      onLogin = {
        navHostController.popBackStack()
      }
    )
  }
}

@Composable
fun RegisterContent(
  registerUiState: RegisterUiState,
  navController: NavController,
  onUsernameChange: (String) -> Unit = {},
  onEmailChange: (String) -> Unit = {},
  onPasswordChange: (String) -> Unit = {},
  onConfirmPasswordChange: (String) -> Unit = {},
  onRegister: () -> Unit = {},
  onLogin: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val frUsername = FocusRequester()
  val frEmail = FocusRequester()
  val frPassword = FocusRequester()
  val frConfirmPassword = FocusRequester()

  val keyboardController = LocalSoftwareKeyboardController.current

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      text = "Welcome to Majorsmatch",
      textAlign = TextAlign.Center,
      fontSize = 21.sp,
      modifier = Modifier
        .padding(top = 24.dp, bottom = 40.dp)
        .fillMaxWidth(),
    )
    Text(
      text = "Register".uppercase(),
      textAlign = TextAlign.Center,
      fontSize = 21.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .padding(bottom = 16.dp)
        .fillMaxWidth(),

    )
    OutlinedTextField(
      label = { Text(text = "Username") },
      value = registerUiState.username,
      onValueChange = { onUsernameChange(it) },
      maxLines = 1,
      isError = registerUiState.usernameError != null,
      supportingText = {
        if (registerUiState.usernameError != null) {
          Text(text = registerUiState.usernameError, color = MaterialTheme.colorScheme.error)
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .focusRequester(frUsername),
      keyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next
      ),
      keyboardActions = KeyboardActions(
        onNext = { frEmail.requestFocus() }
      ),
    )

    OutlinedTextField(
      label = { Text(text = "Email") },
      value = registerUiState.email,
      onValueChange = { onEmailChange(it) },
      maxLines = 1,
      isError = registerUiState.emailError != null,
      supportingText = {
        if (registerUiState.emailError != null) {
          Text(text = registerUiState.emailError, color = MaterialTheme.colorScheme.error)
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .focusRequester(frEmail),
      keyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next
      ),
      keyboardActions = KeyboardActions(
        onNext = { frPassword.requestFocus() }
      ),
    )

    PasswordField(
      label = "Password",
      value = registerUiState.password,
      onValueChange = { onPasswordChange(it) },
      isError = registerUiState.passwordError != null,
      valueError = registerUiState.passwordError,
      modifier = Modifier
        .fillMaxWidth()
        .focusRequester(frPassword),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Next
      ),
      onNext = {frConfirmPassword.requestFocus()}
    )

    PasswordField(
      label = "Confirm password",
      value = registerUiState.confirmPassword,
      onValueChange = { onConfirmPasswordChange(it) },
      isError = registerUiState.confirmPasswordError != null,
      valueError = registerUiState.confirmPasswordError,
      modifier = Modifier
        .fillMaxWidth()
        .focusRequester(frConfirmPassword),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done
      ),
      onDone = {
        keyboardController?.hide()
      }
    )

    Spacer(Modifier.height(8.dp))

    Button(
      onClick = {
        onRegister()
      },
      modifier = Modifier
        .fillMaxWidth(),
    ) {
      Text(text = "Register")
    }

    Spacer(modifier = Modifier.height(8.dp))

    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text("Have any account?")
      TextButton(
        onClick = {
          onLogin()
        },
        modifier = Modifier
      ) {
        Text("login here")
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
  MajorsmatchTheme {
    RegisterScreen(navHostController = NavController(LocalContext.current))
  }
}