package com.soft.credit911.ForgotPassword.model;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}