package com.ey.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.entity.CustomerPolicy;
import com.ey.entity.PremiumPayment;
import com.ey.enums.PaymentStatus;

public interface PremiumPaymentRepository
extends JpaRepository<PremiumPayment, Long> {

List<PremiumPayment> findByCustomerPolicyId(Long customerPolicyId);
boolean existsByCustomerPolicyIdAndPeriodStartDateAndPeriodEndDateAndStatus(
        Long customerPolicyId,
        LocalDate start,
        LocalDate end,
        PaymentStatus status
);
boolean existsByCustomerPolicyAndStatus(
        CustomerPolicy customerPolicy,
        PaymentStatus status
);
boolean existsByCustomerPolicyIdAndStatus(Long id, PaymentStatus due);

}

