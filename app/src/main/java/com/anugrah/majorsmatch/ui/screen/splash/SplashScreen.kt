package com.anugrah.majorsmatch.ui.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
  navHostController: NavHostController,
  splashViewModel: SplashViewModel = hiltViewModel()
) {
  val state by splashViewModel.uiState.collectAsState()

  val scale= remember { Animatable(0f) }

  LaunchedEffect(key1 = true) {
    scale.animateTo(
      targetValue = 0.8f,
      animationSpec = tween(
        durationMillis = 800,
        easing ={
          OvershootInterpolator(4f).getInterpolation(it)
        }
      ),
    )
    delay(1200L)
    navHostController.popBackStack()

    if (state.onBoarding) {
      if (state.isLogin != null) navHostController.navigate(Screen.Home.route)
      else navHostController.navigate(Screen.Login.route)
    }
    else navHostController.navigate(Screen.OnBoarding.route)
  }

  Splash(scale = scale.value)
}

@Composable
fun Splash(
  modifier: Modifier = Modifier,
  scale: Float
) {
  Box(
    modifier = modifier
      .background(Color.Blue)
      .fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Image(
      modifier = Modifier
        .scale(scale)
        .padding(all = 64.dp),
      painter = painterResource(id = R.drawable.logo_app),
      contentDescription = stringResource(R.string.logo_app)
    )
  }
}

@Preview
@Composable
private fun SplashScreenPreview() {
  MajorsmatchTheme {
    Splash(scale = 0f)
  }
}
