//package com.soft.credit911.ui.Chat.Presenter
//
//import com.soft.credit911.R
//import com.soft.credit911.datamodel.ChatObject
//import com.soft.credit911.datamodel.ChatReceived
//import com.soft.credit911.datamodel.ChatSend
//import com.soft.credit911.ui.Chat.ChatContract
//import com.soft.credit911.ui.Chat.ChatContract.Presenter
//import java.util.*
//import kotlin.collections.ArrayList
//
//class ChatPresenter : Presenter {
//     override val chatObjects= ArrayList<ChatObject>()
//
//    private var view: ChatContract.View? = null
//    override fun attachView(view: ChatContract.View?) {
//        this.view = view
//    }
//
//    override fun getChatObjects(): ArrayList<ChatObject>? {
//        return chatObjects
//    }
//
//    override fun onEditTextActionDone(inputText: String?) {
//        val inputObject = ChatSend()
//        inputObject.text =
//            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown."
//        chatObjects.add(inputObject)
//        view!!.notifyAdapterObjectAdded(chatObjects.size - 1)
//        view!!.scrollChatDown()
//    }
//
//    init {
//        // Create the ArrayList for the chat objects
//        chatObjects = ArrayList()
//
//        // Add an initial greeting message
//        val greetingMsg = ChatReceived()
//        greetingMsg.text =
//            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old."
//        greetingMsg.image = R.drawable.chris_hemsworth
//        greetingMsg.userTime = "14:12"
//        chatObjects.add(greetingMsg)
//    }
//}