package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_2dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp

@Composable
fun CardUniversity(
  imgBanner: String,
  acronym: String,
  onClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  Box(
    modifier = Modifier
      .width(157.dp)
      .height(162.dp),
  ) {
    Card(
      onClick = onClick,
      modifier = Modifier.fillMaxSize(),
      shape = RoundedCornerShape(DIMENS_12dp),
      elevation = CardDefaults.cardElevation(
        defaultElevation = DIMENS_2dp
      )
    ) {
      AsyncImage(
        model = imgBanner,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )
    }

    Box(
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .padding(bottom = DIMENS_8dp)
        .width(61.dp)
        .height(36.dp)
        .background(color = Color(0xCC08D8D5), shape = RoundedCornerShape(topStart = 18.dp, topEnd = 0.dp, bottomStart = 18.dp, bottomEnd = 0.dp)),
      contentAlignment = Alignment.Center,
    ) {
      Text(acronym, fontWeight = FontWeight.Bold)
    }
  }

}

@Preview
@Composable
private fun CardUniversityPreview() {
  CardUniversity("https://awsimages.detik.net.id/community/media/visual/2021/06/11/kampus-its-surabaya.jpeg?w=600&q=90", "ITB")
}