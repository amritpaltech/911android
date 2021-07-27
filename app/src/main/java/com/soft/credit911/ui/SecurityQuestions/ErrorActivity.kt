package com.soft.credit911.ui.SecurityQuestions

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.soft.credit911.databinding.ActivityErrrorBinding
import com.soft.credit911.ui.SecurityQuestions.ErrorActivity
import com.soft.credit911.ui.dashboard.Dashboard.fragment.DashboardFragment
import com.soft.credit911.ui.dashboard.LandingActivity

class ErrorActivity : AppCompatActivity() {
    private var layoutBinding: ActivityErrrorBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityErrrorBinding.inflate(layoutInflater)
        val view = layoutBinding!!.root
        setContentView(view)
        layoutBinding!!.tvMessage.text = intent.getStringExtra("message")
        layoutBinding!!.tvGoToDashBoard.setOnClickListener { v: View? ->
            val intent = Intent(this, DashboardFragment::class.java)
            startActivity(intent)
        }
        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                layoutBinding!!.tvGoToDashBoard.text = "" + millisUntilFinished / 1000
            }

            override fun onFinish() {
                val intent = Intent(this@ErrorActivity, LandingActivity::class.java)
                intent.putExtra("isError", 1)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        }.start()
    }
}