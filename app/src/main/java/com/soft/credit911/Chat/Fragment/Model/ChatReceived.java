package com.soft.credit911.Chat.Fragment.Model;

public class ChatReceived extends ChatObject {
    @Override
    public int getType() {
        return ChatObject.RECEIVED_MESSAGE;
    }
}
