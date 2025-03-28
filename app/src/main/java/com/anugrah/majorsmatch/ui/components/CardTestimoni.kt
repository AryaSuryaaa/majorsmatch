package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.util.lerp
import com.anugrah.majorsmatch.data.remote.apiresponse.DataTestimony
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp

@Composable
fun CardTestimony(
  page: Int,
  pageOffset: Float = 0.0f,
  testimony: List<DataTestimony>,
  textOverflow: TextOverflow = TextOverflow.Clip
) {
  Card(
    shape = RoundedCornerShape(DIMENS_12dp),

    modifier = Modifier
      .graphicsLayer {
        lerp(
          start = 0.85f,
          stop = 1f,
          fraction = 1f - pageOffset.coerceIn(0f, 1f)
        ).also { scale ->
          scaleX = scale
          scaleY = scale
        }

        alpha = lerp(
          start = 0.5f,
          stop = 1f,
          fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
      }
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(DIMENS_16dp),
      horizontalAlignment = Alignment.Start,
    ) {
      Text(testimony[page].fullName, fontWeight = FontWeight.Bold)
      Spacer(modifier = Modifier.height(DIMENS_8dp))
      Text(
        testimony[page].testimony,
        overflow = textOverflow
      )
    }
  }
}

