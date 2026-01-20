package com.ey.testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ey.entity.User;
import com.ey.enums.Role;
import com.ey.exception.BadRequestException;
import com.ey.exception.BusinessException;
import com.ey.service.AuthService;

import jakarta.transaction.Transactional;

@SpringBootTest

class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    void signup() {
        User user = authService.signup("k@test.com", "pass123", Role.CUSTOMER);
        assertNotNull(user.getId());
        assertEquals("k@test.com", user.getEmail());
    }

    @Test
    void signupduplicateEmail() {
        authService.signup("l@test.com", "pass", Role.CUSTOMER);

        assertThrows(BusinessException.class, () -> {
            authService.signup("l@test.com", "pass", Role.CUSTOMER);
        });
    }

    @Test
    void login_success() {
        authService.signup("k@test.com", "pass", Role.CUSTOMER);
        String token = authService.login("k@test.com", "pass");
        assertNotNull(token);
    }
    
    @Test
    void login_fail() {
    	authService.signup("vijay@gmail.com", "password", Role.CUSTOMER);
  
    	assertThrows(BadRequestException.class,()->{authService.login("reddy@gmail", "password");});
    }
}
