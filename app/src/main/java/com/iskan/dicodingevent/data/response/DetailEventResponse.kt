package com.iskan.dicodingevent.data.response

import com.google.gson.annotations.SerializedName

data class DetailEventResponse(

    @field:SerializedName("event")
    val event: Event,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)