package com.soft.credit911.ui.casemanagement.Fragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.R
import com.soft.credit911.adaptor.CaseManagementDetailsAdapter
import com.soft.credit911.databinding.FragmentCaseManagementBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.datamodel.CashDemoModel
import kotlinx.android.synthetic.main.fragment_case_management.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class CaseManagementFragment : BaseFragment() {

    private val cashDemoModelList = ArrayList<CashDemoModel>()
    private var caseManagementDetailsAdapter: CaseManagementDetailsAdapter? = null
    override fun getLayoutID(): Int {
        return R.layout.fragment_case_management
    }

    override fun onViewCreated() {
        toolbarTitle.text = "Case Management"
        navigationIcon.visibility = View.GONE
        caseManagementDetailsAdapter =
            context?.let { CaseManagementDetailsAdapter(cashDemoModelList, it) }
        rv_case_management.adapter = caseManagementDetailsAdapter
        prepareCaseManagementData()
    }

    private fun prepareCaseManagementData() {
        var cashDemoModel = CashDemoModel(
            "Dispute with SBI bank",
            "Open",
            "open",
            "20 june 2021",
            "",
            "Agent:Haary",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",
            R.drawable.chris_hemsworth
        )
        cashDemoModelList.add(cashDemoModel)
        cashDemoModel = CashDemoModel(
            "Dispute with HDFC bank",
            "",
            "Closed",
            "19 Dec 2021",
            "",
            "Agent:Harry",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",
            R.drawable.chris_hemsworth
        )
        cashDemoModelList.add(cashDemoModel)
        cashDemoModel = CashDemoModel(
            "Dispute with HDFC bank",
            "",
            "Closed",
            "19 Dec 2021",
            "",
            "Agent:Harry",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",
            R.drawable.chris_hemsworth
        )
        cashDemoModelList.add(cashDemoModel)
        cashDemoModel = CashDemoModel(
            "Dispute with HDFC bank",
            "",
            "Closed",
            "19 Dec 2021",
            "",
            "Agent:Harry",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",
            R.drawable.chris_hemsworth
        )
        cashDemoModelList.add(cashDemoModel)
        cashDemoModel = CashDemoModel(
            "Dispute with HDFC bank",
            "",
            "Closed",
            "19 Dec 2021",
            "",
            "Agent:Harry",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",
            R.drawable.chris_hemsworth
        )
        cashDemoModelList.add(cashDemoModel)
        cashDemoModel = CashDemoModel(
            "Dispute with HDFC bank",
            "",
            "Closed",
            "19 Dec 2021",
            "",
            "Agent:Harry",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",
            R.drawable.chris_hemsworth
        )
        cashDemoModelList.add(cashDemoModel)
        caseManagementDetailsAdapter?.notifyDataSetChanged()
    }
}