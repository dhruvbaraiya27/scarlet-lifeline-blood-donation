package edu.neu.csye6200.model;

import edu.neu.csye6200.enums.BloodGroup;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Document(collection = "donors")
public class Donor {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
    private String password;
    private String name;
    private String email;
    private BloodGroup bloodGroup;
    private String phoneNumber;
    private String address;
}