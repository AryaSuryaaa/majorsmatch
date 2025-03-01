package com.anugrah.majorsmatch.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.anugrah.majorsmatch.navigation.graph.MainNavGraph
import com.anugrah.majorsmatch.ui.components.BottomBar
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_32dp

@Composable
fun MainScreen(
  navController: NavHostController = rememberNavController(),
) {
  Scaffold(
    bottomBar ={
      BottomBar(navController = navController)
    }
  ) {
    Column(
      modifier = Modifier.padding(it)
    ) {
      MainNavGraph(navController = navController)
    }
  }
}