package com.soft.credit911.ui.Splash

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ing.quiz.shared_prefrences.Prefs
import com.ing.quiz.shared_prefrences.SharedPreferencesName
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
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        tv_Get_start_Now.setOnClickListener { v: View? ->
            val intent: Intent
            intent = if (AppPreference(this).isLogin) Intent(
                this,
                LandingActivity::class.java
            ) else Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Prefs.with(this).save(SharedPreferencesName.DEVICETOKEN,token.toString())
        })
    }
}