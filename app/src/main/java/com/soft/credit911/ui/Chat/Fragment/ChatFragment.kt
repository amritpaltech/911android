package com.soft.credit911.ui.Chat.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.soft.credit911.R
import com.soft.credit911.adaptor.ChatDetailsAdapter
import com.soft.credit911.databinding.FragmentChatBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.datamodel.DemoModel
import java.util.*

class ChatFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var fragmentBinding: FragmentChatBinding? = null
    private var toolbarBinding: ToolbarBinding? = null
    private val demoModelList = ArrayList<DemoModel>()
    private var chatDetailsAdapter: ChatDetailsAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentChatBinding.inflate(inflater, container, false)
        toolbarBinding = fragmentBinding!!.toolbarLayout
        toolbarBinding!!.toolbarTitle.text = "Chat"
        toolbarBinding!!.navigationIcon.visibility = View.GONE
        chatDetailsAdapter = context?.let { ChatDetailsAdapter(demoModelList, it) }
        fragmentBinding!!.rvChat.adapter = chatDetailsAdapter
        prepareChatData()
        return fragmentBinding!!.root
    }

    private fun prepareChatData() {
        var demoModel = DemoModel(
            R.drawable.chris_hemsworth,
            "John Smith",
            "Lorem ipsum dolor sit ament conseterur sadipscing elitr ,sed",
            "12.05",
            "2"
        )
        demoModelList.add(demoModel)
        demoModel = DemoModel(
            R.drawable.chris_hemsworth,
            "Harry Singh",
            "Lorem ipsum dolor sit ament conseterur sadipscing elitr ,sed",
            "10.05",
            "2"
        )
        demoModelList.add(demoModel)
        chatDetailsAdapter!!.notifyDataSetChanged()
    }



}