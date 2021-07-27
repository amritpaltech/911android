package com.soft.credit911.ui.Splash

import android.content.Intent
import android.view.View
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.ui.dashboard.LandingActivity
import com.soft.credit911.ui.Login.LoginActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.AppPreference
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : SubBaseActivity() {

    override fun getLayoutID(): Int {
       return R.layout.activity_splash
    }

    override fun onViewCreated() {
        tv_Get_start_Now.setOnClickListener { v: View? ->
            val intent: Intent
            intent = if (AppPreference(this).isLogin) Intent(
                this,
                LandingActivity::class.java
            ) else Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}