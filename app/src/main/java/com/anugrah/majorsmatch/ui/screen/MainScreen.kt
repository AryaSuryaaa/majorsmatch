package com.anugrah.majorsmatch.ui.screen

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anugrah.majorsmatch.navigation.graph.MainNavGraph
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.BottomBarApp
import com.anugrah.majorsmatch.ui.components.LoadingScreen
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import java.util.Locale

@Composable
fun MainScreen(
  navController: NavHostController = rememberNavController(),
  viewModel: MainViewModel = hiltViewModel(),
  loaderState: MutableState<Boolean>,
) {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route
  val context = LocalContext.current
  val uiState by viewModel.uiState.collectAsState()


  LaunchedEffect(Unit) {
    viewModel.getTheme()
    viewModel.getLanguage()
  }

  LaunchedEffect(uiState.language) {
    if (uiState.language.isNotEmpty()) {
      setLocale(context, uiState.language)
    }
  }

  val darkTheme = isDarkThemeFromPreference(uiState.theme)

  MajorsmatchTheme(
    darkTheme = darkTheme,
    dynamicColor = false
  ) {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.background
    ) {
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
  }
}

@Composable
fun isDarkThemeFromPreference(themePreference: String): Boolean {
  return when (themePreference) {
    "Light" -> false
    "Dark" -> true
    "Auto" -> isSystemInDarkTheme()
    else -> isSystemInDarkTheme()
  }
}

fun setLocale(context: Context, language: String) {
  val locale = Locale(language)
  Locale.setDefault(locale)

  val config = Configuration(context.resources.configuration)
  if (config.locales.get(0).language != language) {
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    val activity = context as? Activity
    activity?.recreate()
  }
}
