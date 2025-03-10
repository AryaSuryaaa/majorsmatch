package com.anugrah.majorsmatch.ui.screen.explore

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.data.dummy.universityLists
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.common.UiState
import com.anugrah.majorsmatch.ui.components.ExploreCard
import com.anugrah.majorsmatch.ui.components.SearchBarComponent
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp

@Composable
fun ExploreScreen(
  navHostController: NavHostController,
  viewModel: ExploreViewModel = hiltViewModel()
) {
  val universityList = universityLists
  val uiState by viewModel.uiState.collectAsState()

  when (uiState) {
    is UiState.Loading -> {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
      }
    }
    is UiState.Success -> {
      val data = (uiState as UiState.Success<ExploreUiState>).data
      ExploreContent(
        query = data.query,
        onQueryChange = { viewModel.updateQuery(it) },
        toDetailUniversity = { universityId ->
          navHostController.navigate(
            Screen.DetailUniversity.withArgs(
              universityId
            )
          )
        },
        listUniversity = universityList
      )
    }
    is UiState.Error -> {
      val errorMessage = (uiState as UiState.Error).errorMessage
      Text("Error: $errorMessage", color = Color.Red, modifier = Modifier.fillMaxSize())
    }
  }
}

@Composable
fun ExploreContent(
  query: String,
  toDetailUniversity: (Int) -> Unit,
  onQueryChange: (String) -> Unit,
  listUniversity: List<University>,
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

    LazyVerticalGrid(
      columns = GridCells.Adaptive(113.dp),
      horizontalArrangement = Arrangement.spacedBy(DIMENS_8dp),
      verticalArrangement = Arrangement.spacedBy(DIMENS_8dp),
    ) {
      items(listUniversity.size) {
        ExploreCard(
          onClick = { toDetailUniversity(listUniversity[it].id) },
          imgLogo = listUniversity[it].imgLogo,
          universityName = listUniversity[it].name
        )
      }
    }
  }
}

@Preview
@Composable
private fun ExploreScreenPrev() {
  val universityList = universityLists
  ExploreContent(
    query = "Dummy Query",
    onQueryChange = {},
    toDetailUniversity = {},
    listUniversity = universityList
  )
}