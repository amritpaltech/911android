package com.soft.credit911.ui.SecurityQuestions

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.soft.credit911.adaptor.SecurityQuestionAdapter
import com.soft.credit911.databinding.ActivitySecurityQuestionsBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.datamodel.AnswersModel
import com.soft.credit911.datamodel.AuthQuestionResponse
import com.soft.credit911.datamodel.SecurityQuestionModel
import com.soft.credit911.ui.SecurityQuestions.mvp.AuthQuestionPrSenter
import com.soft.credit911.ui.SecurityQuestions.mvp.AuthQuestionView
import com.soft.credit911.ui.SecurityQuestions.mvp.SecurityPresenter
import java.util.*

class SecurityQuestionsActivity : AppCompatActivity(), AuthQuestionView {
    private var layoutBinding: ActivitySecurityQuestionsBinding? = null
    private var toolbarBinding: ToolbarBinding? = null
    var securityPresenter: SecurityPresenter? = null
    private var securityQuestionAdapter: SecurityQuestionAdapter? = null
    private var authQuestionPrSenter: AuthQuestionPrSenter? = null
    private val securityQuestionModelList = ArrayList<SecurityQuestionModel>()
    var answersModels = ArrayList<AnswersModel>()
    var answer1: String? = null
    var answer2: String? = null
    var answer3: String? = null
    var question1: String? = null
    var question2: String? = null
    var question3: String? = null
    var token: String? = null
    var securityQuestionModels: List<SecurityQuestionModel>? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivitySecurityQuestionsBinding.inflate(
            layoutInflater
        )
        val view = layoutBinding!!.root
        setContentView(view)
        toolbarBinding = layoutBinding!!.toolbarLayout
        toolbarBinding!!.toolbarTitle.text = "Security Questions"
        toolbarBinding!!.navigationIcon.visibility = View.GONE
        intiView()
    }

    private fun intiView() {
        securityQuestionAdapter = SecurityQuestionAdapter(this)
        layoutBinding!!.rvQuestion.adapter = securityQuestionAdapter
        if (intent.hasExtra("responseObj")) {
            val jsObj = intent.getStringExtra("responseObj")
            Log.e("TAX1", jsObj!!)
            setData(jsObj)
        }
        if (intent.hasExtra("token")) {
            token = intent.getStringExtra("token")
        }
        layoutBinding!!.tvSubmit.setOnClickListener { v: View? ->
            answer1 = securityQuestionAdapter!!.ans1
            answer2 = securityQuestionAdapter!!.ans2
            answer3 = securityQuestionAdapter!!.ans3
            question1 = securityQuestionModels!![0].id
            question2 = securityQuestionModels!![1].id
            question3 = securityQuestionModels!![2].id
            authQuestionPrSenter = AuthQuestionPrSenter(this, this)
            authQuestionPrSenter!!.authQuestion(
                question1,
                answer1,
                question2,
                answer2,
                question3,
                answer3,
                token
            )
        }
    }

    private fun setData(jsObj: String?) {
        try {
            securityQuestionModels =
                Gson().fromJson(jsObj, object : TypeToken<List<SecurityQuestionModel?>?>() {}.type)
            securityQuestionModels?.let { securityQuestionAdapter?.addList(it) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun AuthQuestionResponse(authQuestionResponse: AuthQuestionResponse) {
        val intent: Intent
        if (authQuestionResponse.status == "success") {
            intent = Intent(this, CongratulationsActivity::class.java)
            intent.putExtra("message", authQuestionResponse.message)
        } else {
            intent = Intent(this, ErrorActivity::class.java)
            intent.putExtra("message", authQuestionResponse.message)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}