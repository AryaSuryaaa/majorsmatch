package com.anugrah.majorsmatch.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.anugrah.majorsmatch.R

@Composable
fun CircleBackButton(onClick: () -> Unit = {} ,modifier: Modifier = Modifier) {
  FilledIconButton(
    onClick = onClick,
    colors = IconButtonDefaults.filledIconButtonColors(
      containerColor = MaterialTheme.colorScheme.background
    ),
    modifier = modifier
  ) {
    Icon(
      imageVector = Icons.AutoMirrored.Filled.ArrowBack,
      contentDescription = stringResource(R.string.back),
      tint = MaterialTheme.colorScheme.onBackground
    )
  }
}