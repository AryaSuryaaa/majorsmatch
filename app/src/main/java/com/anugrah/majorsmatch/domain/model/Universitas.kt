package com.anugrah.majorsmatch.domain.model

data class Universitas(
  val id: Int,
  val nama: String,
  val imgLogo: String,
  val imgBanner: String,
  val deskripsi: String,
  val acronym: String,
  val fakultas: List<Fakultas>
)
