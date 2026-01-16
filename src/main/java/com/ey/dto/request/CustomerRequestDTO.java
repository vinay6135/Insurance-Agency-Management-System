package com.ey.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerRequestDTO {
	
	@NotBlank(message="Full name required")
	@Size(min=3, max=50)
	private String fullName;
	
	@NotBlank(message="Date of Birth is required")
	@Past(message="Date of birth must be in the past")
	private LocalDate dateofBirth;
	
	@NotNull(message="Age is required")
	@Min(value=18, message="Minimum age must be 18")
	@Max(value=80, message="Maximum age allowed is 80")
	private  Integer age;
	
	@NotBlank(message="Gender is required")
	private String gender;
	
	@NotNull(message="Phone number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
    private String contactNumber;

    @NotBlank(message = "Address is required")
    @Size(min = 10)
    private String address;

    @NotBlank(message = "Nominee name is required")
    private String nomineeName;

    @NotBlank(message = "Nominee relation is required")
    private String nomineeRelation;
    
    @NotBlank(message="Nominee phone number is required")
    private String nomineePhone;

    @NotBlank(message = "Identity proof number is required")
    private String identityProofNumber;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(LocalDate dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}



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

	public String getIdentityProofNumber() {
		return identityProofNumber;
	}

	public void setIdentityProofNumber(String identityProofNumber) {
		this.identityProofNumber = identityProofNumber;
	}
    
    
    

}
