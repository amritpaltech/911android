package com.soft.credit911.ui.dashboard

import android.view.View
import com.google.gson.Gson
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.ChartUtils
import com.soft.credit911.R
import com.soft.credit911.adaptor.CreditHistoryListAdaptor
import com.soft.credit911.adaptor.CreditreportListAdaptor
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.fcm.notificationObject
import kotlinx.android.synthetic.main.activity_credit_history.*
import kotlinx.android.synthetic.main.activity_credit_report.*
import kotlinx.android.synthetic.main.toolbar.*
import org.json.JSONArray
import org.json.JSONObject

class ActivityCreditReport:SubBaseActivity() {

    var creditReport:DashboardResponse.CreditReport?=null
    override fun getLayoutID(): Int {
        return R.layout.activity_credit_report
    }

    override fun onViewCreated() {
        toolbarTitle.text="Credit History"
        creditReport=intent.getSerializableExtra("creditData") as DashboardResponse.CreditReport

        var myStr= creditReport?.reportFactor
        try {
//            myStr = myStr?.replace("\"", "")
            var json= JSONArray(myStr)
            var adap = CreditreportListAdaptor(json!!) {
            }
            rvFactor.adapter = adap
        }catch (e:Exception){
           e.printStackTrace()
        }
        navigationIcon.setOnClickListener { v: View? -> onBackPressed() }


        val reportDate = creditReport?.reportDate
        val nextDate =creditReport?.nextDate
        tv_report_date.text = reportDate
        tv_next_date.text = nextDate
        source.text=creditReport?.source?:"0"
        cScore.text=creditReport?.score?:"0"
        if(intent.extras?.containsKey("pushData")==true){
            pushDataMain = intent?.getSerializableExtra("pushData") as notificationObject
            showPushDialog()
        }
    }

}