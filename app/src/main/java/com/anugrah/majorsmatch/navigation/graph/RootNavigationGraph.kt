package com.anugrah.majorsmatch.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.screen.MainScreen
import com.anugrah.majorsmatch.ui.screen.login.LoginScreen
import com.anugrah.majorsmatch.ui.screen.onboarding.OnBoardingScreen
import com.anugrah.majorsmatch.ui.screen.register.RegisterScreen
import com.anugrah.majorsmatch.ui.screen.splash.SplashScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    route = Graph.ROOT,
    startDestination = Screen.Splash.route
  ) {
    composable(route = Screen.Splash.route) {
      SplashScreen(navController = navController)
    }

    composable(route = Screen.OnBoarding.route) {
      OnBoardingScreen(navController = navController)
    }

    composable(Screen.Login.route) {
      LoginScreen(navController = navController)
    }

    composable(Screen.Register.route) {
      RegisterScreen(navController = navController)
    }

    composable(route = Graph.MAIN) {
      MainScreen()
    }
  }
}