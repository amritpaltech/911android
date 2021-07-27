package com.soft.credit911.datamodel

class ChatReceived : ChatObject() {
    override val type: Int
        get() = RECEIVED_MESSAGE
}