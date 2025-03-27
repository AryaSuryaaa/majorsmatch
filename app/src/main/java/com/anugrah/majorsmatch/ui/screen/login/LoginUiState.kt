package com.anugrah.majorsmatch.ui.screen.login

import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.data.remote.apiresponse.LoginResponse

data class LoginUiState (
  val email: String = "",
  val password: String = "",
  val loginResult: ResultState<LoginResponse> = ResultState.Idle
)