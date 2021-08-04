package com.soft.credit911.ui.Chat

import com.soft.credit911.datamodel.ChatObject
import java.util.*

interface ChatContract {
    interface View {
        fun notifyAdapterObjectAdded(position: Int)
        fun scrollChatDown()
    }

    interface Presenter {
        fun attachView(view: View?)
        //val chatObjects: ArrayList<ChatObject>?
        fun onEditTextActionDone(inputText: String?)
       // fun getChatObjects(): ArrayList<ChatObject>?
    }
}