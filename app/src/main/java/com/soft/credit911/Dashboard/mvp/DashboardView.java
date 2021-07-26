package com.soft.credit911.Dashboard.mvp;

import com.soft.credit911.Dashboard.Model.DashboardResponse;
import com.soft.credit911.SecurityQuestions.Model.SecurityResponse;

public interface DashboardView {
    void DashboardResponse(DashboardResponse dashboardResponse);
    void SecurityResponse(SecurityResponse securityResponse);
}
