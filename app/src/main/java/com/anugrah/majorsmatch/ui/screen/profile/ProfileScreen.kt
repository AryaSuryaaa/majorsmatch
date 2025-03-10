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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.DynamicDialog
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import com.anugrah.majorsmatch.utils.showToast

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
  navHostController: NavHostController
) {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    AccountContent(
      onLogout = {
        navHostController.navigate(Screen.Login.route) {
          popUpTo(navHostController.graph.id) {
            inclusive = true
          }
        }
      }
    )
  }
}

@Composable
fun AccountContent(
  onLogout: () -> Unit
) {

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
        text = "Surya",
        style = MaterialTheme.typography.titleLarge
      )
      Text(
        text = "email",
        style = MaterialTheme.typography.bodyMedium,
      )
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

    // "Account Settings"
    SectionTitle(title = stringResource(R.string.account_settings))

    AccountItem(
      title = "Language",
      onClick = { context.showToast(context.getString(R.string.this_feature_is_not_available_now)) }
    )

    ToggleItem(title = stringResource(R.string.push_notifications), checked = false, onCheckedChange = {})
    ToggleItem(title = stringResource(R.string.dark_mode), checked = false, onCheckedChange = {})

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
fun LogoutButton(onClick: () -> Unit) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
    modifier = Modifier.fillMaxWidth()
  ) {
    Text(text = "Log out")
  }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
  MajorsmatchTheme {
    AccountContent{}
  }
}