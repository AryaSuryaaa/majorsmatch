package com.anugrah.majorsmatch.ui.screen.home

import android.annotation.SuppressLint
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.data.dummy.universitasList
import com.anugrah.majorsmatch.domain.model.Universitas
import com.anugrah.majorsmatch.ui.components.CardUniversity
import com.anugrah.majorsmatch.ui.components.SliderBanner
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_2dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
  navHostController: NavHostController
) {
  val universitas = universitasList
  HomeScreenContent(listUniversity = universitas)
}

@Composable
fun HomeScreenContent(listUniversity: List<Universitas>, modifier: Modifier = Modifier) {
  Scaffold { padding ->
    Column(
      modifier = modifier
        .verticalScroll(rememberScrollState())
        .padding(padding),
    ) {
      HeaderHome()
      Spacer(modifier = Modifier.height(DIMENS_16dp))
      TopUniversity(listUniversity)
    }
  }
}

@Composable
fun HeaderHome(modifier: Modifier = Modifier) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(start = DIMENS_16dp, end = DIMENS_16dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text("Selamat datang (nama)!") // ganti nama
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
  university: List<Universitas>,
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
          acronym = it.acronym
        )
      }
    }
  }
}