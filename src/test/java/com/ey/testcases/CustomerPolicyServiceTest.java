package com.ey.testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ey.dto.request.CustomerRequestDTO;
import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.dto.response.CustomerResponseDTO;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.Policy;
import com.ey.enums.PolicyStatus;
import com.ey.enums.Role;
import com.ey.exception.BusinessException;
import com.ey.repository.CustomerPolicyRepository;
import com.ey.repository.CustomerRepository;
import com.ey.repository.PolicyRepository;
import com.ey.service.AuthService;
import com.ey.service.CustomerPolicyService;
import com.ey.service.CustomerService;

@SpringBootTest
class CustomerPolicyServiceTest {

    @Autowired
    private CustomerPolicyService service;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthService authService;

    @Autowired
    private PolicyRepository policyRepository;
    
    @Autowired
    private CustomerRepository customerrepo;
    
    @Autowired
    private CustomerPolicyRepository cprepo;

    @Test
    void purchasePolicy_success() {
        authService.signup("cp3@test.com", "pass", Role.CUSTOMER);

        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setFullName("Test");
        dto.setAge(25);
        dto.setGender("MALE");
        dto.setContactNumber("9876543210");
        dto.setAddress("Hyderabad");
        dto.setNomineeName("Father");
        dto.setNomineeRelation("FATHER");
        dto.setNomineePhone("9876543211");
        dto.setIdentityProofNumber("ABCDE1234F");

        customerService.addCustomer(dto, "cp3@test.com");

        Policy p = new Policy();
        p.setPolicyName("Health");
        p.setCoverageAmount(500000.0);
        p.setPremiumAmount(12000.0);
        p.setDurationYears(1);
        p.setActive(true);

        Policy p1=policyRepository.save(p);
        

        CustomerPolicyResponseDTO res =
                service.purchasePolicy(p1.getId(), "cp3@test.com");

        assertEquals(PolicyStatus.PENDING_PAYMENT, res.getStatus());
    }

    @Test
    void purchasePolicy_duplicate_shouldFail() {
    	 authService.signup("cp4@test.com", "pass", Role.CUSTOMER);

         CustomerRequestDTO dto = new CustomerRequestDTO();
         dto.setFullName("Test");
         dto.setAge(25);
         dto.setGender("MALE");
         dto.setContactNumber("9876543210");
         dto.setAddress("Hyderabad");
         dto.setNomineeName("Father");
         dto.setNomineeRelation("FATHER");
         dto.setNomineePhone("9876543211");
         dto.setIdentityProofNumber("ABCDE1234F");

         CustomerResponseDTO resdto=customerService.addCustomer(dto, "cp4@test.com");

         Policy p = new Policy();
         p.setPolicyName("Health");
         p.setCoverageAmount(500000.0);
         p.setPremiumAmount(12000.0);
         p.setDurationYears(1);
         p.setActive(true);

         Policy p2=policyRepository.save(p);
         CustomerPolicy cp = new CustomerPolicy();
         cp.setCustomer(customerrepo.getById(resdto.getId()));
         cp.setPolicy(p);
         cp.setStatus(PolicyStatus.PENDING_PAYMENT);
         CustomerPolicy saved = cprepo.save(cp);

        assertThrows(BusinessException.class, () -> {
            service.purchasePolicy(saved.getId(), "cp4@test.com");
        });
    }


}

