package com.soft.credit911.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.fcm.notificationObject
import com.soft.credit911.ui.Changepassword.ChangePasswordActivity
import com.soft.credit911.ui.Chat.Activity.ChatActivity
import com.soft.credit911.ui.Chat.Fragment.ChatFragment
import com.soft.credit911.ui.MyProfile.Activity.MyProfileActivity
import com.soft.credit911.ui.SecurityQuestions.SecurityQuestionsActivity
import com.soft.credit911.ui.casemanagement.CaseManagementActivity
import com.soft.credit911.ui.casemanagement.Fragement.CaseManagementFragment
import com.soft.credit911.ui.dashboard.Dashboard.DashboardFragment
import com.soft.credit911.ui.dashboard.UserProfile.Fragment.UserProfileFragment
import com.soft.credit911.ui.documnet.DocumentActivity
import com.soft.credit911.ui.notifications.NotificationActivity
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : SubBaseActivity() {
    var isError = 0
    var pushData: notificationObject?=null
    var isPushHandled=true
    override fun getLayoutID(): Int {

        return R.layout.activity_landing
    }

    override fun onViewCreated() {

        if (intent?.hasExtra("push_data") == true &&
            intent?.getSerializableExtra("push_data") != null) {
            pushData = intent?.getSerializableExtra("push_data") as notificationObject
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
                R.id.Contact_message -> {
                    fragment = ChatFragment()
                    loadFragment(fragment)
                }
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
        if (pushData != null && !isPushHandled) {
            when (pushData?.notificationType) {
                "document" -> {

                    val intent = Intent(this, DocumentActivity::class.java)
                    intent.putExtra("pushData", pushData)
                    startActivity(intent)
                }
                "dashboard" -> {

                }
                "notifications" -> {
                    val intent = Intent(this, NotificationActivity::class.java)
                    intent.putExtra("pushData", pushData)
                    startActivity(intent)
                }
                "credit_history" -> {
                    val intent = Intent(this, ActivityCreditHistory::class.java)
                    intent.putExtra("pushData", pushData)
                    intent.putExtra("history", data.creditReportHistory)
                    startActivity(intent)
                }
                "credit_report" -> {
                    val intent = Intent(this, ActivityCreditReport::class.java)
                    intent.putExtra("pushData", pushData)
                    intent.putExtra("creditData", data.creditReport)
                    startActivity(intent)
                }
                "profile" -> {
                    val intent = Intent(this, MyProfileActivity::class.java)
                    intent.putExtra("pushData", pushData)
                    startActivity(intent)
                }
                "password" -> {
                    val intent = Intent(this, ChangePasswordActivity::class.java)
                    intent.putExtra("pushData", pushData)
                    startActivity(intent)
                }

                "chat" -> {
                    val intent = Intent(this, ChatActivity::class.java)
                    intent.putExtra("pushData", pushData)
                    startActivity(intent)
                }

                "case_management" -> {
                    val intent = Intent(this, CaseManagementActivity::class.java)
                    intent.putExtra("pushData", pushData)
                    startActivity(intent)

                }
                "security_questions" -> {
                    val intent = Intent(this, SecurityQuestionsActivity::class.java)
                    intent.putExtra("pushData", pushData)
                    startActivity(intent)
                }
            }
            pushData = null
            isPushHandled=true
        }
    }

}