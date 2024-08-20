package com.example.ums.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequest {

	@NotNull(message = "userName cannot be null")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$")
	private String userName;
	@NotNull(message = "useremail cannot be null")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$")
	private String userEmail;
	@NotNull(message = "password cannot be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "password must contain atleast 1 uppercase"
			+ " 1 lower case and special character and 8 lengths")
	private String userPassword;
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long userContact;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public long getUserContact() {
		return userContact;
	}
	public void setUserContact(long userContact) {
		this.userContact = userContact;
	}
	
	
}
