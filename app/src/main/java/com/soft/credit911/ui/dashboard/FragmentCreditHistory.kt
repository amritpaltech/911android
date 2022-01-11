package com.soft.credit911.ui.dashboard

import android.view.View
import com.ing.quiz.ui.base_classes.BaseFragment
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.ChartUtils
import com.soft.credit911.R
import com.soft.credit911.Utils.loadImg
import com.soft.credit911.adaptor.CreditHistoryListAdaptor
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.fcm.notificationObject
import com.soft.credit911.model.ReporthistoryData
import kotlinx.android.synthetic.main.activity_credit_history.*
import kotlinx.android.synthetic.main.toolbar.*

class FragmentCreditHistory:BaseFragment() {

    var historyData:ArrayList<DashboardResponse.CreditReportHistoryItem>?=null
    var historyData2:ArrayList<ReporthistoryData>?=null
    override fun getLayoutID(): Int {
        return R.layout.activity_credit_history
    }

    override fun onViewCreated() {
        toolbarTitle.text="Credit History"
        historyData=arguments?.getSerializable("history") as ArrayList<DashboardResponse.CreditReportHistoryItem>
        historyData2=arguments?.getSerializable("history2") as ArrayList<ReporthistoryData>
        ChartUtils().setChartData(chart,historyData)
//        val myList2=historyData2
//        myList2?.sortByDescending{it.historyData2}
        var adap= historyData2?.let {
            CreditHistoryListAdaptor(it){

            }
        }
        rv_documentCompulsary.adapter=adap

        navigationIcon.setOnClickListener { v: View? -> super.onBackPress() }
        if(arguments?.containsKey("pushData")==true){
            (activity as LandingActivity).pushDataMain = arguments?.getSerializable("pushData") as notificationObject
            (activity as LandingActivity).showPushDialog()
        }
        scoreImg1.loadImg("https://911credit.s3.amazonaws.com/misc/experian.png")
        scoreImg2.loadImg("https://911credit.s3.amazonaws.com/misc/trans_union.png")
        scoreImg3.loadImg("https://911credit.s3.amazonaws.com/misc/equifax.png")
    }
}