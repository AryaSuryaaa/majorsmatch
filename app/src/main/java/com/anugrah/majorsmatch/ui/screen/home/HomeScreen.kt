package com.anugrah.majorsmatch.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.data.dummy.universityLists
import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.data.remote.apiresponse.DataTestimony
import com.anugrah.majorsmatch.data.remote.apiresponse.DataUser
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.CardUniversity
import com.anugrah.majorsmatch.ui.components.SliderBanner
import com.anugrah.majorsmatch.ui.theme.DIMENS_114dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun HomeScreen(
  navHostController: NavHostController,
  viewModel: HomeViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState.collectAsState()
  LocalContext.current

  LaunchedEffect(Unit) {
    viewModel.getUserSession()
    viewModel.getUniversities()
    viewModel.getTestimony()
  }

  uiState.userSession?.let {
    HomeScreenContent(
      user = it,
      listUniversity = uiState.universities,
      testimony = uiState.testimony,
      toDetailUniversity = { university ->
        navHostController.currentBackStackEntry?.savedStateHandle?.set("university", university)
        navHostController.navigate(Screen.DetailUniversity.route)
      },
      toSurvey = {
        navHostController.navigate(Screen.Survey.route)
      }
    )
  }
}

@Composable
fun HomeScreenContent(
  user: DataLogin,
  listUniversity: List<University>,
  toDetailUniversity: (University) -> Unit,
  toSurvey: () -> Unit = {},
  testimony: List<DataTestimony>,
  modifier: Modifier = Modifier
) {
  Scaffold { padding ->
    Column(
      modifier = modifier
        .verticalScroll(rememberScrollState())
        .padding(padding),
    ) {
      Spacer(modifier = Modifier.height(DIMENS_16dp))
      HeaderHome(name = user.dataUser.username)
      Spacer(modifier = Modifier.height(DIMENS_16dp))
      TopUniversity(
        listUniversity,
        toDetailUniversity = { university ->
          toDetailUniversity(university)
        }
      )
      Spacer(modifier = Modifier.height(DIMENS_16dp))
      ButtonSurvey(toSurvey = toSurvey)
      Spacer(modifier = Modifier.height(DIMENS_16dp))
      Testimonials(
        testimony = testimony
      )
    }
  }
}

@Composable
fun HeaderHome(name: String, modifier: Modifier = Modifier) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(start = DIMENS_16dp, end = DIMENS_16dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(stringResource(R.string.welcome, name))
    Image(
      painter = painterResource(id = R.drawable.logo_app),
      contentDescription = "logo app",
      modifier = Modifier.width(40.dp),
      contentScale = ContentScale.Fit
    )
  }
}

@Composable
fun TopUniversity(
  university: List<University>,
  toDetailUniversity: (University) -> Unit = {},
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier
    .fillMaxWidth()
  ) {
    Text(
      stringResource(R.string.top_university),
      style = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Bold
      ),
      modifier = Modifier.padding(horizontal = DIMENS_16dp)
    )
    Spacer(modifier = Modifier.height(DIMENS_8dp))
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(DIMENS_12dp),
      contentPadding = PaddingValues(DIMENS_8dp)
    ) {
      items(university) {
        CardUniversity(
          imgBanner = it.imgBanner,
          acronym = it.acronym,
          onClick = {
            toDetailUniversity(it)
          }
        )
      }
    }
  }
}

@Composable
fun ButtonSurvey(
  toSurvey: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier
    .fillMaxWidth()
    .padding(horizontal = DIMENS_16dp)
  ) {
    Text(
      stringResource(R.string.talent_survey),
      style = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Bold
      ),
    )
    Spacer(modifier = Modifier.height(DIMENS_8dp))

    Box(modifier = Modifier.fillMaxSize()) {
      Card(
        shape = RoundedCornerShape(DIMENS_12dp),
      ) {
        Image(
          painter = painterResource(id = R.drawable.bg_survey),
          contentDescription = null,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize(),
        )
      }
      Box(
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .padding(bottom = DIMENS_8dp, end = DIMENS_16dp)
      ) {
        Button(
          onClick = toSurvey,
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
          )
          ) {
          Row(
            horizontalArrangement = Arrangement.spacedBy(DIMENS_8dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text("GO")
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowForward,
              contentDescription = null
            )
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Testimonials(
  testimony: List<DataTestimony>
) {
  Column {
    Text(
      text = stringResource(R.string.what_they_say),
      modifier = Modifier.padding(horizontal = DIMENS_16dp),
      fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(DIMENS_8dp))
    SliderBanner(
      testimony = testimony,
      modifier = Modifier
        .height(DIMENS_114dp)
        .fillMaxWidth(),
    )
  }
}

@Preview
@Composable
private fun HomScreenPrev() {
  MajorsmatchTheme {
    val universityList = universityLists
    HomeScreenContent(
      user = DataLogin(
          DataUser(
            username = "Femmy",
            email = "william.henry.harrison@example-pet-store.com",
            id = 1,
            fullName = "Mas Femmy"
          ),
        token = ""
      ),
      testimony = emptyList(),
      listUniversity = universityList,
      toDetailUniversity = {},
    )
  }
}