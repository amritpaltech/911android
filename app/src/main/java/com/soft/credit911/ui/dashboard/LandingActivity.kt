package com.soft.credit911.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.ui.Chat.Fragment.ChatFragment
import com.soft.credit911.ui.casemanagement.Fragement.CaseManagementFragment
import com.soft.credit911.ui.dashboard.Dashboard.DashboardFragment
import com.soft.credit911.ui.dashboard.UserProfile.Fragment.UserProfileFragment
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : SubBaseActivity() {
    var isError = 0

    override fun getLayoutID(): Int {

        return R.layout.activity_landing
    }

    override fun onViewCreated() {
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

}