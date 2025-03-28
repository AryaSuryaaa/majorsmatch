package com.anugrah.majorsmatch.ui.screen.feedback

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anugrah.majorsmatch.MainActivity
import com.anugrah.majorsmatch.R
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.data.remote.apiresponse.DataTestimony
import com.anugrah.majorsmatch.navigation.screen.Screen
import com.anugrah.majorsmatch.ui.components.CardTestimony
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp
import com.anugrah.majorsmatch.utils.showToast

@Composable
fun FeedbackScreen(
  navHostController: NavHostController,
  viewModel: FeedbackViewModel = hiltViewModel(),
  modifier: Modifier = Modifier
) {
  val uiState by viewModel.uiState.collectAsState()
  val context = LocalContext.current

  LaunchedEffect(Unit) {
    viewModel.getUserSession()
    viewModel.getTestimony()
  }

  LaunchedEffect(uiState.submitResult) {
    val mainActivity = context as MainActivity

    when (uiState.submitResult) {
      is ResultState.Loading -> {
        mainActivity.loaderState.value = true
      }
      is ResultState.Success -> {
        mainActivity.loaderState.value = false
        context.showToast(context.getString(R.string.submit_success))
        navHostController.popBackStack()
        navHostController.navigate(Screen.Home.route) {
          popUpTo(Screen.Login.route) { inclusive = true }
        }
      }
      is ResultState.Error -> {
        mainActivity.loaderState.value = false
        val error = (uiState.submitResult as ResultState.Error).error
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
      }
      else -> {
        Unit
      }
    }
  }

  FeedbackContent(
    testimony = uiState.testimony,
    text = uiState.inputTestimony,
    updateInputTestimony = { text ->
      viewModel.updateInputTestimony(text)
    },
    submitTestimony = {
      viewModel.submitTestimony()
    },
    onClickBack = {
      navHostController.popBackStack()
    },
    modifier = Modifier.fillMaxSize()
  )
}

@Composable
fun FeedbackContent(
  testimony: List<DataTestimony>,
  text: String = "",
  updateInputTestimony: (String) -> Unit,
  submitTestimony: () -> Unit = {},
  onClickBack: () -> Unit = {},
  modifier: Modifier
) {
  Column(
    modifier = modifier
  ) {
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
        text = stringResource(R.string.feed_back).uppercase(),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(start = 8.dp)
      )
    }
    ListFeedback(
      testimony = testimony,
      modifier = Modifier.weight(1f)
    )
    SubmitFeedBack(
      text = text,
      updateInputTestimony = updateInputTestimony,
      submitTestimony = submitTestimony
    )
  }
}

@Composable
fun SubmitFeedBack(
  text: String = "",
  updateInputTestimony: (String) -> Unit = {},
  submitTestimony: () -> Unit = {}
) {
  Column(
    modifier = Modifier.padding(DIMENS_16dp)
  ) {
    OutlinedTextField (
      value = text,
      onValueChange = { updateInputTestimony(it) },
      label = { Text(text = "Input feedback here") },
      modifier = Modifier
        .padding(bottom = DIMENS_16dp)
        .fillMaxWidth()
    )

    Button(
      onClick = { submitTestimony() },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = stringResource(R.string.submit))
    }
  }
}

@Composable
fun ListFeedback(
  testimony: List<DataTestimony> = emptyList(),
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(DIMENS_16dp),
    verticalArrangement = spacedBy(DIMENS_8dp)
  ) {
    items(testimony.size) { index ->
      CardTestimony(
        testimony = testimony,
        page = index,
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun FeedbackScreenPrev() {
  FeedbackContent(
    testimony = emptyList(),
    updateInputTestimony = {},
    modifier = Modifier.fillMaxSize()
  )
}
