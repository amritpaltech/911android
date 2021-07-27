package com.soft.credit911.ui.Chat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.soft.credit911.ui.Chat.ChatContract;
import com.soft.credit911.adaptor.MessageDetailsAdapter;
import com.soft.credit911.ui.Chat.Presenter.ChatPresenter;
import com.soft.credit911.databinding.ActivityChatBinding;
import com.soft.credit911.databinding.ToolbarBinding;

public class ChatActivity extends AppCompatActivity implements ChatContract.View {

    private ActivityChatBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    private ChatPresenter presenter;
    private MessageDetailsAdapter messageDetailsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityChatBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);
        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Chat");
        toolbarBinding.navigationIcon.setOnClickListener(v -> {
            onBackPressed();
        });
        layoutBinding.editMsg.setOnEditorActionListener(searchBoxListener);

        this.presenter = new ChatPresenter();
        presenter.attachView(this);

        this.messageDetailsAdapter = new MessageDetailsAdapter(this);
        if (presenter.getChatObjects() != null) {
            messageDetailsAdapter.add(presenter.getChatObjects());
            layoutBinding.rvMessage.setAdapter(messageDetailsAdapter);

        }
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

    @Override
    public void notifyAdapterObjectAdded(int position) {
        this.messageDetailsAdapter.notifyItemInserted(position);
    }

    @Override
    public void scrollChatDown() {
        this.layoutBinding.rvMessage.scrollToPosition(presenter.getChatObjects().size() - 1);
    }

    private EditText.OnEditorActionListener searchBoxListener = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView tv, int actionId, KeyEvent keyEvent) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (!TextUtils.isEmpty(layoutBinding.editMsg.getText().toString())) {
                    presenter.onEditTextActionDone(layoutBinding.editMsg.getText().toString());
                    layoutBinding.editMsg.getText().clear();
                    return true;
                }
            }
            return false;
        }
    };
}