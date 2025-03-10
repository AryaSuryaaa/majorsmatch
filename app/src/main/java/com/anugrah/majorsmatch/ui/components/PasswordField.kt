package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.anugrah.majorsmatch.R

@Composable
fun PasswordField(
  label: String,
  value: String,
  onValueChange: (String) -> Unit,
  isError: Boolean = false,
  valueError: String? = null,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
    keyboardType = KeyboardType.Password,
    imeAction = ImeAction.Done
  ),
  modifier: Modifier = Modifier,
  onDone: () -> Unit = {},
  onNext: () -> Unit = {},
) {
  var passwordVisible by remember { mutableStateOf(false) }

  OutlinedTextField(
    label = { Text(text = label) },
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    maxLines = 1,
    isError = isError,
    supportingText = {
      if (valueError != null) {
        Text(text = valueError, color = MaterialTheme.colorScheme.error)
      }
    },
    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
    trailingIcon = {
      val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
      IconButton(onClick = { passwordVisible = !passwordVisible }) {
        Icon(
          imageVector = image,
          contentDescription = if (passwordVisible) stringResource(R.string.hide_password) else stringResource(R.string.show_password)
        )
      }
    },
    keyboardOptions = keyboardOptions,
    keyboardActions = KeyboardActions(
      onNext = { onNext() },
      onDone = { onDone() }
    ),
  )
}
