package edu.neu.csye6200.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Map;
import edu.neu.csye6200.enums.BloodGroup;

@Data
public class BloodBankDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Contact number is required")
    private String contactNumber;

    private Map<BloodGroup, Integer> inventory;
}