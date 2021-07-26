package com.soft.credit911.Login.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}

	public class Data{

		@SerializedName("user_avatar")
		private String userAvatar;

		@SerializedName("api_token")
		private String apiToken;

		@SerializedName("last_name")
		private String lastName;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("email_verified_at")
		private String emailVerifiedAt;

		@SerializedName("token_2fa_expiry")
		private String token2faExpiry;

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("token_2fa")
		private String token2fa;

		@SerializedName("phone_number")
		private String phoneNumber;

		@SerializedName("id")
		private int id;

		@SerializedName("first_name")
		private String firstName;

		@SerializedName("email")
		private String email;

		@SerializedName("status")
		private String status;

		public String getUserAvatar(){
			return userAvatar;
		}

		public String getApiToken(){
			return apiToken;
		}

		public String getLastName(){
			return lastName;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public String getEmailVerifiedAt(){
			return emailVerifiedAt;
		}

		public String getToken2faExpiry(){
			return token2faExpiry;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public String getToken2fa(){
			return token2fa;
		}

		public String getPhoneNumber(){
			return phoneNumber;
		}

		public int getId(){
			return id;
		}

		public String getFirstName(){
			return firstName;
		}

		public String getEmail(){
			return email;
		}

		public String getStatus(){
			return status;
		}
	}
}