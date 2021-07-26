package com.soft.credit911.OTPVerification.mvp;

import com.soft.credit911.OTPVerification.model.GenerateOTPResponse;

public interface OTPVerificationView {
    void generateOTPResponse(GenerateOTPResponse generateOTPResponse);
    void verifiyOTPResponse(GenerateOTPResponse generateOTPResponse);
}
