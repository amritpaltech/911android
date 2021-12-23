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



        @SerializedName("token_2fa_expiry")
        val token2faExpiry: String? = null



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
        var postal_code: String? = null


        @SerializedName("state")
        var state: String? = null


        @SerializedName("city")
        var city: String? = null


        @SerializedName("address_line_2")
        var address_line_2: String? = null

        @SerializedName("address_line_1")
        var address_line_1: String? = null


        @SerializedName("scancredit_username")
        val scancredit_username: String? = null

        @SerializedName("scancredit_password")
        val scancredit_password: String? = null

        @SerializedName("suffix")
        var suffix: String? = null

        @SerializedName("middle_name")
        var middle_name: String? = null

        @SerializedName("country")
        var country: String? = null
    }
