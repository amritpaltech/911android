package com.soft.credit911.ui.OTPVerification.mvp;

import com.soft.credit911.datamodel.GenerateOTPResponse;

public interface OTPVerificationView {
    void generateOTPResponse(GenerateOTPResponse generateOTPResponse);
    void verifiyOTPResponse(GenerateOTPResponse generateOTPResponse);
}
