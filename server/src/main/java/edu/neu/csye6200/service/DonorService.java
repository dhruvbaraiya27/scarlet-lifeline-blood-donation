package edu.neu.csye6200.service;

import edu.neu.csye6200.dto.DonorRegistrationDTO;
import edu.neu.csye6200.dto.LoginDTO;
import edu.neu.csye6200.exception.CustomException;
import edu.neu.csye6200.model.Donor;
import edu.neu.csye6200.repository.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonorService {
    private final DonorRepository donorRepository;
    private final PasswordEncoder passwordEncoder;

    public Donor register(DonorRegistrationDTO dto) {
        if (donorRepository.existsByUsername(dto.getUsername())) {
            throw new CustomException("Username already exists");
        }

        Donor donor = new Donor();
        donor.setUsername(dto.getUsername());
        donor.setPassword(passwordEncoder.encode(dto.getPassword()));
        donor.setName(dto.getName());
        donor.setEmail(dto.getEmail());
        donor.setBloodGroup(dto.getBloodGroup());
        donor.setPhoneNumber(dto.getPhoneNumber());
        donor.setAddress(dto.getAddress());

        return donorRepository.save(donor);
    }

    public Donor login(LoginDTO dto) {
        Donor donor = donorRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new CustomException("Invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), donor.getPassword())) {
            throw new CustomException("Invalid credentials");
        }

        return donor;
    }

    public Donor getDonorById(String id) {
        return donorRepository.findById(id)
                .orElseThrow(() -> new CustomException("Donor not found"));
    }
}