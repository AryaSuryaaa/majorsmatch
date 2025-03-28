package com.anugrah.majorsmatch.ui.screen.survey

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.data.dummy.questionList
import com.anugrah.majorsmatch.ui.components.QuestionCard

@SuppressLint("MutableCollectionMutableState")
@Composable
fun SurveyScreen(
  navHostController: NavHostController
) {
  var answers by remember { mutableStateOf(mutableMapOf<String, Boolean?>()) }

  Column(
    modifier = Modifier
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(vertical = 8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(onClick = { navHostController.popBackStack() }) {
        Icon(
          imageVector = Icons.AutoMirrored.Default.ArrowBack,
          contentDescription = stringResource(R.string.back),
          tint = MaterialTheme.colorScheme.onSurface
        )
      }
      Text(
        text = "SURVEY",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(start = 8.dp)
      )
    }
    LazyColumn(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      contentPadding = PaddingValues(16.dp)
    ) {
      items(questionList) { question ->
        QuestionCard(
          text = question.text,
          selected = answers[question.text],
          onSelectionChange = { newAnswer ->
            answers = answers.toMutableMap().apply { put(question.text, newAnswer) }
          },
        )
      }
    }

    Button(
      onClick = { /* Kirim jawaban */ },
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Text("Kirim")
    }
  }
}
