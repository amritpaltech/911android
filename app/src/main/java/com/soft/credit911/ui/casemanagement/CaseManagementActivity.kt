package com.soft.credit911.ui.casemanagement

import android.graphics.Color
import android.view.View
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.loadImg
import com.soft.credit911.datamodel.data_cases
import com.soft.credit911.fcm.notificationObject
import kotlinx.android.synthetic.main.activity_case_management.*
import kotlinx.android.synthetic.main.toolbar.*

class CaseManagementActivity : SubBaseActivity() {

    var cases: data_cases.Cases?=null
        override fun getLayoutID(): Int {
            return R.layout.activity_case_management
        }

        override fun onViewCreated() {
            toolbarTitle.text = "Case Management"
            cases=intent.getSerializableExtra("caseData") as data_cases.Cases
            navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
            init()
        }
    
        fun init(){
            tv_bank_name.text=cases?.name
            tv_user_mes.text=cases?.description
            tv_status.text=cases?.status
            tv_mark.setColorFilter(Color.GRAY)
            if(cases?.status.equals("assigned")){
                tv_mark.setColorFilter(Color.YELLOW)
            }else if(cases?.status.equals("open")){
                tv_mark.setColorFilter(Color.GREEN)
            }else if(cases?.status.equals("closed")){
                tv_mark.setColorFilter(Color.BLUE)
            }

            tv_date.text=cases?.date
            if(cases?.matters?.size?:0>0) {
                tv_agent_name.text ="Agent: "+ cases?.matters?.get(0)?.agent_details?.first_name+ " "+cases?.matters?.get(0)?.agent_details?.last_name
                iv_user.loadImg( cases?.matters?.get(0)?.agent_details?.avatar)
            }
            if(intent.extras?.containsKey("pushData")==true){
                pushDataMain = intent?.getSerializableExtra("pushData") as notificationObject
                showPushDialog()
            }
        }

    }