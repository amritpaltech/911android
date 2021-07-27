package com.soft.credit911.ui.Chat.Presenter;

import com.soft.credit911.ui.Chat.ChatContract;
import com.soft.credit911.datamodel.ChatSend;
import com.soft.credit911.datamodel.ChatObject;
import com.soft.credit911.datamodel.ChatReceived;
import com.soft.credit911.R;

import java.util.ArrayList;

public class ChatPresenter implements ChatContract.Presenter {

    private ArrayList<ChatObject> chatObjects;
    private ChatContract.View view;


    public ChatPresenter() {
        // Create the ArrayList for the chat objects
        this.chatObjects = new ArrayList<>();

        // Add an initial greeting message
        ChatReceived greetingMsg = new ChatReceived();
        greetingMsg.setText("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.");
        greetingMsg.setImage(R.drawable.chris_hemsworth);
        greetingMsg.setUserTime("14:12");
        chatObjects.add(greetingMsg);
    }


    @Override
    public void attachView(ChatContract.View view) {
        this.view = view;
    }

    @Override
    public ArrayList<ChatObject> getChatObjects() {
        return this.chatObjects;
    }

    @Override
    public void onEditTextActionDone(String inputText) {
        ChatSend inputObject = new ChatSend();
        inputObject.setText("Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown.");

        this.chatObjects.add(inputObject);
        view.notifyAdapterObjectAdded(chatObjects.size() - 1);

        view.scrollChatDown();
    }
}
