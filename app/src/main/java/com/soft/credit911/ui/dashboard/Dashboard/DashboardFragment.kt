package com.soft.credit911.ui.dashboard.Dashboard

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils.Companion.showdialog
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.datamodel.SecurityResponse
import com.soft.credit911.ui.SecurityQuestions.SecurityQuestionsActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*

class DashboardFragment : BaseFragment() {

    var viewModel: DashBoardViewModel? = null
    override fun getLayoutID(): Int {
        return R.layout.fragment_dashboard
    }

    override fun onViewCreated() {
        setInformation()
    }


    fun setInformation() {
        toolbarTitle.text = "Dashboard"
        navigationIcon.visibility = View.GONE
        viewModel = DashBoardViewModel()
        attachObserver()

        tv_credit_repair_status.setOnClickListener { v: View? ->
            val intent = Intent(context, SecurityQuestionsActivity::class.java)
            context?.startActivity(intent)
        }
        sesame_view.setOnClickListener {
            sesame_view.setSesameValues(
                1000
            )
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
            val reportDate = dashboardResponse.data!!.creditReport!!.reportDate
            val nextDate = dashboardResponse.data!!.creditReport!!.nextDate
            tv_report_date.text = reportDate
            tv_next_date.text = nextDate
        })

        viewModel?.responseSecurity?.observe(viewLifecycleOwner, Observer {securityResponse->
            if (securityResponse.code == "100") {
                val token = securityResponse.data!!.authToken
                val jsResponse = securityResponse.data!!.questions
                val intent = Intent(context, SecurityQuestionsActivity::class.java)
                intent.putExtra("responseObj", jsResponse)
                intent.putExtra("token", token)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        })
    }
}
