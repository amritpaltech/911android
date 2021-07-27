package com.soft.credit911.datamodel

import com.google.gson.annotations.SerializedName

class AuthQuestionResponse {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null
}