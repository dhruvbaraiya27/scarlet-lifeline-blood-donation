package edu.neu.csye6200.repository;

import edu.neu.csye6200.model.BloodBank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BloodBankRepository extends MongoRepository<BloodBank, String> {
    boolean existsByName(String name);
    boolean existsByAddress(String address);
}
