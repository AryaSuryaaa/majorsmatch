package com.anugrah.majorsmatch.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_40dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_68dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_90dp
import com.anugrah.majorsmatch.ui.theme.GilroyFontFamily
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import com.anugrah.majorsmatch.ui.theme.TEXT_SIZE_18sp

@Composable
fun OnBoardingScreen(
  modifier: Modifier = Modifier,
  navHostController: NavHostController,
  onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
  OnBoarding(
    modifier = modifier,
    onClick ={
      navHostController.popBackStack()
      navHostController.navigate(Screen.Login.route)
      onBoardingViewModel.saveOnBoardingState(isCompleted = true)
    }
  )
}

@Composable
fun OnBoarding(
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Image(
      modifier = Modifier.fillMaxSize(),
      painter = painterResource(id = R.drawable.img_student),
      contentDescription = stringResource(R.string.student_image),
      contentScale = ContentScale.Crop
    )
    Surface(
      modifier = Modifier
        .align(alignment = Alignment.BottomCenter)
        .padding(bottom = DIMENS_90dp),
      color = Color.Transparent
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = stringResource(R.string.welcome_to_majorsmatch),
          style = MaterialTheme.typography.titleLarge.copy(color = Color.White, textAlign = TextAlign.Center)
        )

        Text(
          text = stringResource(R.string.get_your_match_majority_with_our_app),
          style = MaterialTheme.typography.titleMedium.copy(color = Color.White, textAlign = TextAlign.Center)
        )

        Spacer(modifier = Modifier.height(DIMENS_40dp))
        Button(
          onClick = {
            onClick.invoke()
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(height = DIMENS_68dp)
            .padding(start = DIMENS_16dp, end = DIMENS_16dp),
          colors = ButtonDefaults.buttonColors(Color.Blue),
          shape = RoundedCornerShape(DIMENS_12dp)
        ) {
          Text(
            text = stringResource(R.string.get_started),
            fontFamily = GilroyFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = TEXT_SIZE_18sp,
            color = Color.White
          )
        }
      }
    }
  }
}

@Preview
@Composable
private fun OnBoardingScreenPreview() {
  MajorsmatchTheme {
    OnBoarding(onClick = {})
  }
}