package com.anugrah.majorsmatch.domain.model

import com.anugrah.majorsmatch.data.remote.apiresponse.DataUniversity

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

fun DataUniversity.toUniversity(): University {
  val faculties = this.majors.groupBy { it.facultyName to it.facultyCode }
    .map { (key, majorsItems) ->
      val (facultyName, facultyCode) = key
      Faculty(
        name = facultyName,
        code = facultyCode,
        majors = majorsItems.map { majorItem ->
          Major(
            majorName = majorItem.majorName,
            majorCode = majorItem.majorCode,
            facultyCode = majorItem.facultyCode
          )
        }
      )
    }

  return University(
    id = this.id,
    name = this.name,
    imgLogo = this.imgLogoUrl,
    imgBanner = this.imgBannerUrl,
    description = this.description,
    acronym = this.acronym,
    faculty = faculties,
    website = this.websiteUrl
  )
}
