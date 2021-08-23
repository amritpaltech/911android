package com.soft.credit911.datamodel

class data_notification {

    val data: ArrayList<AppNotification>?=null
    val links:Links?=null

    val status: String? = null
    class AppNotification{
        val id: String? = null
        val description: String? = null
        val created_at: String? = null
    }
    class Links{
     val next:String?=null
    }
}