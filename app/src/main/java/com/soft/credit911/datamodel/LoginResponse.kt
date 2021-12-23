package com.soft.credit911.datamodel

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("data")
    var data: DataLoginObj? = null

    @SerializedName("message")
    val message: String? = null

    @SerializedName("status")
    val status: String? = null
}

     class DataLoginObj {
        @SerializedName("user_avatar")
        var userAvatar: String? = null

        @SerializedName("api_token")
        val apiToken: String? = null

        @SerializedName("last_name")
        var lastName: String? = null

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
        var phoneNumber: String? = null

        @SerializedName("id")
        val id = 0

        @SerializedName("first_name")
        var firstName: String? = null

        @SerializedName("email")
        val email: String? = null

        @SerializedName("status")
        val status: String? = null


        @SerializedName("postal_code")
        val postal_code: String? = null


        @SerializedName("state")
        val state: String? = null


        @SerializedName("city")
        val city: String? = null


        @SerializedName("address_line_2")
        val address_line_2: String? = null

        @SerializedName("address_line_1")
        val address_line_1: String? = null
    }
