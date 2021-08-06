package com.soft.credit911.ui.casemanagement.Fragement

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.R
import com.soft.credit911.adaptor.CaseAdaptor
import com.soft.credit911.ui.casemanagement.CaseManagementActivity
import kotlinx.android.synthetic.main.fragment_case_management.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

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
        viewModel?.isLoading?.observe(viewLifecycleOwner,androidx.lifecycle.Observer {

            if(it){
                showProgress()
            }else{
                hideProgress()
            }
        })
        viewModel.dataCases.observe(viewLifecycleOwner, androidx.lifecycle.Observer {case->
        if(case.status.equals("success")){
            val adap= case.cases?.let {
                CaseAdaptor(it){caseData->
                    val intent= Intent(activity, CaseManagementActivity::class.java)
                    intent.putExtra("caseData",caseData)
                    startActivity(intent)
                }
            }
            rv_case_management.adapter = adap
        }

        })

    }

}