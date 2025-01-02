package com.iskan.dicodingevent.data.response

import com.google.gson.annotations.SerializedName

data class EventResponse(

	@field:SerializedName("listEvents")
	val listEvents: List<Event>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)