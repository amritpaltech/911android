package com.soft.credit911.UserProfile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateProfileResponse {

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

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

		@SerializedName("user_avatar")
		private String userAvatar;

		@SerializedName("api_token")
		private String apiToken;

		@SerializedName("roles")
		private List<RolesItem> roles;

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
		private Integer id;

		@SerializedName("first_name")
		private String firstName;

		@SerializedName("email")
		private String email;

		@SerializedName("status")
		private String status;

		public void setUserAvatar(String userAvatar){
			this.userAvatar = userAvatar;
		}

		public String getUserAvatar(){
			return userAvatar;
		}

		public void setApiToken(String apiToken){
			this.apiToken = apiToken;
		}

		public String getApiToken(){
			return apiToken;
		}

		public void setRoles(List<RolesItem> roles){
			this.roles = roles;
		}

		public List<RolesItem> getRoles(){
			return roles;
		}

		public void setLastName(String lastName){
			this.lastName = lastName;
		}

		public String getLastName(){
			return lastName;
		}

		public void setCreatedAt(String createdAt){
			this.createdAt = createdAt;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public void setEmailVerifiedAt(String emailVerifiedAt){
			this.emailVerifiedAt = emailVerifiedAt;
		}

		public String getEmailVerifiedAt(){
			return emailVerifiedAt;
		}

		public void setToken2faExpiry(String token2faExpiry){
			this.token2faExpiry = token2faExpiry;
		}

		public String getToken2faExpiry(){
			return token2faExpiry;
		}

		public void setUpdatedAt(String updatedAt){
			this.updatedAt = updatedAt;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public void setToken2fa(String token2fa){
			this.token2fa = token2fa;
		}

		public String getToken2fa(){
			return token2fa;
		}

		public void setPhoneNumber(String phoneNumber){
			this.phoneNumber = phoneNumber;
		}

		public String getPhoneNumber(){
			return phoneNumber;
		}

		public void setId(Integer id){
			this.id = id;
		}

		public Integer getId(){
			return id;
		}

		public void setFirstName(String firstName){
			this.firstName = firstName;
		}

		public String getFirstName(){
			return firstName;
		}

		public void setEmail(String email){
			this.email = email;
		}

		public String getEmail(){
			return email;
		}

		public void setStatus(String status){
			this.status = status;
		}

		public String getStatus(){
			return status;
		}
	}
	public class Pivot{

		@SerializedName("role_id")
		private Integer roleId;

		@SerializedName("model_type")
		private String modelType;

		@SerializedName("model_id")
		private Integer modelId;

		public void setRoleId(Integer roleId){
			this.roleId = roleId;
		}

		public Integer getRoleId(){
			return roleId;
		}

		public void setModelType(String modelType){
			this.modelType = modelType;
		}

		public String getModelType(){
			return modelType;
		}

		public void setModelId(Integer modelId){
			this.modelId = modelId;
		}

		public Integer getModelId(){
			return modelId;
		}
	}
	public class RolesItem{

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("name")
		private String name;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("id")
		private Integer id;

		@SerializedName("guard_name")
		private String guardName;

		@SerializedName("slug")
		private String slug;

		public void setUpdatedAt(String updatedAt){
			this.updatedAt = updatedAt;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public void setName(String name){
			this.name = name;
		}

		public String getName(){
			return name;
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

		public void setGuardName(String guardName){
			this.guardName = guardName;
		}

		public String getGuardName(){
			return guardName;
		}

		public void setSlug(String slug){
			this.slug = slug;
		}

		public String getSlug(){
			return slug;
		}
	}
}