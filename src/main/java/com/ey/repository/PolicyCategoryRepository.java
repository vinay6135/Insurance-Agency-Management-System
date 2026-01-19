package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.entity.PolicyCategory;

public interface PolicyCategoryRepository
extends JpaRepository<PolicyCategory, Long> {
}

