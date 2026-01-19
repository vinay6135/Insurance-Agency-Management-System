package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateAgentRequestDTO {
	@NotNull(message="Agent Name Cannot be Empty")
	private String name;
	
	 @NotBlank(message = "Phone number is required")
	 @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
	 private String phone;

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public String getPhone() {
		 return phone;
	 }

	 public void setPhone(String phone) {
		 this.phone = phone;
	 }
	 
	 


}
