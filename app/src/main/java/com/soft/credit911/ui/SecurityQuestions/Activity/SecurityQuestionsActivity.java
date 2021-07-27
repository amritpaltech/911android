package com.soft.credit911.ui.SecurityQuestions.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soft.credit911.ui.SecurityQuestions.Adapter.SecurityQuestionAdapter;
import com.soft.credit911.ui.SecurityQuestions.Model.AnswersModel;
import com.soft.credit911.ui.SecurityQuestions.Model.AuthQuestionResponse;
import com.soft.credit911.ui.SecurityQuestions.Model.SecurityQuestionModel;
import com.soft.credit911.ui.SecurityQuestions.mvp.AuthQuestionPrSenter;
import com.soft.credit911.ui.SecurityQuestions.mvp.AuthQuestionView;
import com.soft.credit911.ui.SecurityQuestions.mvp.SecurityPresenter;
import com.soft.credit911.databinding.ActivitySecurityQuestionsBinding;
import com.soft.credit911.databinding.ToolbarBinding;

import java.util.ArrayList;
import java.util.List;

public class SecurityQuestionsActivity extends AppCompatActivity implements AuthQuestionView {
    private ActivitySecurityQuestionsBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    SecurityPresenter securityPresenter;
    private SecurityQuestionAdapter securityQuestionAdapter;
    private AuthQuestionPrSenter authQuestionPrSenter;
    private ArrayList<SecurityQuestionModel> securityQuestionModelList = new ArrayList<>();
    ArrayList<AnswersModel> answersModels = new ArrayList<>();
    String answer1, answer2, answer3;
    String question1, question2, question3;
    String token;
    List<SecurityQuestionModel> securityQuestionModels;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivitySecurityQuestionsBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);
        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Security Questions");
        toolbarBinding.navigationIcon.setVisibility(View.GONE);

        intiView();


    }

    private void intiView() {
        securityQuestionAdapter = new SecurityQuestionAdapter(this);
        layoutBinding.rvQuestion.setAdapter(securityQuestionAdapter);

        if (getIntent().hasExtra("responseObj")) {
            String jsObj = getIntent().getStringExtra("responseObj");
            Log.e("TAX1", jsObj);
            setData(jsObj);
        }

        if(getIntent().hasExtra("token")){
            token = getIntent().getStringExtra("token");
        }

        layoutBinding.tvSubmit.setOnClickListener(v -> {
            answer1 = securityQuestionAdapter.getAns1();
            answer2 = securityQuestionAdapter.getAns2();
            answer3 = securityQuestionAdapter.getAns3();
            question1 = securityQuestionModels.get(0).getId();
            question2 = securityQuestionModels.get(1).getId();
            question3 = securityQuestionModels.get(2).getId();
            authQuestionPrSenter = new AuthQuestionPrSenter(this,this);
            authQuestionPrSenter.authQuestion(question1,answer1,question2,answer2,question3,answer3,token);

        });
    }

    private void setData(String jsObj) {
        try {
            securityQuestionModels = new Gson().fromJson(jsObj, new TypeToken<List<SecurityQuestionModel>>() {
            }.getType());

            securityQuestionAdapter.addList(securityQuestionModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AuthQuestionResponse(AuthQuestionResponse authQuestionResponse) {
        Intent intent;
        if (authQuestionResponse.getStatus().equals("success")) {
            intent = new Intent(this, CongratulationsActivity.class);
            intent.putExtra("message",authQuestionResponse.getMessage());
        } else {
            intent = new Intent(this, ErrorActivity.class);
            intent.putExtra("message",authQuestionResponse.getMessage());
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}