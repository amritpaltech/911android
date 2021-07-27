package com.soft.credit911.datamodel

import com.google.gson.annotations.SerializedName

class SecurityResponse {
    @SerializedName("code")
    var code: String? = null

    @SerializedName("data")
    var data: Data? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null

    inner class Data {
        @SerializedName("updated_at")
        var updatedAt: String? = null

        @SerializedName("user_id")
        var userId: Int? = null

        @SerializedName("questions")
        var questions: String? = null

        @SerializedName("created_at")
        var createdAt: String? = null

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("auth_token")
        var authToken: String? = null

        @SerializedName("status")
        var status: String? = null
    }
}