package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.ui.theme.DIMENS_4dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
  query: String,
  onQueryChange: (String) -> Unit,
) {
  SearchBar(
    query = query,
    onQueryChange = onQueryChange,
    onSearch = {},
    active = false,
    leadingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSurfaceVariant
      )
    },
    placeholder = {
      Text(stringResource(R.string.search_petak))
    },
    colors = SearchBarDefaults.colors(
      containerColor = MaterialTheme.colorScheme.background
    ),

    shadowElevation = DIMENS_4dp,
    onActiveChange = {},
    modifier = Modifier.fillMaxWidth()
  ) { }
}

@Preview
@Composable
private fun SearchBarPreview() {
  SearchBarComponent(query = "", onQueryChange = {})
}