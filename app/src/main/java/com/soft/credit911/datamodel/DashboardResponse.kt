package com.soft.credit911.datamodel

import com.google.gson.annotations.SerializedName
import com.soft.credit911.model.CreditReportData
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

class DashboardResponse :Serializable{
    @SerializedName("data")
    var data: Data? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null

    inner class Data :Serializable{


        @SerializedName("credit_report")
        var creditReport: CreditReport? = null

        @SerializedName("credit_report_data")
        var creditReportData: CreditReportData? = null



        @SerializedName("credit_report_history")
        var creditReportHistory: ArrayList<CreditReportHistoryItem>? = null



        @SerializedName("userinfo")
        var userinfo: DataLoginObj? = null

        @SerializedName("document_alert")
        var document_alert: DocumentAlert? = null
    }

    inner  class DocumentAlert:Serializable{
        @SerializedName("color_code")
        var color_code: String? = null

        @SerializedName("message")
        var message: String? = null

        @SerializedName("action")
        var action: String? = null

        @SerializedName("alert_status")
        var alert_status: String? = null

    }







    inner class CreditReport :Serializable{
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


        @SerializedName("next_date")
        var nextDate: String? = null

        @SerializedName("report_date")
        var reportDate: String? = null
    }

    inner class CreditReportHistoryItem :Serializable{
        @SerializedName("score")
        var score: String? = null

        @SerializedName("score_date")
        var scoreDate: String? = null
    }

}