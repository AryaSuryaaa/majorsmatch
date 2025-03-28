package com.anugrah.majorsmatch.ui.screen.profile

import com.anugrah.majorsmatch.data.remote.apiresponse.DataUser

data class AccountUiState (
  val dataUser: DataUser? = null,
  val theme: String = "Auto",
  val language: String = "en"
)