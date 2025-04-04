package com.anugrah.majorsmatch.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anugrah.majorsmatch.data.remote.apiresponse.SurveyResponse
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.screen.detailuniversity.DetailUniversityScreen
import com.anugrah.majorsmatch.ui.screen.explore.ExploreScreen
import com.anugrah.majorsmatch.ui.screen.feedback.FeedbackScreen
import com.anugrah.majorsmatch.ui.screen.home.HomeScreen
import com.anugrah.majorsmatch.ui.screen.login.LoginScreen
import com.anugrah.majorsmatch.ui.screen.onboarding.OnBoardingScreen
import com.anugrah.majorsmatch.ui.screen.profile.ProfileScreen
import com.anugrah.majorsmatch.ui.screen.register.RegisterScreen
import com.anugrah.majorsmatch.ui.screen.result.ResultScreen
import com.anugrah.majorsmatch.ui.screen.splash.SplashScreen
import com.anugrah.majorsmatch.ui.screen.survey.SurveyScreen

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
      route = Screen.DetailUniversity.route
    ) {
      val university = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<University>("university")

      if (university != null) {
        DetailUniversityScreen(navHostController = navController)
      }
    }

    composable(route = Screen.Survey.route) {
      SurveyScreen(navHostController = navController)
    }
    composable(route = Screen.Feedback.route) {
      FeedbackScreen(navHostController = navController)
    }
    composable(route = Screen.Result.route) {
      val surveyResponse = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<SurveyResponse>("surveyResponse")

      if (surveyResponse != null) {
        ResultScreen(navHostController = navController)
      }
    }
  }
}