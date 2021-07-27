package com.soft.credit911.ui.dashboard.Dashboard.mvp;

import com.soft.credit911.datamodel.DashboardResponse;
import com.soft.credit911.datamodel.SecurityResponse;

public interface DashboardView {
    void DashboardResponse(DashboardResponse dashboardResponse);
    void SecurityResponse(SecurityResponse securityResponse);
}
