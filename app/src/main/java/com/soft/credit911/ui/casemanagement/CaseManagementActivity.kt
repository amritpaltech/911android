package com.soft.credit911.ui.casemanagement

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import kotlinx.android.synthetic.main.activity_case_management.*
import kotlinx.android.synthetic.main.toolbar.*

class CaseManagementActivity : SubBaseActivity() {

        override fun getLayoutID(): Int {
            return R.layout.activity_case_management
        }

        override fun onViewCreated() {
            toolbarTitle.text = "Case Management"
            navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
            Glide.with(this)
                .load(R.drawable.chris_hemsworth)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(500)))
                .placeholder(R.drawable.chris_hemsworth)
                .into(iv_user)

        }
    }