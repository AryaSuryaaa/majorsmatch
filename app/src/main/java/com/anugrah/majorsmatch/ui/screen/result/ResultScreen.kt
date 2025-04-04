package com.anugrah.majorsmatch.ui.screen.result

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.data.dummy.universityLists
import com.anugrah.majorsmatch.data.remote.apiresponse.SurveyData
import com.anugrah.majorsmatch.data.remote.apiresponse.SurveyResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.UniversitiesItemSurvey
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.domain.model.toUniversity
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.MajorCard
import com.anugrah.majorsmatch.ui.components.ResultCardUniversity
import com.anugrah.majorsmatch.ui.screen.profile.SectionTitle
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp

@Composable
fun ResultScreen(
  navHostController: NavHostController,
  modifier: Modifier = Modifier
) {
  val surveyResponse = navHostController.previousBackStackEntry
    ?.savedStateHandle
    ?.get<SurveyResponse>("surveyResponse")

  if (surveyResponse == null) {
    Text("Result not found")
    return
  }

  var backPressedTime by remember { mutableStateOf(0L) }
  val context = LocalContext.current

  fun toHome() {
    navHostController.navigate(Screen.Home.route) {
      popUpTo(navHostController.graph.startDestinationId) { inclusive = true }
    }
  }

  BackHandler {
    val currentTime = System.currentTimeMillis()
    if (currentTime - backPressedTime < 2000) {
      toHome()
    } else {
      backPressedTime = currentTime
      Toast.makeText(context, "Press back again to go Home", Toast.LENGTH_SHORT).show()
    }
  }

  ResultContent(
    toHomeButton = {
      toHome()
    },
    toDetailUniversity = { u ->
      val university = u.toUniversity()
      navHostController.currentBackStackEntry?.savedStateHandle?.set("university", university)
      navHostController.navigate(Screen.DetailUniversity.route)
    },
    surveyData = surveyResponse.data,
    modifier = modifier
  )
}

@Composable
fun ResultContent(
  toHomeButton: () -> Unit = {},
  toDetailUniversity: (UniversitiesItemSurvey) -> Unit = {},
  surveyData: SurveyData,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  Column {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(DIMENS_16dp),
    ) {
      Text(
        text = "Survey Result",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
      )
    }
    Column(
      modifier = Modifier
        .weight(1f)
        .padding(DIMENS_16dp)
        .scrollable(scrollState, Orientation.Vertical),
    ) {
      SectionTitle("Matched major for you")
      LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        verticalArrangement = Arrangement.spacedBy(DIMENS_8dp),
        horizontalArrangement = Arrangement.spacedBy(DIMENS_8dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        items(surveyData.majors) { major ->
          MajorCard(majorName = major.majorName)
        }
      }
      SectionTitle("Matched university")
      LazyColumn(
        verticalArrangement = Arrangement.spacedBy(DIMENS_16dp)
      ) {
        items(surveyData.universities) { university ->
          ResultCardUniversity(
            onClick = { toDetailUniversity(university) },
            imgLogo = university.imgLogoUrl,
            universityName = university.name
          )
        }
      }
    }
    Button(
      onClick = toHomeButton,
      modifier = Modifier
        .fillMaxWidth()
        .padding(DIMENS_16dp)
    ) {
      Text(text = "Back to Home")
    }
  }
}

@Composable
fun SectionTitle(title: String) {
  Text(
    text = title,
    style = MaterialTheme.typography.bodyMedium,
    color = Color.Gray,
    modifier = Modifier.padding(vertical = 8.dp)
  )
}