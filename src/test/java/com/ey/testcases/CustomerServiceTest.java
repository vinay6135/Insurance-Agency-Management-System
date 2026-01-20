package com.ey.testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ey.dto.request.CustomerRequestDTO;
import com.ey.dto.request.UpdateCustomerRequest;
import com.ey.dto.response.CustomerResponseDTO;
import com.ey.enums.Role;
import com.ey.exception.AccessDeniedException;
import com.ey.exception.BusinessException;
import com.ey.service.AuthService;
import com.ey.service.CustomerService;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthService authService;

    @Test
    void addCustomer_success() {
        authService.signup("test@test.com", "pass", Role.CUSTOMER);

        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setFullName("Vinay");
        dto.setAge(25);
        dto.setGender("MALE");
        dto.setContactNumber("9876543210");
        dto.setAddress("Hyderabad");
        dto.setNomineeName("Father");
        dto.setNomineeRelation("FATHER");
        dto.setNomineePhone("9876543211");
        dto.setIdentityProofNumber("ABCDE1234F");

        CustomerResponseDTO res =
                customerService.addCustomer(dto, "test@test.com");

        assertNotNull(res.getId());
        assertEquals("Vinay", res.getFullName());
    }

    @Test
    void addCustomerduplicate() {
        authService.signup("test1@test.com", "pass", Role.CUSTOMER);

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

        customerService.addCustomer(dto, "test1@test.com");

        assertThrows(BusinessException.class, () -> {
            customerService.addCustomer(dto, "test1@test.com");
        });
    }

    @Test
    void getCustomer_unauthorized() {
        authService.signup("test3@test.com", "pass", Role.CUSTOMER);
        authService.signup("test4@test.com", "pass", Role.CUSTOMER);

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

        CustomerResponseDTO res =
                customerService.addCustomer(dto, "test3@test.com");

        assertThrows(AccessDeniedException.class, () -> {
            customerService.getCustomer(res.getId(), "test4@test.com");
        });
    }

    @Test
    void updateCustomer_success() {
        authService.signup("test5@test.com", "pass", Role.CUSTOMER);

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

        CustomerResponseDTO res =
                customerService.addCustomer(dto, "test5@test.com");

        UpdateCustomerRequest up = new UpdateCustomerRequest();
        up.setContactNumber("9999999999");
        up.setAddress("Bangalore");

        CustomerResponseDTO updated =
                customerService.updateCustomer(res.getId(), up, "test5@test.com");

        assertEquals("9999999999", updated.getContactNumber());
        assertEquals("Bangalore", updated.getAddress());
    }
}

