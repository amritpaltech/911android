package com.soft.credit911

import com.ing.quiz.ui.base_classes.BaseActivity
import com.ing.quiz.ui.base_classes.BaseFragment
import com.soft.credit911.adaptor.*
import com.soft.credit911.model.CreditReportData
import kotlinx.android.synthetic.main.demo_activity.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.layout_auto_loans.listCarLoans
import kotlinx.android.synthetic.main.layout_credit_card.listCards
import kotlinx.android.synthetic.main.layout_hard_inquires.*

class FragmentCredReport : BaseFragment() {

    var creditReport: CreditReportData?=null
    override fun getLayoutID(): Int {
        return R.layout.demo_activity
    }

    override fun onViewCreated() {
        creditReport=arguments?.getSerializable("creditData") as CreditReportData
        setPaymentAdapter()
        setAmountAdapter()
        setCardList()
        setAutoList()
        setHardList()
        setData()
        backBtn.setOnClickListener {
            super.onBackPress()
        }
    }

    fun performaceStr(sscore:Int):String{
        if(sscore<400){
        return  "POOR"
        }
        else if(sscore<700){
            return "NEEDS\nWORK"
        }else{
            return "GOOD"
        }
    }

    fun setData(){
        transUnionCount.text=creditReport?.reportdetails?.transUnion?.score.toString()
        quifaxCount.text=creditReport?.reportdetails?.equifax?.score.toString()
        experianCount.text=creditReport?.reportdetails?.experian?.score.toString()


        transUnionCountDate.text=creditReport?.reportdetails?.transUnion?.scoreDate
        euifaxCountDate.text=creditReport?.reportdetails?.equifax?.scoreDate
        exprinCountDate.text=creditReport?.reportdetails?.experian?.scoreDate

        performace1.text=performaceStr(creditReport?.reportdetails?.transUnion?.score?:0)
        performace2.text=performaceStr(creditReport?.reportdetails?.equifax?.score?:0)
        performace3.text=performaceStr(creditReport?.reportdetails?.experian?.score?:0)

        val score1=creditReport?.reportdetails?.transUnion?.score?:0
        val score2=creditReport?.reportdetails?.equifax?.score?:0
        val score3=creditReport?.reportdetails?.experian?.score?:0
        var overallScore=0
        var divInt=0
        var list:ArrayList<Int>?= ArrayList()
        list?.add(score1)
        list?.add(score2)
        list?.add(score3)
        if (list != null) {
            for(item in list){
                if(item>0){
                    divInt=divInt+1
                    overallScore=overallScore+item
                }
            }
        }
        overallScore=overallScore/divInt
        overAllScoretxt.text=""+overallScore
        overAllPerformance.text=""+performaceStr(overallScore)

        transPr.setProgress(((score1*100)/900)/100.0f)
        equiPr.setProgress(((score2*100)/900)/100.0f)
        exPr.setProgress(((score3*100)/900)/100.0f)
        reportDat1.text="Report Date:\n"+arguments?.getString("date1")
        reportDat2.text="Next Update:\n"+arguments?.getString("date2")

        if(overallScore<=300){
            progressOverall.setProgress(0.01f)
        }else if (overallScore<=500){
            progressOverall.setProgress((overallScore-300)/1000.0f)
        }
        else if (overallScore<=575){
            progressOverall.setProgress(.2f+(overallScore-500f)*0.0027f)
        }
        else if (overallScore<=650){
            progressOverall.setProgress(.4f+(overallScore-575f)*0.0033f)
        }
        else if (overallScore<=750){
            progressOverall.setProgress(.65f+(overallScore-650f)*0.0015f)
        }
        else if (overallScore<=900){
            progressOverall.setProgress(.8f+(overallScore-750f)*0.0015f)
        }
        ChartUtilDetailed().setChartData(chartHistory,creditReport?.scoresData)


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
//        val adap= AdapCardsList()
//        adap.addListItems()
//        listCards.adapter=adap
    }
    fun setAutoList()
    {
//        listCarLoans.adapter= AdapAutoLoans()
    }

    fun setHardList()
    {
//        listHardInquires.adapter= AdapHardInquires()
    }


}