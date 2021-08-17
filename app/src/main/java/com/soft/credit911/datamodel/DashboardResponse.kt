package com.soft.credit911.datamodel

import com.google.gson.annotations.SerializedName

class DashboardResponse {
    @SerializedName("data")
    var data: Data? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null

    inner class Data {
        @SerializedName("credit_report")
        var creditReport: CreditReport? = null

        @SerializedName("credit_report_history")
        var creditReportHistory: ArrayList<CreditReportHistoryItem>? = null

        @SerializedName("userinfo")
        var userinfo: Userinfo? = null

        @SerializedName("document_alert")
        var document_alert: DocumentAlert? = null
    }

    inner  class DocumentAlert{
        @SerializedName("color_code")
        var color_code: String? = null

        @SerializedName("message")
        var message: String? = null

        @SerializedName("action")
        var action: String? = null

        @SerializedName("alert_status")
        var alert_status: String? = null

    }

    inner class Userinfo {
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

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("first_name")
        var firstName: String? = null

        @SerializedName("email")
        var email: String? = null

        @SerializedName("status")
        var status: String? = null
    }

    inner class RolesItem {
        @SerializedName("updated_at")
        var updatedAt: String? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("created_at")
        var createdAt: String? = null

        @SerializedName("pivot")
        var pivot: Pivot? = null

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("guard_name")
        var guardName: String? = null

        @SerializedName("slug")
        var slug: String? = null
    }

    inner class Pivot {
        @SerializedName("role_id")
        var roleId: Int? = null

        @SerializedName("model_type")
        var modelType: String? = null

        @SerializedName("model_id")
        var modelId: Int? = null
    }

    inner class CreditReport {
        @SerializedName("score")
        var score: String? = null

        @SerializedName("report_factor")
        var reportFactor: String? = null

        @SerializedName("report_data")
        var reportData: Any? = null

        @SerializedName("updated_at")
        var updatedAt: Any? = null

        @SerializedName("user_id")
        var userId: Int? = null

        @SerializedName("created_at")
        var createdAt: Any? = null

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("score_date")
        var scoreDate: String? = null

        @SerializedName("source")
        var source: String? = null

        @SerializedName("report_identifier")
        var reportIdentifier: Any? = null

        @SerializedName("next_date")
        var nextDate: String? = null

        @SerializedName("report_date")
        var reportDate: String? = null
    }

    inner class CreditReportHistoryItem {
        @SerializedName("score")
        var score: String? = null

        @SerializedName("score_date")
        var scoreDate: String? = null
    }
}