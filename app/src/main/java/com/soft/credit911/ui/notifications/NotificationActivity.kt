package com.soft.credit911.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.adaptor.NotificationAdaptor
import com.soft.credit911.datamodel.data_notification
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class NotificationActivity : SubBaseActivity() {

    var viewModel=NotiificationViewModel()
    var adap:NotificationAdaptor?=null
    var serverUrl="api/get-user-activities"
    var isLoading=false
    var dataList:MutableList<data_notification.AppNotification>?= mutableListOf()
    override fun getLayoutID(): Int {
       return R.layout.activity_notification
    }

    override fun onViewCreated() {
        toolbarTitle.text = "Notification"
        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        intiView()
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
            if(  it.data?.size?:0>0) {
                isLoading = false
                it.data?.let { it1 -> dataList?.addAll(it1) }
                adap?.notifyDataSetChanged()
                var linkstr = it.links?.next
                linkstr = linkstr?.split("page=")?.get(1)
                serverUrl = serverUrl + "?page=" + linkstr
            }
        })

    }

    private fun intiView() {

        setLazyLoaderForRecyclerView();
        setList()
        getData()
    }


    fun setList(){
        adap= dataList?.let {
            NotificationAdaptor(it){

            }
        }
        rv_notification.adapter = adap


    }
    fun setLazyLoaderForRecyclerView() {
        val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = rv_notification.layoutManager?.getChildCount()
                val totalItemCount = rv_notification.layoutManager?.getItemCount()
                val firstVisibleItemPosition =
                    (rv_notification.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (visibleItemCount != null) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount!!
                        && firstVisibleItemPosition >= 0
                    ) {

                        if(!isLoading) {
                            getData()
                        }
                    }

                }
            }
        }

        rv_notification.addOnScrollListener(recyclerViewOnScrollListener)
    }

    fun getData(){
        isLoading=true
        viewModel.getNotifications(serverUrl)
    }
}