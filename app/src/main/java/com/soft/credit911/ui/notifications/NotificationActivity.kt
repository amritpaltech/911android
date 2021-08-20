package com.soft.credit911.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.adaptor.NotificationAdaptor
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class NotificationActivity : SubBaseActivity() {

    var viewModel=NotiificationViewModel()
    override fun getLayoutID(): Int {
       return R.layout.activity_notification
    }

    override fun onViewCreated() {
        toolbarTitle.text = "Notification"
        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        intiView()
        viewModel.getNotifications()
        attachObserser()
    }

    fun attachObserser(){
        viewModel?.isLoading?.observe(this, androidx.lifecycle.Observer {
            if(it){
                showProgress()
            }else{
                hideProgress()
            }
        })

        viewModel?.dataNotification.observe(this, androidx.lifecycle.Observer {
            val notificationDetailsAdapter = it.data?.let { it1 ->
                NotificationAdaptor(it1){

                }
            }
            rv_notification.adapter = notificationDetailsAdapter
        })

    }

    private fun intiView() {


    }
}