package com.soft.credit911.ui.SecurityQuestions.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.credit911.R;
import com.soft.credit911.ui.SecurityQuestions.Model.SecurityQuestionModel;
import com.soft.credit911.databinding.SecurityQuestionItemListBinding;

import java.util.ArrayList;
import java.util.List;

public class SecurityQuestionAdapter extends RecyclerView.Adapter<SecurityQuestionAdapter.SecurityQuestionViewHolder> {

    private List<SecurityQuestionModel> securityQuestionModels = new ArrayList<>();
    public Context context;

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    String ans1,ans2,ans3;

    public SecurityQuestionAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<SecurityQuestionModel> securityQuestionModelList) {
        this.securityQuestionModels = securityQuestionModelList;
        notifyDataSetChanged();
    }


    @Override
    public SecurityQuestionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SecurityQuestionItemListBinding securityQuestionItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.security_question_item_list, viewGroup, false);
        return new SecurityQuestionViewHolder(securityQuestionItemListBinding);

    }

    private RadioButton lastCheckedRB = null;

    @Override
    public void onBindViewHolder(SecurityQuestionViewHolder holder, int position) {

        SecurityQuestionModel securityQuestionList = securityQuestionModels.get(position);
        holder.questionItemListBinding.tvQuestion.setText(securityQuestionList.getText());

        if (!securityQuestionList.getAnswers().get(0).getText().isEmpty()) {
            holder.questionItemListBinding.radiaId1.setText(securityQuestionList.getAnswers().get(0).getText());
         //   holder.questionItemListBinding.radiaId1.setId(Integer.parseInt(securityQuestionList.getAnswers().get(0).getId()));
        }
        holder.questionItemListBinding.radiaId1.setOnClickListener(v -> {
            switch (position){
                case 0: ans1 = securityQuestionModels.get(position).getAnswers().get(0).getId();
                break;
                case 1: ans2 = securityQuestionModels.get(position).getAnswers().get(0).getId();
                break;
                case 2: ans3 = securityQuestionModels.get(position).getAnswers().get(0).getId();
                break;
            }
        });
        if (!securityQuestionList.getAnswers().get(1).getText().isEmpty()) {
            holder.questionItemListBinding.radiaId2.setText(securityQuestionList.getAnswers().get(1).getText());
        //    holder.questionItemListBinding.radiaId2.setId(Integer.parseInt(securityQuestionList.getAnswers().get(1).getId()));
        }
        holder.questionItemListBinding.radiaId2.setOnClickListener(v -> {
            switch (position){
                case 0: ans1 = securityQuestionModels.get(position).getAnswers().get(1).getId();
                    break;
                case 1: ans2 = securityQuestionModels.get(position).getAnswers().get(1).getId();
                    break;
                case 2: ans3 = securityQuestionModels.get(position).getAnswers().get(1).getId();
                    break;
            }
        });
        if (!securityQuestionList.getAnswers().get(2).getText().isEmpty()) {
            holder.questionItemListBinding.radiaId3.setText(securityQuestionList.getAnswers().get(2).getText());
          //  holder.questionItemListBinding.radiaId3.setId(Integer.parseInt(securityQuestionList.getAnswers().get(2).getId()));

        }
        holder.questionItemListBinding.radiaId3.setOnClickListener(v -> {
            switch (position){
                case 0: ans1 = securityQuestionModels.get(position).getAnswers().get(2).getId();
                    break;
                case 1: ans2 = securityQuestionModels.get(position).getAnswers().get(2).getId();
                    break;
                case 2: ans3 = securityQuestionModels.get(position).getAnswers().get(2).getId();
                    break;
            }
        });
        if (!securityQuestionList.getAnswers().get(3).getText().isEmpty()) {
            holder.questionItemListBinding.radiaId4.setText(securityQuestionList.getAnswers().get(3).getText());
           // holder.questionItemListBinding.radiaId4.setId(Integer.parseInt(securityQuestionList.getAnswers().get(3).getId()));

        }
        holder.questionItemListBinding.radiaId4.setOnClickListener(v -> {
            switch (position){
                case 0: ans1 = securityQuestionModels.get(position).getAnswers().get(3).getId();
                    break;
                case 1: ans2 = securityQuestionModels.get(position).getAnswers().get(3).getId();
                    break;
                case 2: ans3 = securityQuestionModels.get(position).getAnswers().get(3).getId();
                    break;
            }
        });
        if (!securityQuestionList.getAnswers().get(4).getText().isEmpty()) {
            holder.questionItemListBinding.radiaId5.setText(securityQuestionList.getAnswers().get(4).getText());
            //holder.questionItemListBinding.radiaId5.setId(Integer.parseInt(securityQuestionList.getAnswers().get(4).getId()));

        }
        holder.questionItemListBinding.radiaId5.setOnClickListener(v -> {
            switch (position){
                case 0: ans1 = securityQuestionModels.get(position).getAnswers().get(4).getId();
                    break;
                case 1: ans2 = securityQuestionModels.get(position).getAnswers().get(4).getId();
                    break;
                case 2: ans3 = securityQuestionModels.get(position).getAnswers().get(4).getId();
                    break;
            }
        });
        holder.questionItemListBinding.radiaId1.setChecked(true);
        switch (position){
            case 0: ans1 = securityQuestionModels.get(position).getAnswers().get(0).getId();
                break;
            case 1: ans2 = securityQuestionModels.get(position).getAnswers().get(0).getId();
                break;
            case 2: ans3 = securityQuestionModels.get(position).getAnswers().get(0).getId();
                break;
        }

    }


    @Override
    public int getItemCount() {
        return securityQuestionModels.size();
    }


    public class SecurityQuestionViewHolder extends RecyclerView.ViewHolder {
        private SecurityQuestionItemListBinding questionItemListBinding;

        public SecurityQuestionViewHolder(SecurityQuestionItemListBinding securityQuestionItemListBinding) {
            super(securityQuestionItemListBinding.getRoot());
            this.questionItemListBinding = securityQuestionItemListBinding;
        }
    }
}
