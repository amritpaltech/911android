package com.soft.credit911.datamodel

abstract class ChatObject {
    var text: String? = null
    var userTime: String? = null
    var image = 0
    abstract val type: Int

    companion object {
        const val SEND_MESSAGE = 0
        const val RECEIVED_MESSAGE = 1
    }
}