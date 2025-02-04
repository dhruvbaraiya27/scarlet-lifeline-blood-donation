package edu.neu.csye6200.service;

import edu.neu.csye6200.dto.BloodBankDTO;
import edu.neu.csye6200.enums.BloodGroup;
import edu.neu.csye6200.exception.CustomException;
import edu.neu.csye6200.model.BloodBank;
import edu.neu.csye6200.repository.BloodBankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BloodBankService {
    private final BloodBankRepository bloodBankRepository;

    public BloodBank addBloodBank(BloodBankDTO dto) {

        if (bloodBankRepository.existsByName(dto.getName())) {
            throw new CustomException("Blood bank with this name already exists");
        }

        if (bloodBankRepository.existsByAddress(dto.getAddress())) {
            throw new CustomException("Blood bank already exists at this address");
        }

        BloodBank bloodBank = new BloodBank();
        bloodBank.setName(dto.getName());
        bloodBank.setAddress(dto.getAddress());
        bloodBank.setContactNumber(dto.getContactNumber());
        bloodBank.setInventory(dto.getInventory());

        return bloodBankRepository.save(bloodBank);
    }

    public void deleteBloodBank(String id) {
        if (!bloodBankRepository.existsById(id)) {
            throw new CustomException("Blood bank not found");
        }
        bloodBankRepository.deleteById(id);
    }

    public BloodBank updateInventory(String id, Map<BloodGroup, Integer> inventory) {
        BloodBank bloodBank = bloodBankRepository.findById(id)
                .orElseThrow(() -> new CustomException("Blood bank not found"));

        bloodBank.setInventory(inventory);
        return bloodBankRepository.save(bloodBank);
    }

    public List<BloodBank> getAllBloodBanks() {
        return bloodBankRepository.findAll();
    }

    public BloodBank getBloodBank(String id) {
        return bloodBankRepository.findById(id)
                .orElseThrow(() -> new CustomException("Blood bank not found"));
    }

    public BloodBank editBloodBank(String id, BloodBankDTO dto) {
        BloodBank bloodBank = bloodBankRepository.findById(id)
                .orElseThrow(() -> new CustomException("Blood bank not found"));

        bloodBank.setName(dto.getName());
        bloodBank.setAddress(dto.getAddress());
        bloodBank.setContactNumber(dto.getContactNumber());

        // Only update inventory if it's provided in the DTO
        if (dto.getInventory() != null) {
            bloodBank.setInventory(dto.getInventory());
        }

        return bloodBankRepository.save(bloodBank);
    }
}
