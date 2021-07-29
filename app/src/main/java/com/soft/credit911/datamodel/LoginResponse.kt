package com.soft.credit911.datamodel

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("data")
    val data: Data? = null

    @SerializedName("message")
    val message: String? = null

    @SerializedName("status")
    val status: String? = null

    inner class Data {
        @SerializedName("user_avatar")
        var userAvatar: String? = null

        @SerializedName("api_token")
        val apiToken: String? = null

        @SerializedName("last_name")
        val lastName: String? = null

        @SerializedName("created_at")
        val createdAt: String? = null

        @SerializedName("email_verified_at")
        val emailVerifiedAt: String? = null

        @SerializedName("token_2fa_expiry")
        val token2faExpiry: String? = null

        @SerializedName("updated_at")
        val updatedAt: String? = null

        @SerializedName("token_2fa")
        val token2fa: String? = null

        @SerializedName("phone_number")
        val phoneNumber: String? = null

        @SerializedName("id")
        val id = 0

        @SerializedName("first_name")
        val firstName: String? = null

        @SerializedName("email")
        val email: String? = null

        @SerializedName("status")
        val status: String? = null
    }
}