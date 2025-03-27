package com.anugrah.majorsmatch.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anugrah.majorsmatch.MainActivity
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.PasswordField
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import com.anugrah.majorsmatch.utils.showToast

@Composable
fun LoginScreen(
  navHostController: NavController,
  viewModel: LoginViewModel = hiltViewModel(),
  modifier: Modifier = Modifier
) {
  val uiState by viewModel.uiState.collectAsState()
  val context = LocalContext.current

  LaunchedEffect(uiState.loginResult) {
    val mainActivity = context as MainActivity

    when (uiState.loginResult) {
      is ResultState.Loading -> {
        mainActivity.loaderState.value = true
      }
      is ResultState.Success -> {
        mainActivity.loaderState.value = false
        context.showToast("Login Success")
        navHostController.popBackStack()
        navHostController.navigate(Screen.Home.route)
      }
      is ResultState.Error -> {
        mainActivity.loaderState.value = false
        val error = (uiState.loginResult as ResultState.Error).error
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
      }
      else -> Unit
    }
  }

  Box(
    modifier = modifier.fillMaxSize()
  ) {
    LoginContent(
      loginUiState = uiState,
      onUsernameChange = { viewModel.setEmail(it) },
      onPasswordChange = { viewModel.setPassword(it) },
      onLogin = {
        viewModel.login()
      },
      onRegister = {
        navHostController.navigate(Screen.Register.route)
      }
    )
  }
}

@Composable
fun LoginContent(
  loginUiState: LoginUiState,
  onUsernameChange: (String) -> Unit = {},
  onPasswordChange: (String) -> Unit = {},
  onLogin: () -> Unit = {},
  onRegister: () -> Unit = {}
) {
  val frEmail = FocusRequester()
  val frPassword = FocusRequester()
  val keyboardController = LocalSoftwareKeyboardController.current

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
  ) {
    Text(
      text = stringResource(R.string.welcome_to_majorsmatch),
      textAlign = TextAlign.Center,
      fontSize = 21.sp,
      modifier = Modifier
        .padding(top = 24.dp, bottom = 40.dp)
        .fillMaxWidth(),
    )
    Text(
      text = "LOGIN",
      textAlign = TextAlign.Center,
      fontSize = 21.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .padding(bottom = 16.dp)
        .fillMaxWidth(),
    )
    OutlinedTextField(
      label = { Text(text = stringResource(R.string.email)) },
      value = loginUiState.email,
      onValueChange = { onUsernameChange(it) },
      maxLines = 1,
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

    Spacer(modifier = Modifier.height(8.dp))

    PasswordField(
      label = "Password",
      value = loginUiState.password,
      onValueChange = { onPasswordChange(it) },
      modifier = Modifier
        .fillMaxWidth()
        .focusRequester(frPassword),
      onDone = {
        keyboardController?.hide()
      }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
      modifier = Modifier.fillMaxWidth(),
      onClick = {
        onLogin()
      }
    ) {
      Text(text = stringResource(R.string.login))
    }

    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = stringResource(R.string.don_t_have_an_account)
      )

      TextButton(
        onClick = {
          onRegister()
        }
      ) {
        Text(stringResource(R.string.register_here))
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
  MajorsmatchTheme {
    LoginScreen(navHostController = NavController(LocalContext.current))
  }
}