package com.anugrah.majorsmatch.domain.model

data class Faculty(
  val name: String,
  val code: String,
  val majors: List<Major>
)