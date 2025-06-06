package com.anugrah.majorsmatch.ui.screen.detailuniversity

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.data.dummy.universityLists
import com.anugrah.majorsmatch.domain.model.Major
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.ui.components.CircleBackButton
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_4dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp
import com.anugrah.majorsmatch.ui.theme.GrayBorderStroke
import com.anugrah.majorsmatch.ui.theme.MajorsmatchTheme

@Composable
fun DetailUniversityScreen(
  navHostController: NavHostController
) {
  val context = LocalContext.current
  val university = navHostController.previousBackStackEntry
    ?.savedStateHandle
    ?.get<University>("university")

  if (university == null) {
    Text(stringResource(R.string.university_not_found))
    return
  }

  DetailsUniversityContent(
    onBackClick =  { navHostController.popBackStack() },
    toWebsiteClick = {
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(university.website))
      context.startActivity(intent)
    },
    university = university
  )
}


@Composable
fun DetailsUniversityContent(
  onBackClick: () -> Unit,
  toWebsiteClick: () -> Unit = {},
  university: University
) {
  LazyColumn {
    item {
      HeaderBox(onBackClick = onBackClick, university =  university)
      Description(description = university.description)
    }
    item {
      Faculties(university = university)
    }
    item {
      ButtonToWebsite(onClick = toWebsiteClick)
    }
  }
}

@Composable
fun HeaderBox(onBackClick: () -> Unit, university: University) {
  Box(
    modifier = Modifier.fillMaxWidth()
  ) {
    BannerImage(university.imgBanner)
    CircleBackButton(onClick = onBackClick, modifier = Modifier.padding(top = DIMENS_16dp, start = DIMENS_8dp))
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 138.dp)
    ) {
      HeaderDetail(university.name, university.imgLogo)
    }
  }
}

@Composable
fun BannerImage(imgBanner: String) {
  AsyncImage(
    model = imgBanner,
    contentDescription = null,
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .fillMaxWidth()
      .height(158.dp)
  )
}

@Composable
fun HeaderDetail(
  universityName: String,
  imgLogo: String
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = DIMENS_16dp, end = DIMENS_16dp, bottom = DIMENS_16dp),
    verticalAlignment = Alignment.Bottom,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = universityName.uppercase(),
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold
      ),
      maxLines = 3,
      modifier = Modifier.weight(1f)
    )

    Spacer(modifier = Modifier.width(DIMENS_8dp))

    Card(
      colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background,
      ),
      modifier = Modifier
        .width(112.dp)
        .height(101.dp),
      shape = RoundedCornerShape(DIMENS_12dp),
      border = BorderStroke(width = 1.dp, color = GrayBorderStroke),
      elevation = CardDefaults.cardElevation(
        defaultElevation = DIMENS_4dp
      ),
    ) {
      AsyncImage(
        model = imgLogo,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
      )
    }
  }
}

@Composable
fun Description(description: String) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = DIMENS_16dp, end = DIMENS_16dp, bottom = DIMENS_16dp),
  ) {
    Text(stringResource(R.string.description), fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(DIMENS_4dp))
    Text(description, textAlign = TextAlign.Justify)
  }
}

@Composable
fun Faculties(university: University) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = DIMENS_16dp, end = DIMENS_16dp, bottom = DIMENS_16dp),
  ) {
    Text(text = stringResource(R.string.faculties_and_programs), fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(DIMENS_4dp))
    university.faculty.forEach {
      FacultyItem(faculty = it.name, programs = it.majors)
    }
  }
}

@Composable
private fun FacultyItem(faculty: String, programs: List<Major>) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(bottom = DIMENS_8dp),
    shape = RoundedCornerShape(0),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.background
    ),
    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(DIMENS_12dp)
    ) {
      Text(faculty, fontWeight = FontWeight.Bold)
      val programsText = programs.joinToString(", ") { it.majorName }
      Text(text = programsText, textAlign = TextAlign.Start)
    }
  }
}

@Composable
fun ButtonToWebsite(onClick: () -> Unit) {
  Button (
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = DIMENS_16dp, end = DIMENS_16dp, bottom = DIMENS_16dp),
    onClick = onClick
  ) {
    Text(stringResource(R.string.visit_website), color = MaterialTheme.colorScheme.onPrimary)
  }
}



@Preview(showBackground = true)
@Composable
private fun DetailUniversityScreenPreview() {
  val university = universityLists
  MajorsmatchTheme {
    DetailsUniversityContent(
      onBackClick = {},
      university =  university[0]
    )
  }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailUniversityScreenPreviewDark() {
  val university = universityLists
  MajorsmatchTheme {
    DetailsUniversityContent(
      onBackClick = {},
      university =  university[0]
    )
  }
}