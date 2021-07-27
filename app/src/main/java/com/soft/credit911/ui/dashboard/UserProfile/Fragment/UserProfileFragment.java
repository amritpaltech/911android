package com.soft.credit911.ui.dashboard.UserProfile.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.soft.credit911.ui.casemanagement.CaseManagementActivity;
import com.soft.credit911.ui.Chat.Activity.ChatActivity;
import com.soft.credit911.ui.Login.LoginActivity;
import com.soft.credit911.R;
import com.soft.credit911.ui.Changepassword.ChangePasswordActivity;
import com.soft.credit911.ui.documnet.DocumentActivity;
import com.soft.credit911.ui.MyProfile.Activity.MyProfileActivity;
import com.soft.credit911.ui.notifications.NotificationActivity;
import com.soft.credit911.datamodel.UpdateProfileResponse;
import com.soft.credit911.ui.dashboard.UserProfile.mvp.UpdateProfilePresenter;
import com.soft.credit911.ui.dashboard.UserProfile.mvp.UpdateProfileView;
import com.soft.credit911.Utils.AppPreference;
import com.soft.credit911.databinding.FragmentUserProfileBinding;


public class UserProfileFragment extends Fragment implements UpdateProfileView {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public UserProfileFragment() {

    }

    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
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

    private FragmentUserProfileBinding fragmentBinding;
    UpdateProfilePresenter updateProfilePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentUserProfileBinding.inflate(inflater, container, false);


        fragmentBinding.tvMyProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MyProfileActivity.class);
            getActivity().startActivity(intent);
        });

        fragmentBinding.tvChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
            getActivity().startActivity(intent);
        });

        fragmentBinding.tvChat.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            getActivity().startActivity(intent);
        });

        fragmentBinding.tvDocument.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DocumentActivity.class);
            getActivity().startActivity(intent);
        });


        fragmentBinding.tvNotification.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NotificationActivity.class);
            getActivity().startActivity(intent);
        });
        fragmentBinding.tvCaseManagement.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CaseManagementActivity.class);
            getActivity().startActivity(intent);
        });


        fragmentBinding.logoutTv.setOnClickListener(v -> {
            new AppPreference(getActivity()).setIsLogin(false);
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().startActivity(intent);
        });

        Glide.with(getContext())
                .load(R.drawable.chris_hemsworth)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(500)))
                .placeholder(R.drawable.chris_hemsworth)
                .into(fragmentBinding.ivUserImg);
         updateProfilePresenter = new UpdateProfilePresenter(getContext(), this::UpdateProfileResponse);
       //  initView();
        return fragmentBinding.getRoot();
    }

    private void initView() {
        updateProfilePresenter.updateProfileAvatar();
    }

    @Override
    public void UpdateProfileResponse(UpdateProfileResponse updateProfileResponse) {
        /*CommonUtils.showdialog(updateProfileResponse.getMessage(), getContext(), false);*/
    }
}