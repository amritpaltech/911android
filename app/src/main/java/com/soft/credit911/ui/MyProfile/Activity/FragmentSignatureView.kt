package com.soft.credit911.ui.MyProfile.Activity

import android.content.Intent
import com.ing.quiz.network.RestClient
import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.R
import com.soft.credit911.Utils.loadImg
import com.soft.credit911.ui.signature.SignatureProfileActivity
import com.soft.credit911.ui.signature.signatureObject
import kotlinx.android.synthetic.main.activity_view_signature.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.HashMap

class FragmentSignatureView:BaseFragment() {
    override fun getLayoutID(): Int {
        return R.layout.activity_view_signature
    }


    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

    override fun onViewCreated() {
        EventBus.getDefault().register(this)
        toolbarTitle.text = "My Signature"
        navigationIcon.setOnClickListener {
            super.onBackPress()
        }
        tvViewSignature.setOnClickListener {
            startActivity( Intent(activity, SignatureProfileActivity::class.java))
            super.onBackPress()
        }
        signature_pad.loadImg("https://911credit.s3.amazonaws.com/"+arguments?.getString("image"))
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: signatureObject?) {
        childFragmentManager.popBackStack()
    }
}