package com.anugrah.majorsmatch.ui.screen.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.data.remote.apiresponse.DataUniversity
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.domain.model.toUniversity
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.ExploreCard
import com.anugrah.majorsmatch.ui.components.LoadingScreen
import com.anugrah.majorsmatch.ui.components.SearchBarComponent
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp

@Composable
fun ExploreScreen(
  navHostController: NavHostController,
  viewModel: ExploreViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.searchUniversity()
  }

  LaunchedEffect(uiState.query) {
    viewModel.searchUniversity()
  }

  ExploreContent(
    query = uiState.query,
    onQueryChange = { viewModel.updateQuery(it) },
    toDetailUniversity = { university ->
//      navHostController.navigate(
//        Screen.DetailUniversity.withArgs(
//          university
//        )
//      )
      navHostController.currentBackStackEntry?.savedStateHandle?.set("university", university)
      navHostController.navigate(Screen.DetailUniversity.route)
    },
    listUniversity = uiState.universities,
    isLoading = uiState.isLoading,
    errorMessage = uiState.errorMessage
  )
}

@Composable
fun ExploreContent(
  query: String,
  toDetailUniversity: (University) -> Unit,
  onQueryChange: (String) -> Unit,
  listUniversity: List<DataUniversity>,
  isLoading: Boolean,
  errorMessage: String?
  ) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(DIMENS_16dp)
  ) {
    SearchBarComponent(
      query = query,
      onQueryChange = onQueryChange
    )

    Spacer(modifier = Modifier.height(DIMENS_16dp))

    if (!isLoading && errorMessage == null) {
      LazyVerticalGrid(
        columns = GridCells.Adaptive(113.dp),
        horizontalArrangement = Arrangement.spacedBy(DIMENS_8dp),
        verticalArrangement = Arrangement.spacedBy(DIMENS_8dp),
      ) {
        items(listUniversity.size) {
          ExploreCard(
            onClick = { toDetailUniversity(listUniversity[it].toUniversity()) },
            imgLogo = listUniversity[it].imgLogoUrl,
            universityAcronym = listUniversity[it].acronym
          )
        }
      }
    } else if (errorMessage != null) {
      Text(errorMessage, modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally))
    } else {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .clickable(
            enabled = false,
            onClick = {}
          ),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator()
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun ExploreScreenPrev() {
  ExploreContent(
    query = "Dummy Query",
    onQueryChange = {},
    toDetailUniversity = {},
    listUniversity = emptyList(),
    isLoading = false,
    errorMessage = null
  )
}