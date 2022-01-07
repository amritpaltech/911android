package com.soft.credit911.datamodel

import com.google.gson.annotations.SerializedName

class UpdateProfileResponse {
    @SerializedName("data")
    var data: Data? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null

    inner class Data {
        @SerializedName("user_avatar")
        var userAvatar: String? = null

        @SerializedName("api_token")
        var apiToken: String? = null

        @SerializedName("roles")
        var roles: List<RolesItem>? = null

        @SerializedName("last_name")
        var lastName: String? = null

        @SerializedName("created_at")
        var createdAt: String? = null

        @SerializedName("email_verified_at")
        var emailVerifiedAt: String? = null

        @SerializedName("token_2fa_expiry")
        var token2faExpiry: String? = null

        @SerializedName("updated_at")
        var updatedAt: String? = null

        @SerializedName("token_2fa")
        var token2fa: String? = null

        @SerializedName("phone_number")
        var phoneNumber: String? = null

        @SerializedName("signature")
        var signature: String? = null

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("first_name")
        var firstName: String? = null

        @SerializedName("email")
        var email: String? = null

        @SerializedName("status")
        var status: String? = null
    }

    inner class Pivot {
        @SerializedName("role_id")
        var roleId: Int? = null

        @SerializedName("model_type")
        var modelType: String? = null

        @SerializedName("model_id")
        var modelId: Int? = null
    }

    inner class RolesItem {
        @SerializedName("updated_at")
        var updatedAt: String? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("created_at")
        var createdAt: String? = null

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("guard_name")
        var guardName: String? = null

        @SerializedName("slug")
        var slug: String? = null
    }
}