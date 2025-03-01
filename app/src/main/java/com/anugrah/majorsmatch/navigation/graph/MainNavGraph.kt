package com.anugrah.majorsmatch.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anugrah.majorsmatch.navigation.screen.BottomNavItemScreen
import com.anugrah.majorsmatch.ui.screen.explore.ExploreScreen
import com.anugrah.majorsmatch.ui.screen.home.HomeScreen
import com.anugrah.majorsmatch.ui.screen.profile.ProfileScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    route = Graph.MAIN,
    startDestination = BottomNavItemScreen.Home.route,
  ) {
    composable(route = BottomNavItemScreen.Home.route)  {
      HomeScreen(navHostController = navController)
    }
    composable(route = BottomNavItemScreen.Explore.route) {
      ExploreScreen(navHostController = navController)
    }
    composable(route = BottomNavItemScreen.Profile.route) {
      ProfileScreen(navHostController = navController)
    }
  }
}