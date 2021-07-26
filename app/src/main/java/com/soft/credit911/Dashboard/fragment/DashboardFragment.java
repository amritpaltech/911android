package com.soft.credit911.Dashboard.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.soft.credit911.Dashboard.Model.DashboardResponse;
import com.soft.credit911.Dashboard.mvp.DashboardPresenter;
import com.soft.credit911.Dashboard.mvp.DashboardView;
import com.soft.credit911.Landing.LandingActivity;
import com.soft.credit911.SecurityQuestions.Activity.SecurityQuestionsActivity;
import com.soft.credit911.SecurityQuestions.Model.SecurityResponse;
import com.soft.credit911.Utils.CommonUtils;
import com.soft.credit911.databinding.FragmentDashboardBinding;
import com.soft.credit911.databinding.ToolbarBinding;


public class DashboardFragment extends Fragment implements DashboardView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentDashboardBinding fragmentBinding;
    private ToolbarBinding toolbarBinding;
    DashboardPresenter dashboardPresenter;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentBinding = FragmentDashboardBinding.inflate(inflater, container, false);
        toolbarBinding = fragmentBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Dashboard");
        toolbarBinding.navigationIcon.setVisibility(View.GONE);
        dashboardPresenter = new DashboardPresenter(getContext(), this);
        fragmentBinding.tvCreditRepairStatus.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SecurityQuestionsActivity.class);
            getContext().startActivity(intent);
        });
        fragmentBinding.sesameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentBinding.sesameView.setSesameValues(1000);
            }
        });

        fragmentBinding.scroolableContent.setVisibility(View.VISIBLE);
        fragmentBinding.txtErrorText.setVisibility(View.GONE);
        if (getArguments() != null) {
            if(getArguments().containsKey("isError") && getArguments().getInt("isError")==1){
                fragmentBinding.scroolableContent.setVisibility(View.GONE);
                fragmentBinding.txtErrorText.setVisibility(View.VISIBLE);
            }else{
                dashboardPresenter.creditReport();
            }
        }else{
            dashboardPresenter.creditReport();
        }

        return fragmentBinding.getRoot();
    }

    @Override
    public void DashboardResponse(DashboardResponse dashboardResponse) {
       CommonUtils.showdialog(dashboardResponse.getMessage(), getContext(), false);
        String reportDate = dashboardResponse.getData().getCreditReport().getReportDate();
        String nextDate = dashboardResponse.getData().getCreditReport().getNextDate();
        fragmentBinding.tvReportDate.setText(reportDate);
        fragmentBinding.tvNextDate.setText(nextDate);

    }

    @Override
    public void SecurityResponse(SecurityResponse securityResponse) {
        if(securityResponse.getCode().equals("100")){
            String token = securityResponse.getData().getAuthToken();
            String jsResponse = securityResponse.getData().getQuestions();
            Intent intent = new Intent(getContext(), SecurityQuestionsActivity.class);
            intent.putExtra("responseObj",jsResponse);
            intent.putExtra("token",token);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

  /*  private final int[] mColors = new int[]{
            0xFFFF80AB,
            0xFFFF4081,
            0xFFFF5177,
            0xFFFF7997
    };


   public void startColorChangeAnim() {

        ObjectAnimator animator = ObjectAnimator.ofInt(fragmentBinding.layout, "backgroundColor", mColors);
        animator.setDuration(3000);
       animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }*/
}