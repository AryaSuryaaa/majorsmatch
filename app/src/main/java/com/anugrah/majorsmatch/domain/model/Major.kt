package com.anugrah.majorsmatch.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Major(
  val majorName: String,
  val majorCode: String,
  val facultyCode: String
) : Parcelable