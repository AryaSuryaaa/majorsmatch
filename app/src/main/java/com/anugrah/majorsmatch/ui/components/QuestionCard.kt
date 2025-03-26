package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp

@Composable
fun QuestionCard(
  text: String,
  selected: Boolean?,
  onSelectionChange: (Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = MaterialTheme.shapes.medium
  ) {
    Column(modifier = Modifier.padding(DIMENS_16dp)) {
      Text(text, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 16.dp))

      Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
        RadioButton(
          selected = selected == true,
          onClick = { onSelectionChange(true) }
        )
        Text("Yes", modifier = Modifier.clickable { onSelectionChange(true) })
      }

      Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
          selected = selected == false,
          onClick = { onSelectionChange(false) }
        )
        Text("No", modifier = Modifier.clickable { onSelectionChange(false) })
      }
    }
  }
}


@Preview
@Composable
private fun QuestionCardPreview() {
  QuestionCard(
    "Halo", true, modifier = Modifier,
    onSelectionChange = {  }
  )
}