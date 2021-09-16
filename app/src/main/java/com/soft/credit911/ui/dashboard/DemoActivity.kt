package com.marriageapp

import com.ing.quiz.ui.base_classes.BaseActivity
import com.soft.credit911.R
import com.utility.adapter.*
import kotlinx.android.synthetic.main.demo_activity.*
import kotlinx.android.synthetic.main.layout_auto_loans.*
import kotlinx.android.synthetic.main.layout_auto_loans.listCarLoans
import kotlinx.android.synthetic.main.layout_credit_card.*
import kotlinx.android.synthetic.main.layout_credit_card.listCards
import kotlinx.android.synthetic.main.layout_hard_inquires.*

class DemoActivity : BaseActivity() {
    override fun getLayoutID(): Int {
        return R.layout.demo_activity
    }

    override fun onViewCreated() {
        setPaymentAdapter()
        setAmountAdapter()
        setCardList()
        setAutoList()
        setHardList()
    }

    fun setPaymentAdapter()
    {
        listPayment.adapter= AdapPaymentHistory()


    }

    fun setAmountAdapter()
    {
        amountList.adapter= AdapAmount()

    }

    fun setCardList()
    {
        val adap=AdapCardsList()
        adap.addListItems()
        listCards.adapter=adap
    }
    fun setAutoList()
    {
        listCarLoans.adapter= AdapAutoLoans()
    }

    fun setHardList()
    {
        listHardInquires.adapter= AdapHardInquires()
    }


}