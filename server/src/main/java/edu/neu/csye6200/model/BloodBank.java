package edu.neu.csye6200.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;
import java.util.HashMap;
import edu.neu.csye6200.enums.BloodGroup;

@Data
@Document(collection = "bloodbanks")
public class BloodBank {
    @Id
    private String id;
    private String name;
    private String address;
    private String contactNumber;
    private Map<BloodGroup, Integer> inventory = new HashMap<>();
}