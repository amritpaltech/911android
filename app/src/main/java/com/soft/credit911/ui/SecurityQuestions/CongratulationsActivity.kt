package com.soft.credit911.ui.SecurityQuestions

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.soft.credit911.databinding.ActivityCongratulationsBinding
import com.soft.credit911.ui.dashboard.LandingActivity

class CongratulationsActivity : AppCompatActivity() {
    private var layoutBinding: ActivityCongratulationsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityCongratulationsBinding.inflate(
            layoutInflater
        )
        val view = layoutBinding!!.root
        setContentView(view)
        layoutBinding!!.tvMesssage.text = intent.getStringExtra("message")
        layoutBinding!!.tvGoToDashBoard.setOnClickListener { v: View? ->
            val intent = Intent(this, LandingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }
}