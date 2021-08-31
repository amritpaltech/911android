package com.soft.credit911.ui.dashboard

import android.view.View
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.ChartUtils
import com.soft.credit911.R
import com.soft.credit911.adaptor.CreditHistoryListAdaptor
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.fcm.notificationObject
import kotlinx.android.synthetic.main.activity_credit_history.*
import kotlinx.android.synthetic.main.toolbar.*

class ActivityCreditHistory:SubBaseActivity() {

    var historyData:ArrayList<DashboardResponse.CreditReportHistoryItem>?=null
    override fun getLayoutID(): Int {
        return R.layout.activity_credit_history
    }

    override fun onViewCreated() {
        toolbarTitle.text="Credit History"
        historyData=intent.getSerializableExtra("history") as ArrayList<DashboardResponse.CreditReportHistoryItem>
        ChartUtils().setChartData(chart,historyData)
        val myList2=historyData
        myList2?.sortByDescending{it.scoreDate}
        var adap= myList2?.let {
            CreditHistoryListAdaptor(it){

            }
        }
        rv_documentCompulsary.adapter=adap

        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }

        if(intent.extras?.containsKey("pushData")==true){
            pushDataMain = intent?.getSerializableExtra("pushData") as notificationObject
            showPushDialog()
        }
    }
}