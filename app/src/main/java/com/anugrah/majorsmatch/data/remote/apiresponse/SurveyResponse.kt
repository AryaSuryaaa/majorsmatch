package com.anugrah.majorsmatch.data.remote.apiresponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurveyResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: SurveyData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
): Parcelable

@Parcelize
data class SurveyData(

	@field:SerializedName("majors")
	val majors: List<MajorsItemSurvey>,

	@field:SerializedName("universities")
	val universities: List<UniversitiesItemSurvey>
): Parcelable

@Parcelize
data class UniversitiesItemSurvey(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("majors")
	val majors: List<MajorsItemSurvey>,

	@field:SerializedName("acronym")
	val acronym: String,

	@field:SerializedName("websiteUrl")
	val websiteUrl: String,

	@field:SerializedName("imgBannerUrl")
	val imgBannerUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("imgLogoUrl")
	val imgLogoUrl: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
): Parcelable

@Parcelize
data class MajorsItemSurvey(

	@field:SerializedName("facultyName")
	val facultyName: String,

	@field:SerializedName("majorName")
	val majorName: String,

	@field:SerializedName("majorCode")
	val majorCode: String,

	@field:SerializedName("facultyCode")
	val facultyCode: String,

	@field:SerializedName("universityId")
	val universityId: Int,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
): Parcelable
