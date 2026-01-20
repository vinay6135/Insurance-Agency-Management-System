package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;

import com.ey.config.JwtUtil;
import com.ey.entity.User;
import com.ey.enums.Role;
import com.ey.exception.BadRequestException;
import com.ey.exception.BusinessException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.repository.UserRepository;

@Service
public class AuthService {
	org.slf4j.Logger logger  = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User signup(String email, String password, Role role) {
    	logger.info("Signup attempt for email: {}, role: {}", email, role);

        if (userRepository.existsByEmail(email)) {
        	logger.warn("Signup failed. Email already exists: {}", email);
            throw new BusinessException("Email already exists",HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        User saved = userRepository.save(user);
        logger.info("User registered successfully. UserId={}, Email={}, Role={}",
                saved.getId(), saved.getEmail(), saved.getRole());
        

        return saved;
    }

    public String login(String email, String password) {
    	logger.info("Login attempt for email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }
    
    public String resetPassword(String email, String oldPassword, String newPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BusinessException("Old password is incorrect", HttpStatus.BAD_REQUEST);
        }

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new BusinessException("New password cannot be same as old password", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password reset successful";
    }
}
