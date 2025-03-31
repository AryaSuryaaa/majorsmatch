package com.anugrah.majorsmatch.ui.screen.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.data.dummy.universityLists
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.ui.components.MajorCard
import com.anugrah.majorsmatch.ui.components.ResultCardUniversity
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp

@Composable
fun ResultScreen(
  navHostController: NavHostController,
  modifier: Modifier = Modifier
) {
  val listUniversity = universityLists
  ResultContent(
    onClickBack = { navHostController.navigateUp() },
    listUniversity = listUniversity,
    modifier = modifier
  )
}

@Composable
fun ResultContent(
  onClickBack: () -> Unit = {},
  listUniversity: List<University>,
  modifier: Modifier = Modifier
) {
  Column {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(vertical = 8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(onClick = onClickBack) {
        Icon(
          imageVector = Icons.AutoMirrored.Default.ArrowBack,
          contentDescription = stringResource(R.string.back),
          tint = MaterialTheme.colorScheme.onSurface
        )
      }
      Text(
        text = "Survey Result",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(start = 8.dp)
      )
    }
    Column(
      modifier = Modifier.padding(DIMENS_16dp)
    ) {
      SectionTitle("Matched major for you")
      LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        verticalArrangement = Arrangement.spacedBy(DIMENS_8dp),
        horizontalArrangement = Arrangement.spacedBy(DIMENS_8dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        val allMajors = listUniversity.flatMap { university ->
          university.faculty.flatMap { it.majors }
        }
        items(allMajors) { major ->
          MajorCard(majorName = major.majorName)
        }
      }
      SectionTitle("Matched university")
      LazyColumn(
        verticalArrangement = Arrangement.spacedBy(DIMENS_16dp)
      ) {
        items(listUniversity) { university ->
          ResultCardUniversity(
            imgLogo = university.imgLogo,
            universityName = university.name
          )
        }
      }

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