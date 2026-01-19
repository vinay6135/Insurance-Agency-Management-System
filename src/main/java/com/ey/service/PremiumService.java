package com.ey.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.entity.Customer;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.Notification;
import com.ey.entity.PremiumPayment;
import com.ey.enums.InstallmentType;
import com.ey.enums.PaymentStatus;
import com.ey.enums.PolicyStatus;
import com.ey.exception.BadRequestException;
import com.ey.exception.BusinessException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.CustomerPolicyMapper;
import com.ey.repository.CustomerPolicyRepository;
import com.ey.repository.NotificationRepository;
import com.ey.repository.PremiumPaymentRepository;

@Service
public class PremiumService {

    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;

    @Autowired
    private PremiumPaymentRepository premiumPaymentRepository;
    
    @Autowired
    private NotificationRepository notificationrepo;
    
    @Autowired
    private CustomerPolicyMapper customerpolicymapper;

    
    public Map<String, Object> preview(Long customerPolicyId) {

        CustomerPolicy cp = customerPolicyRepository.findById(customerPolicyId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer policy not found"));

        int age = cp.getCustomer().getAge();
        double basePremium = cp.getPolicy().getPremiumAmount();
        double ageBasedPremium = calculateAgeBasedPremium(basePremium, age);

        return Map.of(
                "totalPremium", ageBasedPremium,
                "installments", List.of(
                        Map.of("type", InstallmentType.MONTHLY, "amount", ageBasedPremium / 12),
                        Map.of("type", InstallmentType.QUARTERLY, "amount", ageBasedPremium / 4),
                        Map.of("type", InstallmentType.YEARLY, "amount", ageBasedPremium)
                )
        );
    }

    public CustomerPolicyResponseDTO pay(Long customerPolicyId, InstallmentType type) {

        CustomerPolicy cp = customerPolicyRepository.findById(customerPolicyId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer policy not found"));

        int age = cp.getCustomer().getAge();
        double basePremium = cp.getPolicy().getPremiumAmount();
        double finalPremium = calculateAgeBasedPremium(basePremium, age);

        LocalDate today = LocalDate.now();
        LocalDate start;
        LocalDate end;

        switch (type) {
            case MONTHLY -> {
                start = today.withDayOfMonth(1);
                end = today.withDayOfMonth(today.lengthOfMonth());
            }
            case QUARTERLY -> {
                int quarter = (today.getMonthValue() - 1) / 3;
                start = LocalDate.of(today.getYear(), quarter * 3 + 1, 1);
                end = start.plusMonths(3).minusDays(1);
            }
            case YEARLY -> {
                start = LocalDate.of(today.getYear(), 1, 1);
                end = LocalDate.of(today.getYear(), 12, 31);
            }
            default -> throw new BadRequestException("Invalid installment type");
        }
        double amount =
                type == InstallmentType.YEARLY ? finalPremium :
                type == InstallmentType.QUARTERLY ? finalPremium / 4 :
                finalPremium / 12;
        if(!premiumPaymentRepository.existsByCustomerPolicyAndStatus(cp,PaymentStatus.PAID))
        {
        	sendNotification(
                    cp.getCustomer(),
                    "Premium payment request: ₹" + amount +
                    " for " + type +
                    " installment." 
            );
        	
        	sendNotification(
                    cp.getCustomer(),
                    "Welcome aboard!\n"
                    + "\n"
                    + "Your first premium of ₹"+amount+" has been received.\n"
                    + "Your policy "+cp.getPolicy().getPolicyName()+" is now active.\n"
                    + "\n"
                    + "Stay insured. Stay protected.\n"
                    
            );
        	
        	
        }

        boolean alreadyPaid =
                premiumPaymentRepository
                        .existsByCustomerPolicyIdAndPeriodStartDateAndPeriodEndDateAndStatus(
                                customerPolicyId, start, end, PaymentStatus.PAID
                        );

        if (alreadyPaid) {
            throw new BusinessException(
                    "Premium already paid for this " + type + " period",
                    HttpStatus.CONFLICT
            );
        }

        sendNotification(
                cp.getCustomer(),
                "Premium payment request: ₹" + amount +
                " for " + type +
                " installment. Due before " + end
        );

        PremiumPayment payment = new PremiumPayment();
        payment.setCustomerPolicy(cp);
        payment.setInstallmentType(type);
        payment.setAmount(amount);
        payment.setPaymentDate(today);
        payment.setPeriodStartDate(start);
        payment.setPeriodEndDate(end);
        payment.setStatus(PaymentStatus.PAID);

        premiumPaymentRepository.save(payment);

        if (cp.getStatus() != PolicyStatus.ACTIVE) {
            cp.setStatus(PolicyStatus.ACTIVE);
            customerPolicyRepository.save(cp);
        }


        sendNotification(
                cp.getCustomer(),
                "Payment successful: ₹" + amount +
                " paid for " + type +
                " installment." 
        );
        CustomerPolicyResponseDTO resdto=customerpolicymapper.toResponse(cp);
        resdto.setInstallmentType(type);

        return resdto;
    }
    
    private double calculateAgeBasedPremium(double basePremium, int age) {
        if (age <= 30) return basePremium;
        if (age <= 45) return basePremium * 1.2;
        if (age <= 60) return basePremium * 1.5;
        return basePremium * 2.0;
    }

    private void sendNotification(Customer customer, String message) {
        Notification notification = new Notification();
        notification.setCustomer(customer);
        notification.setMessage(message);
        notificationrepo.save(notification);
    }
}
