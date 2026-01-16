package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PolicyCategoryRequestDTO {
	@NotBlank
    private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
