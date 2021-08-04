package com.soft.credit911.ui.Chat.Activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.soft.credit911.adaptor.MessageDetailsAdapter
import com.soft.credit911.databinding.ActivityChatBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.ui.Chat.ChatContract

class ChatActivity : AppCompatActivity(), ChatContract.View {
    private var layoutBinding: ActivityChatBinding? = null
    private var toolbarBinding: ToolbarBinding? = null
//    private var presenter: ChatPresenter? = null
    private var messageDetailsAdapter: MessageDetailsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityChatBinding.inflate(layoutInflater)
        val view = layoutBinding!!.root
        setContentView(view)
        toolbarBinding = layoutBinding!!.toolbarLayout
        toolbarBinding!!.toolbarTitle.text = "Chat"
        toolbarBinding!!.navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        layoutBinding!!.editMsg.setOnEditorActionListener(searchBoxListener)
//        presenter = ChatPresenter()
//        presenter!!.attachView(this)
//        messageDetailsAdapter = MessageDetailsAdapter(this)
//        if (presenter?.chatObjects != null) {
//            messageDetailsAdapter!!.add(presenter?.chatObjects)
//            layoutBinding!!.rvMessage.adapter = messageDetailsAdapter
//        }
        /* layoutBinding.editMsg.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    ChatMessageModel chatMessageModel = new ChatMessageModel(layoutBinding.editMsg.getText().toString(), true);
                    chatMessage.add(chatMessageModel);

                    ChatMessageModel chatMessageMode2 = new ChatMessageModel(layoutBinding.editMsg.getText().toString(), false);
                    chatMessage.add(chatMessageMode2);
                    messageDetailsAdapter.notifyDataSetChanged();

                }
                return true;
            }
        });

       layoutBinding.sendBtn.setOnClickListener(v -> {
         String txt = layoutBinding.editMsg.getText().toString().trim();
            if (!txt.isEmpty()) {
                ChatMessageModel chatMessageModel = new ChatMessageModel();
                chatMessageModel.setMessage("How are you doing? This is a long chatMessage that should probably wrap.");
                chatMessageModel.setTime("12:05");
                chatMessage.add(chatMessageModel);
            messageDetailsAdapter.addList(chatMessage);

            }


        });
*/
    }

    override fun notifyAdapterObjectAdded(position: Int) {
        messageDetailsAdapter!!.notifyItemInserted(position)
    }

    override fun scrollChatDown() {
//        layoutBinding!!.rvMessage.scrollToPosition(presenter!!.chatObjects!!.size - 1)
    }

    private val searchBoxListener = TextView.OnEditorActionListener { tv, actionId, keyEvent ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (!TextUtils.isEmpty(layoutBinding!!.editMsg.text.toString())) {
//                presenter!!.onEditTextActionDone(layoutBinding!!.editMsg.text.toString())
                layoutBinding!!.editMsg.text.clear()
                return@OnEditorActionListener true
            }
        }
        false
    }
}