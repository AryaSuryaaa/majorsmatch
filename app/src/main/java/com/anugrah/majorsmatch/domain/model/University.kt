package com.anugrah.majorsmatch.domain.model

data class University(
  val id: Int,
  val name: String,
  val imgLogo: String,
  val imgBanner: String,
  val description: String,
  val acronym: String,
  val faculty: List<Faculty>,
  val website: String
)
