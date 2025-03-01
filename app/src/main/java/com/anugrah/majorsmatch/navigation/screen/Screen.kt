package com.anugrah.majorsmatch.navigation.screen

sealed class Screen(val route: String) {
  data object Splash : Screen("splash_screen")
  data object OnBoarding : Screen("on_boarding_screen")
  data object Login : Screen("login_screen")
  data object Register : Screen("register_screen")
}