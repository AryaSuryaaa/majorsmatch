package com.anugrah.majorsmatch.ui.screen.splash

import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin

data class SplashUiState (
  val onBoarding: Boolean = false,
  val isLogin: DataLogin? = null
)