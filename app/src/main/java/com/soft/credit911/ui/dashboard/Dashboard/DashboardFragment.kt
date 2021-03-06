package com.soft.credit911.ui.dashboard.Dashboard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.FragmentCredReport
import com.soft.credit911.ChartUtils
import com.soft.credit911.R
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.CommonUtils.Companion.showdialog
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.datamodel.LoginResponse
import com.soft.credit911.datamodel.dataCountries
import com.soft.credit911.model.CreditReportData
import com.soft.credit911.model.ReporthistoryData
import com.soft.credit911.ui.dashboard.FragmentCreditHistory
import com.soft.credit911.ui.dashboard.LandingActivity
import com.soft.credit911.ui.documnet.FragmentDocument
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*
import org.json.JSONObject


class DashboardFragment : BaseFragment() {

    var viewModel: DashBoardViewModel? = null
    var historyData:ArrayList<DashboardResponse.CreditReportHistoryItem>?=null
    var historyData2:ArrayList<ReporthistoryData>?=null
    var creditData: CreditReportData?=null
    var reportDate=""
    var nextDate=""
    override fun getLayoutID(): Int {
        return R.layout.fragment_dashboard
    }

    override fun onViewCreated() {

    }

    override fun onResume() {
        super.onResume()
        setInformation()
    }

    fun setInformation() {
        toolbarTitle.text = "Dashboard"
        navigationIcon.visibility = View.GONE
        viewModel = DashBoardViewModel()
        attachObserver()

        tv_credit_repair_status.setOnClickListener { v: View? ->
            (activity as LandingActivity).selectCaseScreen()
        }

        tv_credit_history.setOnClickListener {
            if(historyData!=null) {
                val frg= FragmentCreditHistory()
                val bundle=Bundle()
                bundle.putSerializable("history", historyData)
                bundle.putSerializable("history2", historyData2)
                frg.arguments=bundle
                addSubContentFragment(frg)
//                val intent = Intent(activity, FragmentCreditHistory::class.java)
//                intent.putExtra("history", historyData)
//                startActivity(intent)
            }
        }

        tv_credit_report.setOnClickListener {
            if(creditData!=null) {

                val frg= FragmentCredReport()
                val bundle=Bundle()
                bundle.putSerializable("creditData", creditData)
                bundle.putString("date1", reportDate)
                bundle.putString("date2", nextDate)
                frg.arguments=bundle
                addSubContentFragment(frg)

            }
        }

        scroolableContent.visibility = View.VISIBLE
        txtErrorText.visibility = View.GONE
        if (arguments != null) {
            if (arguments?.containsKey("isError")==true && arguments?.getInt("isError") == 1) {
                scroolableContent.visibility = View.GONE
                txtErrorText.visibility = View.VISIBLE
            } else {
                viewModel?.getCreditInfo()
            }
        } else {
            viewModel?.getCreditInfo()
        }

        CardToDoc.setOnClickListener {
            addSubContentFragment(FragmentDocument())
//            val intent = Intent(activity, FragmentDocument::class.java)
//            activity?.startActivity(intent)
        }
    }

    fun attachObserver(){

        viewModel?.isLoading?.observe(viewLifecycleOwner, Observer {
            if(it){
                showProgress()
            }else{
                hideProgress()
            }

        })
        viewModel?.responseAppHomedata?.observe(viewLifecycleOwner, Observer {dashboardResponse->
            if(dashboardResponse.message!=null && dashboardResponse?.message?.length?:0>0){
                context?.let { showdialog(dashboardResponse.message, it, false) }
            }

            var loginResp= LoginResponse()

//            Log.e("aasa","asdad"+dashboardResponse.data?.userinfo?.country)
            loginResp.data=dashboardResponse.data?.userinfo;
            AppPreference(activity).userObject = loginResp
            var countryData=dataCountries()
            countryData.country=dashboardResponse.data!!.creditReportData!!.countries
            AppPreference(activity).setCountryArray(countryData)
             reportDate= dashboardResponse.data!!.creditReport!!.reportDate?:""
            nextDate = dashboardResponse.data!!.creditReport!!.nextDate?:""
            historyData=dashboardResponse.data?.creditReportHistory
            historyData2=dashboardResponse?.data?.creditReportData?.reporthistoryData
            dashboardResponse?.data?.creditReportData?.android_app_version?.let {
                (activity as LandingActivity).checkAppVersionStatus(
                    it
                )
            }
            creditData=dashboardResponse?.data?.creditReportData
            tv_report_date.text = reportDate
            tv_next_date.text = nextDate
            scroolableContent.visibility=View.VISIBLE
            score.text=dashboardResponse?.data?.creditReport?.score?:"0"
            val sscore=dashboardResponse.data?.creditReport?.score?.toInt()?:0
            if(sscore<400){
                scoreAbout.text="POOR"
            }
            else if(sscore<700){
                scoreAbout.text="NEEDS WORK"
            }else{
                scoreAbout.text="GOOD"
            }
            ChartUtils().setChartData(chart,dashboardResponse?.data?.creditReportHistory)
            if(sscore<=300){
                progress.setProgress(0.01f)
            }else if (sscore<=500){
                progress.setProgress((sscore-300)/1000.0f)
            }
            else if (sscore<=575){
                progress.setProgress(.2f+(sscore-500f)*0.0027f)
            }
            else if (sscore<=650){
                progress.setProgress(.4f+(sscore-575f)*0.0033f)
            }
            else if (sscore<=750){
                progress.setProgress(.65f+(sscore-650f)*0.0015f)
            }
            else if (sscore<=900){
                progress.setProgress(.8f+(sscore-750f)*0.0015f)
            }
            CardToDoc.visibility=View.GONE
            if(dashboardResponse?.data?.document_alert!=null
                && dashboardResponse?.data?.document_alert?.alert_status.equals("y")){
                CardToDoc.visibility=View.VISIBLE
                textMessage.text= dashboardResponse?.data?.document_alert?.message
                CardToDoc.setCardBackgroundColor(Color.parseColor(dashboardResponse?.data?.document_alert?.color_code))
            }
            (activity as LandingActivity).handlePush(dashboardResponse.data!!)


        })

        viewModel?.responseSecurity?.observe(viewLifecycleOwner, Observer {securityResponse->
            if (securityResponse.code == "100") {
//                val token = securityResponse.data!!.authToken
//                val jsResponse = securityResponse.data!!.questions
//                val intent = Intent(context, SecurityQuestionsActivity::class.java)
//                intent.putExtra("responseObj", jsResponse)
//                intent.putExtra("token", token)
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                startActivity(intent)
            }
        })
    }






}
