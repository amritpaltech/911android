package com.soft.credit911.ui.casemanagement.Fragement

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ing.quiz.ui.base_classes.BaseActivity
import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.R
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.adaptor.CaseAdaptor
import kotlinx.android.synthetic.main.fragment_case_management.*
import kotlinx.android.synthetic.main.toolbar.*

class CaseManagementFragment : BaseFragment() {
    val viewModel=CaseViewModel()
    override fun getLayoutID(): Int {
        return R.layout.fragment_case_management
    }

    override fun onViewCreated() {
        toolbarTitle.text = "Case Management"
        navigationIcon.visibility = View.GONE
        viewModel.getCases()
        attachObserver()
    }

    fun attachObserver(){
        viewModel?.apiError.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            CommonUtils.showdialog(it.toString(), activity as BaseActivity, false)
        })

        viewModel?.isLoading?.observe(viewLifecycleOwner,androidx.lifecycle.Observer {

            if(it){
                showProgress()
            }else{
                hideProgress()
            }
        })
        viewModel.dataCases.observe(viewLifecycleOwner, androidx.lifecycle.Observer {case->
        if(case.status.equals("success")){
            if(case.cases?.size?:0>0){
                cases.visibility=View.GONE
                val adap= case.cases?.let {
                    CaseAdaptor(it){caseData->
//                        val bundle= Bundle()
//                        val frg=FragMentCaseManagementDetail()
//                        bundle.putSerializable("caseData",caseData)
//                        frg.arguments=bundle
////                        val intent= Intent(activity, FragMentCaseManagementDetail::class.java)
////                        intent.putExtra("caseData",caseData)
//                        addSubContentFragment(frg)
                    }
                }
                rv_case_management.adapter = adap
            }else{
                cases.visibility=View.VISIBLE
            }

        }

        })

    }

}