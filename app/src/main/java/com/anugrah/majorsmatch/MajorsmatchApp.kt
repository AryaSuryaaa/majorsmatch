package com.anugrah.majorsmatch

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anugrah.majorsmatch.ui.navigation.Screen
import com.anugrah.majorsmatch.ui.screen.login.LoginScreen
import com.anugrah.majorsmatch.ui.screen.register.RegisterScreen

@Composable
fun MajorsmatchApp(
  navController: NavHostController = rememberNavController(),
  modifier: Modifier = Modifier
) {
  Scaffold() {
    NavHost(
      navController = navController,
      startDestination = Screen.Login.route,
      modifier =  Modifier.padding(it)
    ) {
      composable(Screen.Login.route) {
        LoginScreen(navController)
      }
      composable(Screen.Register.route) {
        RegisterScreen(navController)
      }
    }
  }
}