package com.anugrah.majorsmatch.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.screen.explore.ExploreScreen
import com.anugrah.majorsmatch.ui.screen.home.HomeScreen
import com.anugrah.majorsmatch.ui.screen.login.LoginScreen
import com.anugrah.majorsmatch.ui.screen.onboarding.OnBoardingScreen
import com.anugrah.majorsmatch.ui.screen.profile.ProfileScreen
import com.anugrah.majorsmatch.ui.screen.register.RegisterScreen
import com.anugrah.majorsmatch.ui.screen.splash.SplashScreen

@Composable
fun MainNavGraph(navController: NavHostController, startDestination: String = Screen.Splash.route) {
  NavHost(
    navController = navController,
    startDestination = startDestination,
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

    composable(route = Screen.Home.route)  {
      HomeScreen(navHostController = navController)
    }
    composable(route = Screen.Explore.route) {
      ExploreScreen(navHostController = navController)
    }
    composable(route = Screen.Profile.route) {
      ProfileScreen(navHostController = navController)
    }
  }
}