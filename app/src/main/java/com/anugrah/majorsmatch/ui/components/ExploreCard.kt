package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp
import com.anugrah.majorsmatch.ui.theme.GrayBorderStroke

@Composable
fun ExploreCard(
  onClick: () -> Unit = {},
  imgLogo: String,
  universityAcronym: String
) {
  Card(
    onClick =  onClick,
    shape = RoundedCornerShape(DIMENS_12dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.background
    ),
    border = BorderStroke(width = 1.dp, color = GrayBorderStroke),
    modifier = Modifier
      .width(113.dp)
      .height(152.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxSize().padding(DIMENS_8dp)
    ) {
      AsyncImage(
        model = imgLogo,
        contentDescription = null,
        modifier = Modifier.weight(1f).fillMaxWidth()
      )
      Text(
        universityAcronym,
        modifier = Modifier.padding(top = DIMENS_8dp).fillMaxWidth(),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
      )
    }
  }
}

@Preview
@Composable
private fun ExploreCardPreview() {
  ExploreCard(
    imgLogo = "https://www.academicindonesia.com/wp-content/uploads/2016/09/Logo-UI.png",
    universityAcronym = "Universitas Indonesia"
  )
}