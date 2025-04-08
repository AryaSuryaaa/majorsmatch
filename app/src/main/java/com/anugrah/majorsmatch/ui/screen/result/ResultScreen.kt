package com.anugrah.majorsmatch.ui.screen.result

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.data.remote.apiresponse.SurveyData
import com.anugrah.majorsmatch.data.remote.apiresponse.SurveyResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.UniversitiesItemSurvey
import com.anugrah.majorsmatch.domain.model.toUniversity
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.MajorCard
import com.anugrah.majorsmatch.ui.components.ResultCardUniversity
import com.anugrah.majorsmatch.ui.screen.profile.SectionTitle
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp

@Composable
fun ResultScreen(
  navHostController: NavHostController
) {
  val surveyResponse = navHostController.previousBackStackEntry
    ?.savedStateHandle
    ?.get<SurveyResponse>("surveyResponse")

  if (surveyResponse == null) {
    Text("Result not found")
    return
  }

  var backPressedTime by remember { mutableLongStateOf(0L) }
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
    surveyData = surveyResponse.data
  )
}

@Composable
fun ResultContent(
  toHomeButton: () -> Unit = {},
  toDetailUniversity: (UniversitiesItemSurvey) -> Unit = {},
  surveyData: SurveyData
) {
  Column {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(horizontal = DIMENS_16dp),
    ) {
      Text(
        text = "Survey Result",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(vertical = DIMENS_8dp)
      )
    }
    LazyColumn(
      modifier = Modifier
        .weight(1f)
        .padding(horizontal = DIMENS_16dp),
      verticalArrangement = Arrangement.spacedBy(DIMENS_8dp)
    ) {
      // Title for majors
      item {
        SectionTitle("Matched major for you")
      }

      // Grid for majors
      item {
        LazyVerticalGrid(
          columns = GridCells.Adaptive(minSize = 160.dp),
          horizontalArrangement = Arrangement.spacedBy(DIMENS_8dp),
          verticalArrangement = Arrangement.spacedBy(DIMENS_8dp),
          modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
        ) {
          items(surveyData.majors) { major ->
            MajorCard(majorName = major.majorName)
          }
        }
      }

      // Title for universities
      item {
        SectionTitle("Matched university")
      }

      // List of universities
      items(surveyData.universities) { university ->
        ResultCardUniversity(
          onClick = { toDetailUniversity(university) },
          imgLogo = university.imgLogoUrl,
          universityName = university.name
        )
      }
    }

    Button(
      onClick = toHomeButton,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = DIMENS_16dp)
    ) {
      Text(text = "Back to Home")
    }
  }
}