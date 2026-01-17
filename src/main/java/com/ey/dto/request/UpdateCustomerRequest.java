package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateCustomerRequest {
	
	 @NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
	    private String contactNumber;
	 
	 @NotBlank(message = "Address is required")
	    @Size(min = 10)
	    private String address;
	 
	 @NotBlank(message = "Nominee name is required")
	    private String nomineeName;

	   @NotBlank(message = "Nominee relation is required")
	    private String nomineeRelation;
	    
	    @NotBlank(message="Nominee phone number required")
	    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
	    private String nomineePhone;

		public String getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getNomineeName() {
			return nomineeName;
		}

		public void setNomineeName(String nomineeName) {
			this.nomineeName = nomineeName;
		}

		public String getNomineeRelation() {
			return nomineeRelation;
		}

		public void setNomineeRelation(String nomineeRelation) {
			this.nomineeRelation = nomineeRelation;
		}

		public String getNomineePhone() {
			return nomineePhone;
		}

		public void setNomineePhone(String nomineePhone) {
			this.nomineePhone = nomineePhone;
		}
	    
	    

}
