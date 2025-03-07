package com.anugrah.majorsmatch.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.screen.detailuniversity.DetailUniversityScreen
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
      SplashScreen(navHostController = navController)
    }

    composable(route = Screen.OnBoarding.route) {
      OnBoardingScreen(navHostController = navController)
    }

    composable(Screen.Login.route) {
      LoginScreen(navHostController = navController)
    }

    composable(Screen.Register.route) {
      RegisterScreen(navHostController = navController)
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
    composable(
      route = Screen.DetailUniversity.route,
      arguments = listOf(
        navArgument("universityId") { type = NavType.IntType }
      )
    ) { backStackEntry ->
      val universityId = backStackEntry.arguments?.getInt("universityId") ?: 0
      DetailUniversityScreen(universityId = universityId, navHostController = navController)
    }
  }
}