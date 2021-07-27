package com.soft.credit911.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soft.credit911.R
import com.soft.credit911.databinding.SecurityQuestionItemListBinding
import com.soft.credit911.adaptor.SecurityQuestionAdapter.SecurityQuestionViewHolder
import com.soft.credit911.datamodel.SecurityQuestionModel
import java.util.*

class SecurityQuestionAdapter(var context: Context) :
    RecyclerView.Adapter<SecurityQuestionViewHolder>() {
    private var securityQuestionModels: List<SecurityQuestionModel> = ArrayList()
    var ans1: String? = null
    var ans2: String? = null
    var ans3: String? = null
    fun addList(securityQuestionModelList: List<SecurityQuestionModel>) {
        securityQuestionModels = securityQuestionModelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SecurityQuestionViewHolder {
        val securityQuestionItemListBinding: SecurityQuestionItemListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.security_question_item_list, viewGroup, false
            )
        return SecurityQuestionViewHolder(securityQuestionItemListBinding)
    }

    private val lastCheckedRB: RadioButton? = null
    override fun onBindViewHolder(holder: SecurityQuestionViewHolder, position: Int) {
        val securityQuestionList = securityQuestionModels[position]
        holder.questionItemListBinding.tvQuestion.text = securityQuestionList.text
        if (securityQuestionList.answers?.get(0)?.text?.isEmpty()==false) {
            holder.questionItemListBinding.radiaId1.text = securityQuestionList.answers!![0].text
            //   holder.questionItemListBinding.radiaId1.setId(Integer.parseInt(securityQuestionList.getAnswers().get(0).getId()));
        }
        holder.questionItemListBinding.radiaId1.setOnClickListener { v: View? ->
            when (position) {
                0 -> ans1 = securityQuestionModels[position].answers?.get(0)?.id
                1 -> ans2 = securityQuestionModels[position].answers?.get(0)?.id
                2 -> ans3 = securityQuestionModels[position].answers?.get(0)?.id
            }
        }
        if (securityQuestionList.answers?.get(1)?.text?.isEmpty()==false) {
            holder.questionItemListBinding.radiaId2.text = securityQuestionList.answers!![1].text
            //    holder.questionItemListBinding.radiaId2.setId(Integer.parseInt(securityQuestionList.getAnswers().get(1).getId()));
        }
        holder.questionItemListBinding.radiaId2.setOnClickListener { v: View? ->
            when (position) {
                0 -> ans1 = securityQuestionModels[position].answers?.get(1)?.id
                1 -> ans2 = securityQuestionModels[position].answers?.get(1)?.id
                2 -> ans3 = securityQuestionModels[position].answers?.get(1)?.id
            }
        }
        if (securityQuestionList.answers?.get(2)?.text?.isEmpty()==false) {
            holder.questionItemListBinding.radiaId3.text = securityQuestionList.answers!![2].text
            //  holder.questionItemListBinding.radiaId3.setId(Integer.parseInt(securityQuestionList.getAnswers().get(2).getId()));
        }
        holder.questionItemListBinding.radiaId3.setOnClickListener { v: View? ->
            when (position) {
                0 -> ans1 = securityQuestionModels[position].answers?.get(2)?.id
                1 -> ans2 = securityQuestionModels[position].answers?.get(2)?.id
                2 -> ans3 = securityQuestionModels[position].answers?.get(2)?.id
            }
        }
        if (securityQuestionList.answers?.get(3)?.text?.isEmpty()==false) {
            holder.questionItemListBinding.radiaId4.text = securityQuestionList.answers!![3].text
            // holder.questionItemListBinding.radiaId4.setId(Integer.parseInt(securityQuestionList.getAnswers().get(3).getId()));
        }
        holder.questionItemListBinding.radiaId4.setOnClickListener { v: View? ->
            when (position) {
                0 -> ans1 = securityQuestionModels[position].answers?.get(3)?.id
                1 -> ans2 = securityQuestionModels[position].answers?.get(3)?.id
                2 -> ans3 = securityQuestionModels[position].answers?.get(3)?.id
            }
        }
        if (securityQuestionList.answers?.get(4)?.text?.isEmpty()==false) {
            holder.questionItemListBinding.radiaId5.text = securityQuestionList.answers?.get(4)!!.text
            //holder.questionItemListBinding.radiaId5.setId(Integer.parseInt(securityQuestionList.getAnswers().get(4).getId()));
        }
        holder.questionItemListBinding.radiaId5.setOnClickListener { v: View? ->
            when (position) {
                0 -> ans1 = securityQuestionModels[position].answers?.get(4)?.id
                1 -> ans2 = securityQuestionModels[position].answers?.get(4)?.id
                2 -> ans3 = securityQuestionModels[position].answers?.get(4)?.id
            }
        }
        holder.questionItemListBinding.radiaId1.isChecked = true
        when (position) {
            0 -> ans1 = securityQuestionModels[position].answers?.get(0)?.id
            1 -> ans2 = securityQuestionModels[position].answers?.get(0)?.id
            2 -> ans3 = securityQuestionModels[position].answers?.get(0)?.id
        }
    }

    override fun getItemCount(): Int {
        return securityQuestionModels.size
    }

    inner class SecurityQuestionViewHolder(val questionItemListBinding: SecurityQuestionItemListBinding) :
        RecyclerView.ViewHolder(
            questionItemListBinding.root
        )
}