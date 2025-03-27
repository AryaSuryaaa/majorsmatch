package com.anugrah.majorsmatch.data.remote.apiresponse

import com.google.gson.annotations.SerializedName

data class GetUniversitiesResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<DataUniversity>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataUniversity(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("majors")
	val majors: List<MajorsItem>,

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
)

data class MajorsItem(

	@field:SerializedName("universityId")
	val universityId: Int,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("facultyName")
	val facultyName: String,

	@field:SerializedName("majorName")
	val majorName: String,

	@field:SerializedName("majorCode")
	val majorCode: String,

	@field:SerializedName("facultyCode")
	val facultyCode: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
