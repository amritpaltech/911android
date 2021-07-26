package com.soft.credit911.CaseMangement.Fragement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soft.credit911.CaseMangement.Adapter.CaseManagementDetailsAdapter;
import com.soft.credit911.CaseMangement.Model.CashDemoModel;
import com.soft.credit911.R;
import com.soft.credit911.databinding.FragmentCaseManagementBinding;
import com.soft.credit911.databinding.ToolbarBinding;

import java.util.ArrayList;

public class CaseManagementFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CaseManagementFragment() {
    }


    public static CaseManagementFragment newInstance(String param1, String param2) {
        CaseManagementFragment fragment = new CaseManagementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentCaseManagementBinding fragmentBinding;
    private ToolbarBinding toolbarBinding;
    private ArrayList<CashDemoModel> cashDemoModelList = new ArrayList<>();
    private CaseManagementDetailsAdapter caseManagementDetailsAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBinding = FragmentCaseManagementBinding.inflate(inflater, container, false);
        toolbarBinding = fragmentBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Case Management");
        toolbarBinding.navigationIcon.setVisibility(View.GONE);

        caseManagementDetailsAdapter = new CaseManagementDetailsAdapter(cashDemoModelList,getContext());
        fragmentBinding.rvCaseManagement.setAdapter(caseManagementDetailsAdapter);
        prepareCaseManagementData();
        return fragmentBinding.getRoot();
    }

    private void prepareCaseManagementData() {
        CashDemoModel cashDemoModel = new CashDemoModel("Dispute with SBI bank","Open","open","20 june 2021","","Agent:Haary","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",R.drawable.chris_hemsworth);
        cashDemoModelList.add(cashDemoModel);
        cashDemoModel = new CashDemoModel("Dispute with HDFC bank","","Closed","19 Dec 2021","","Agent:Harry","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",R.drawable.chris_hemsworth);
        cashDemoModelList.add(cashDemoModel);
        cashDemoModel = new CashDemoModel("Dispute with HDFC bank","","Closed","19 Dec 2021","","Agent:Harry","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",R.drawable.chris_hemsworth);
        cashDemoModelList.add(cashDemoModel);
        cashDemoModel = new CashDemoModel("Dispute with HDFC bank","","Closed","19 Dec 2021","","Agent:Harry","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",R.drawable.chris_hemsworth);
        cashDemoModelList.add(cashDemoModel);
        cashDemoModel = new CashDemoModel("Dispute with HDFC bank","","Closed","19 Dec 2021","","Agent:Harry","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",R.drawable.chris_hemsworth);
        cashDemoModelList.add(cashDemoModel);
        cashDemoModel = new CashDemoModel("Dispute with HDFC bank","","Closed","19 Dec 2021","","Agent:Harry","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",R.drawable.chris_hemsworth);
        cashDemoModelList.add(cashDemoModel);
        caseManagementDetailsAdapter.notifyDataSetChanged();

    }
}