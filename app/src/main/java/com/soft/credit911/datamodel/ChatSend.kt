package com.soft.credit911.datamodel

class ChatSend : ChatObject() {
    override val type: Int
        get() = SEND_MESSAGE
}