package com.anugrah.majorsmatch.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Faculty(
  val name: String,
  val code: String,
  val majors: List<Major>
) : Parcelable