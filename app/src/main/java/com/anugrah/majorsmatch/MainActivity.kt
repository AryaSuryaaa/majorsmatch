package com.anugrah.majorsmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.anugrah.majorsmatch.ui.screen.MainScreen
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  val loaderState = mutableStateOf(false)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MainScreen(loaderState = loaderState)
    }
  }
}