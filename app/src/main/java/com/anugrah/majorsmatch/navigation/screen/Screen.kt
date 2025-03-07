package com.anugrah.majorsmatch.navigation.screen

sealed class Screen(val route: String) {
  data object Splash : Screen("splash_screen")
  data object OnBoarding : Screen("on_boarding_screen")
  data object Login : Screen("login_screen")
  data object Register : Screen("register_screen")
  data object Home : Screen("home_screen")
  data object Explore : Screen("explore_screen")
  data object Profile : Screen("profile_screen")
  data object DetailUniversity : Screen("detail_university_screen/{universityId}") {
    fun withArgs(universityId: Int): String {
      return "detail_university_screen/$universityId"
    }
  }
}