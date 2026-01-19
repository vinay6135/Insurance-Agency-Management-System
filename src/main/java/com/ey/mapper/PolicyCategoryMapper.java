package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.PolicyCategoryRequestDTO;
import com.ey.dto.response.PolicyCategoryResponseDTO;
import com.ey.entity.PolicyCategory;

@Component
public class PolicyCategoryMapper {

    public PolicyCategory toEntity(PolicyCategoryRequestDTO dto) {
        PolicyCategory category = new PolicyCategory();
        category.setCategoryName(dto.getCategoryName());
        category.setCategoryDescription(dto.getCategoryDescription());
        return category;
    }

    public PolicyCategoryResponseDTO toResponse(PolicyCategory entity) {
        PolicyCategoryResponseDTO dto = new PolicyCategoryResponseDTO();
        dto.setId(entity.getId());
        dto.setCategoryName(entity.getCategoryName());
        dto.setCategoryDescription(entity.getCategoryDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public void updateEntity(PolicyCategory entity, PolicyCategoryRequestDTO dto) {
        entity.setCategoryName(dto.getCategoryName());
        entity.setCategoryDescription(dto.getCategoryDescription());
    }
}
