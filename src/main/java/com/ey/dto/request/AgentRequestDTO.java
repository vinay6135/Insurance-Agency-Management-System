package com.ey.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AgentRequestDTO {
	
	@NotNull(message="Agent Name Cannot be Empty")
	private String name;
	
	@NotNull(message="Agent Email Id Required")
	@Email(message="Invalid Email Address")
	private String email;
	
	 @NotBlank(message = "Phone number is required")
	 @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
	 private String phone;

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public String getEmail() {
		 return email;
	 }

	 public void setEmail(String email) {
		 this.email = email;
	 }

	 public String getPhone() {
		 return phone;
	 }

	 public void setPhone(String phone) {
		 this.phone = phone;
	 }
	 
	 

}