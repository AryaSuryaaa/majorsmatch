package com.anugrah.majorsmatch.ui.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.data.remote.apiresponse.DataUser
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.DynamicDialog
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import com.anugrah.majorsmatch.utils.showToast

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
  navHostController: NavHostController,
  viewModel: AccountViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.getTheme()
    viewModel.getLanguage()
  }

  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    uiState.dataUser?.let {
      AccountContent(
        dataUser = it,
        onLogout = {
          navHostController.navigate(Screen.Login.route) {
            popUpTo(navHostController.graph.id) {
              inclusive = true
            }
          }
        },
        toFeedback = {
          navHostController.navigate(Screen.Feedback.route)
        },
        setTheme = { theme ->
          viewModel.saveTheme(theme)
        },
        setLanguage = { language ->
          viewModel.saveLanguage(language)
        },
        theme = uiState.theme,
        language = uiState.language
      )
    }
  }
}

@Composable
fun AccountContent(
  onLogout: () -> Unit,
  dataUser: DataUser,
  toFeedback: () -> Unit,
  setTheme: (String) -> Unit,
  setLanguage: (String) -> Unit,
  theme: String,
  language: String
) {
  var showThemeDialog by remember { mutableStateOf(false) }
  var currentTheme by remember { mutableStateOf("Auto") }
  var showLanguageDialog by remember { mutableStateOf(false) }
  var currentLanguage by remember { mutableStateOf("English") }
  val (showLogOutDialog, setShowLogOutDialog) = remember { mutableStateOf(false) }
  val context = LocalContext.current

  // Dialog Log out
  DynamicDialog(
    showDialog = showLogOutDialog,
    onDismiss = { setShowLogOutDialog(false) },
    title = stringResource(R.string.log_out),
    message = stringResource(R.string.are_you_sure_you_want_to_log_out),
    onConfirm = {
      onLogout()
    },
    onDismissButton = { /* Optional */ }
  )

  ThemeDialog(
    showDialog = showThemeDialog,
    currentTheme = theme,
    onDismiss = { showThemeDialog = false },
    onConfirm = { selectedTheme ->
      currentTheme = selectedTheme
      setTheme(selectedTheme)
      showThemeDialog = false
    }
  )

  LanguageDialog(
    showDialog = showLanguageDialog,
    currentLanguage = language,
    onDismiss = { showLanguageDialog = false },
    onConfirm = { selectedLanguage ->
      currentLanguage = selectedLanguage
      setLanguage(selectedLanguage)
      showLanguageDialog = false
    }
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(16.dp),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = dataUser.fullName,
        style = MaterialTheme.typography.titleLarge
      )
      Text(
        text = dataUser.email,
        style = MaterialTheme.typography.bodyMedium,
      )
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

    // "Account Settings"
    SectionTitle(title = stringResource(R.string.account_settings))

    AccountItem(
      title = stringResource(R.string.language),
      onClick = { showLanguageDialog = true }
    )

    AccountItem(
      title = stringResource(R.string.dark_mode),
      onClick = { showThemeDialog = true }
    )
    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

    // "Feed back"
    SectionTitle(title = stringResource(R.string.feed_back))

    AccountItem(title = stringResource(R.string.give_feedback), onClick = toFeedback)

    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

    // "More"
    SectionTitle(title = stringResource(R.string.more))

    AccountItem(title = stringResource(R.string.about_app), onClick = { context.showToast(context.getString(R.string.this_feature_is_not_available_now)) })
    AccountItem(title = stringResource(R.string.privacy_policy), onClick = { context.showToast(context.getString(R.string.this_feature_is_not_available_now)) })
    AccountItem(title = stringResource(R.string.terms_and_conditions), onClick = { context.showToast(context.getString(R.string.this_feature_is_not_available_now)) })

    Spacer(modifier = Modifier.height(16.dp))

    LogoutButton { setShowLogOutDialog(true) }
  }
}

@Composable
fun AccountItem(
  title: String,
  onClick: () -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding(vertical = 16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.bodyLarge,
      color = MaterialTheme.colorScheme.onBackground
    )
  }
}

@Composable
fun SectionTitle(title: String) {
  Text(
    text = title,
    style = MaterialTheme.typography.bodyMedium,
    color = Color.Gray,
    modifier = Modifier.padding(vertical = 8.dp)
  )
}

@Composable
fun ToggleItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(text = title, style = MaterialTheme.typography.bodyLarge)
    Switch(checked = checked, onCheckedChange = onCheckedChange)
  }
}

@Composable
fun ThemeDialog(
  showDialog: Boolean,
  currentTheme: String,
  onDismiss: () -> Unit,
  onConfirm: (selectedTheme: String) -> Unit
) {
  if (showDialog) {
    var selectedTheme by remember { mutableStateOf(currentTheme) }
    AlertDialog(
      onDismissRequest = onDismiss,
      title = { Text(text = stringResource(R.string.select_theme)) },
      text = {
        Column {
          val themes = listOf(
            stringResource(R.string.light),
            stringResource(R.string.dark),
            stringResource(R.string.auto)
          )
          themes.forEach { theme ->
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier
                .fillMaxWidth()
                .clickable { selectedTheme = theme }
                .padding(vertical = 4.dp)
            ) {
              RadioButton(
                selected = (theme == selectedTheme),
                onClick = { selectedTheme = theme }
              )
              Text(text = theme)
            }
          }
        }
      },
      confirmButton = {
        Button(onClick = { onConfirm(selectedTheme) }) {
          Text(text = stringResource(R.string.confirm))
        }
      },
      dismissButton = {
        Button(onClick = onDismiss) {
          Text(text = stringResource(R.string.cancel))
        }
      }
    )
  }
}

@Composable
fun LanguageDialog(
  showDialog: Boolean,
  currentLanguage: String,
  onDismiss: () -> Unit,
  onConfirm: (selectedLanguage: String) -> Unit
) {
  if (showDialog) {
    var selectedLanguage by remember { mutableStateOf(currentLanguage) }
    val languageOptions = listOf("en" to "English", "in" to "Bahasa Indonesia")
    AlertDialog(
      onDismissRequest = onDismiss,
      title = { Text(text = "Select Language") },
      text = {
        Column {
          languageOptions.forEach { (code, name) ->
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier
                .fillMaxWidth()
                .clickable { selectedLanguage = code }
                .padding(vertical = 4.dp)
            ) {
              RadioButton(
                selected = (selectedLanguage == code),
                onClick = { selectedLanguage = code }
              )
              Text(text = name)
            }
          }
        }
      },
      confirmButton = {
        Button(onClick = { onConfirm(selectedLanguage) }) {
          Text(text = stringResource(R.string.confirm))
        }
      },
      dismissButton = {
        Button(onClick = onDismiss) {
          Text(text = stringResource(R.string.cancel))
        }
      }
    )
  }
}



@Composable
fun LogoutButton(onClick: () -> Unit) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
    modifier = Modifier.fillMaxWidth()
  ) {
    Text(text = stringResource(R.string.log_out))
  }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
  MajorsmatchTheme {
    AccountContent(
      onLogout = {},
      dataUser = DataUser(1, "anugrah", "anugrah@gmail.com", "angrh"),
      toFeedback = {},
      setTheme = {},
      setLanguage = {},
      theme = "",
      language = ""
    )
  }
}

@Preview
@Composable
private fun ThemePreview() {
  ThemeDialog(
    showDialog = true,
    currentTheme = "Dark",
    onDismiss = {}
  ) { }
}