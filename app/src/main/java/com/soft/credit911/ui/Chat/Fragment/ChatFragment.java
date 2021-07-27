package com.soft.credit911.ui.Chat.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soft.credit911.adaptor.ChatDetailsAdapter;
import com.soft.credit911.datamodel.DemoModel;
import com.soft.credit911.R;
import com.soft.credit911.databinding.FragmentChatBinding;
import com.soft.credit911.databinding.ToolbarBinding;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public ChatFragment() {

    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentChatBinding fragmentBinding;
    private ToolbarBinding toolbarBinding;
    private ArrayList<DemoModel> demoModelList = new ArrayList<>();

    private ChatDetailsAdapter chatDetailsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentBinding = FragmentChatBinding.inflate(inflater, container, false);
        toolbarBinding = fragmentBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Chat");
        toolbarBinding.navigationIcon.setVisibility(View.GONE);

        chatDetailsAdapter = new ChatDetailsAdapter(demoModelList,getContext());
        fragmentBinding.rvChat.setAdapter(chatDetailsAdapter);
        prepareChatData();
        return fragmentBinding.getRoot();
    }

    private void prepareChatData() {
        DemoModel demoModel = new DemoModel(R.drawable.chris_hemsworth, "John Smith", "Lorem ipsum dolor sit ament conseterur sadipscing elitr ,sed", "12.05", "2");
        demoModelList.add(demoModel);
        demoModel = new DemoModel(R.drawable.chris_hemsworth, "Harry Singh", "Lorem ipsum dolor sit ament conseterur sadipscing elitr ,sed", "10.05", "2");
        demoModelList.add(demoModel);
        chatDetailsAdapter.notifyDataSetChanged();
    }
}