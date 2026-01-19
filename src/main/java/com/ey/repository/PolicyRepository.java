package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByCategoryId(Long categoryId);
    List<Policy> findByCategoryIdAndActiveTrue(Long categoryId);

    List<Policy> findByActiveTrue();
}
