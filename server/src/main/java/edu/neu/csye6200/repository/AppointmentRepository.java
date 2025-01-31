package edu.neu.csye6200.repository;


import edu.neu.csye6200.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByDonorId(String donorId);
    List<Appointment> findByBloodBankId(String bloodBankId);
}