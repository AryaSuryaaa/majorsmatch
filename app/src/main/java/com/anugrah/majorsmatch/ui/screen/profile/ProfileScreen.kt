package com.anugrah.majorsmatch.ui.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.DynamicDialog
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import com.anugrah.majorsmatch.utils.showToast

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
  modifier: Modifier = Modifier,
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

  // Dialog untuk Log out
  DynamicDialog(
    showDialog = showLogOutDialog,
    onDismiss = { setShowLogOutDialog(false) },
    title = "Log Out",
    message = "Are you sure you want to log out?",
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

    // Bagian "Account Settings"
    SectionTitle(title = "Account Settings")

    AccountItem(
      title = "Language",
      onClick = { context.showToast("This feature is not available now") }
    )

    ToggleItem(title = "Push notifications", checked = false, onCheckedChange = {})
    ToggleItem(title = "Dark mode", checked = false, onCheckedChange = {})

    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

    // Bagian "More"
    SectionTitle(title = "More")

    AccountItem(title = "About App", onClick = { context.showToast("This feature is not available now") })
    AccountItem(title = "Privacy policy", onClick = { context.showToast("This feature is not available now") })
    AccountItem(title = "Terms and conditions", onClick = { context.showToast("This feature is not available now") })

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