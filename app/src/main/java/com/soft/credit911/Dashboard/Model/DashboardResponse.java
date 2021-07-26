package com.soft.credit911.Dashboard.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashboardResponse{

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

		@SerializedName("credit_report")
		private CreditReport creditReport;

		@SerializedName("credit_report_history")
		private List<CreditReportHistoryItem> creditReportHistory;

		@SerializedName("userinfo")
		private Userinfo userinfo;

		public void setCreditReport(CreditReport creditReport){
			this.creditReport = creditReport;
		}

		public CreditReport getCreditReport(){
			return creditReport;
		}

		public void setCreditReportHistory(List<CreditReportHistoryItem> creditReportHistory){
			this.creditReportHistory = creditReportHistory;
		}

		public List<CreditReportHistoryItem> getCreditReportHistory(){
			return creditReportHistory;
		}

		public void setUserinfo(Userinfo userinfo){
			this.userinfo = userinfo;
		}

		public Userinfo getUserinfo(){
			return userinfo;
		}
	}



	public class Userinfo{

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
	public class RolesItem{

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("name")
		private String name;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("pivot")
		private Pivot pivot;

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

		public void setPivot(Pivot pivot){
			this.pivot = pivot;
		}

		public Pivot getPivot(){
			return pivot;
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
	public class CreditReport{

		@SerializedName("score")
		private String score;

		@SerializedName("report_factor")
		private String reportFactor;

		@SerializedName("report_data")
		private Object reportData;

		@SerializedName("updated_at")
		private Object updatedAt;

		@SerializedName("user_id")
		private Integer userId;

		@SerializedName("created_at")
		private Object createdAt;

		@SerializedName("id")
		private Integer id;

		@SerializedName("score_date")
		private String scoreDate;

		@SerializedName("source")
		private String source;

		@SerializedName("report_identifier")
		private Object reportIdentifier;

		@SerializedName("next_date")
		private String nextDate;

		@SerializedName("report_date")
		private String reportDate;

		public void setScore(String score){
			this.score = score;
		}

		public String getScore(){
			return score;
		}

		public void setReportFactor(String reportFactor){
			this.reportFactor = reportFactor;
		}

		public String getReportFactor(){
			return reportFactor;
		}

		public void setReportData(Object reportData){
			this.reportData = reportData;
		}

		public Object getReportData(){
			return reportData;
		}

		public void setUpdatedAt(Object updatedAt){
			this.updatedAt = updatedAt;
		}

		public Object getUpdatedAt(){
			return updatedAt;
		}

		public void setUserId(Integer userId){
			this.userId = userId;
		}

		public Integer getUserId(){
			return userId;
		}

		public void setCreatedAt(Object createdAt){
			this.createdAt = createdAt;
		}

		public Object getCreatedAt(){
			return createdAt;
		}

		public void setId(Integer id){
			this.id = id;
		}

		public Integer getId(){
			return id;
		}

		public void setScoreDate(String scoreDate){
			this.scoreDate = scoreDate;
		}

		public String getScoreDate(){
			return scoreDate;
		}

		public void setSource(String source){
			this.source = source;
		}

		public String getSource(){
			return source;
		}

		public void setReportIdentifier(Object reportIdentifier){
			this.reportIdentifier = reportIdentifier;
		}

		public Object getReportIdentifier(){
			return reportIdentifier;
		}

		public void setNextDate(String nextDate){
			this.nextDate = nextDate;
		}

		public String getNextDate(){
			return nextDate;
		}

		public void setReportDate(String reportDate){
			this.reportDate = reportDate;
		}

		public String getReportDate(){
			return reportDate;
		}
	}

	public class CreditReportHistoryItem{

		@SerializedName("score")
		private String score;

		@SerializedName("score_date")
		private String scoreDate;

		public void setScore(String score){
			this.score = score;
		}

		public String getScore(){
			return score;
		}

		public void setScoreDate(String scoreDate){
			this.scoreDate = scoreDate;
		}

		public String getScoreDate(){
			return scoreDate;
		}
	}


}