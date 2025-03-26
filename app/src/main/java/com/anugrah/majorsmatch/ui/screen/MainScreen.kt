package com.anugrah.majorsmatch.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anugrah.majorsmatch.navigation.graph.MainNavGraph
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.BottomBarApp
import com.anugrah.majorsmatch.ui.components.LoadingScreen

@Composable
fun MainScreen(
  navController: NavHostController = rememberNavController(),
  loaderState: MutableState<Boolean>,
) {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route

  Box(
    modifier = Modifier.fillMaxSize()
  ) {
    Scaffold(
      bottomBar = {
        if (currentRoute == Screen.Home.route || currentRoute == Screen.Explore.route || currentRoute == Screen.Profile.route) {
          BottomBarApp(navController = navController)
        }
      }
    ) {
      Column(
        modifier = Modifier.padding(it)
      ) {
        MainNavGraph(navController = navController)
      }
    }
    if (loaderState.value) {
      LoadingScreen()
    }
  }
}