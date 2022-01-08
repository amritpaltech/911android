package com.soft.credit911.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.fcm.notificationObject
import com.soft.credit911.ui.Changepassword.FragmentChangePassword
import com.soft.credit911.ui.Chat.Activity.ChatActivity
import com.soft.credit911.ui.MyProfile.Activity.MyProfileActivity
import com.soft.credit911.ui.casemanagement.FragMentCaseManagementDetail
import com.soft.credit911.ui.casemanagement.Fragement.CaseManagementFragment
import com.soft.credit911.ui.dashboard.Dashboard.DashboardFragment
import com.soft.credit911.ui.dashboard.UserProfile.Fragment.UserProfileFragment
import com.soft.credit911.ui.documnet.FragmentDocument
import com.soft.credit911.ui.notifications.FragmentNotification
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : SubBaseActivity() {
    var isError = 0
    var pushDataPre: notificationObject?=null
    var isPushHandled=true
    override fun getLayoutID(): Int {

        return R.layout.activity_landing
    }

    override fun onViewCreated() {

        if (intent?.hasExtra("push_data") == true &&
            intent?.getSerializableExtra("push_data") != null) {
            pushDataPre = intent?.getSerializableExtra("push_data") as notificationObject
            isPushHandled=false
        }

        if (intent.hasExtra("isError")) {
            isError = intent.getIntExtra("isError", 0)
        }
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.Dashboard -> {
                    fragment = DashboardFragment()
                    val bundle = Bundle()
                    bundle.putInt("isError", isError)
                    fragment.setArguments(bundle)
                    loadFragment(fragment)
                }
//                R.id.Contact_message -> {
//                    fragment = ChatFragment()
//                    loadFragment(fragment)
//                }
                R.id.Case_management -> {
                    fragment = CaseManagementFragment()
                    loadFragment(fragment)
                }
                R.id.More -> {
                    fragment = UserProfileFragment()
                    loadFragment(fragment)
                }
            }
            true
        }
       bottomNavigationView.selectedItemId = R.id.Dashboard
    }

    fun loadFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flFragment, fragment)
            fragmentTransaction.commit()
        }
    }


    fun selectCaseScreen(){
        bottomNavigationView.selectedItemId=R.id.Case_management
    }

    fun handlePush(data: DashboardResponse.Data) {
        if (pushDataPre != null && !isPushHandled) {
            when (pushDataPre?.notificationType) {
                "document" -> {
                    val frg=FragmentDocument()
                    val bundle=Bundle()
                    bundle.putSerializable("pushData", pushDataPre)
                    frg.arguments=bundle
                    addSubContentFragment(frg)
//                    val intent = Intent(this, FragmentDocument::class.java)
//                    intent.putExtra("pushData", pushDataPre)
//                    startActivity(intent)
                }
                "dashboard" -> {
                    pushDataMain=pushDataPre
                    showPushDialog()
                }
                "notifications" -> {
                    val frg=FragmentNotification()
                    val bundle=Bundle()
                    bundle.putSerializable("pushData", pushDataPre)
                    frg.arguments=bundle
                    addSubContentFragment(frg)
//                    val intent = Intent(this, FragmentNotification::class.java)
//                    intent.putExtra("pushData", pushDataPre)
//                    startActivity(intent)
                }
                "credit_history" -> {
//                    val intent = Intent(this, FragmentCreditHistory::class.java)
//                    intent.putExtra("pushData", pushDataPre)
//                    intent.putExtra("history", data.creditReportHistory)
//                    startActivity(intent)
                    val frg=FragmentCreditHistory()
                    val bundle=Bundle()
                    bundle.putSerializable("pushData", pushDataPre)
                    bundle.putSerializable("history",  data.creditReportHistory)
                    frg.arguments=bundle
                    addSubContentFragment(frg)

                }
                "credit_report" -> {
                    val intent = Intent(this, ActivityCreditReport::class.java)
                    intent.putExtra("pushData", pushDataPre)
                    intent.putExtra("creditData", data.creditReport)
                    startActivity(intent)
                }
                "profile" -> {
                    val intent = Intent(this, MyProfileActivity::class.java)
                    intent.putExtra("pushData", pushDataPre)
                    startActivity(intent)
                }
                "password" -> {
                    val frg=FragmentChangePassword()
                    val bundle=Bundle()
                    bundle.putSerializable("pushData", pushDataPre)
                    frg.arguments=bundle
                    addSubContentFragment(frg)
//                    val intent = Intent(this, FragmentChangePassword::class.java)
//                    intent.putExtra("pushData", pushDataPre)
//                    startActivity(intent)
                }

                "chat" -> {
                    val intent = Intent(this, ChatActivity::class.java)
                    intent.putExtra("pushData", pushDataPre)
                    startActivity(intent)
                }

                "case_management" -> {
                    val frg=FragMentCaseManagementDetail()
                    val bundle=Bundle()
                    bundle.putSerializable("pushData", pushDataPre)
                    frg.arguments=bundle
                    addSubContentFragment(frg)
//                    val intent = Intent(this, FragMentCaseManagementDetail::class.java)
//                    intent.putExtra("pushData", pushDataPre)
//                    startActivity(intent)

                }
                "security_questions" -> {
//                    val intent = Intent(this, SecurityQuestionsActivity::class.java)
//                    intent.putExtra("pushData", pushDataPre)
//                    startActivity(intent)
                }
            }
            pushDataPre = null
            isPushHandled=true
        }

    }

}