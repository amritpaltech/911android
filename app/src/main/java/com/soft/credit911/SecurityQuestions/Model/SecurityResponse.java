package com.soft.credit911.SecurityQuestions.Model;

import com.google.gson.annotations.SerializedName;

public class SecurityResponse{

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public class Data{

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("user_id")
		private Integer userId;

		@SerializedName("questions")
		private String questions;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("id")
		private Integer id;

		@SerializedName("auth_token")
		private String authToken;

		@SerializedName("status")
		private String status;

		public void setUpdatedAt(String updatedAt){
			this.updatedAt = updatedAt;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public void setUserId(Integer userId){
			this.userId = userId;
		}

		public Integer getUserId(){
			return userId;
		}

		public void setQuestions(String questions){
			this.questions = questions;
		}

		public String getQuestions(){
			return questions;
		}

		public void setCreatedAt(String createdAt){
			this.createdAt = createdAt;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public void setId(Integer id){
			this.id = id;
		}

		public Integer getId(){
			return id;
		}

		public void setAuthToken(String authToken){
			this.authToken = authToken;
		}

		public String getAuthToken(){
			return authToken;
		}

		public void setStatus(String status){
			this.status = status;
		}

		public String getStatus(){
			return status;
		}
	}
}