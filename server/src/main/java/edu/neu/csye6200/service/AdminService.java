package edu.neu.csye6200.service;

import edu.neu.csye6200.dto.AdminRegistrationDTO;
import edu.neu.csye6200.dto.LoginDTO;
import edu.neu.csye6200.exception.CustomException;
import edu.neu.csye6200.model.Admin;
import edu.neu.csye6200.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Admin register(AdminRegistrationDTO dto) {
        if (adminRepository.existsByUsername(dto.getUsername())) {
            throw new CustomException("Username already exists");
        }

        Admin admin = new Admin();
        admin.setUsername(dto.getUsername());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());

        return adminRepository.save(admin);
    }

    public Admin login(LoginDTO dto) {
        Admin admin = adminRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new CustomException("Invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
            throw new CustomException("Invalid credentials");
        }

        return admin;
    }
}
