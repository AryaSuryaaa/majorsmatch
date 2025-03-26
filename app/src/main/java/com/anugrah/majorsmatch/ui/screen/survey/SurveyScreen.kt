package com.anugrah.majorsmatch.ui.screen.survey

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
      modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
      Text("Kirim")
    }
  }
}
