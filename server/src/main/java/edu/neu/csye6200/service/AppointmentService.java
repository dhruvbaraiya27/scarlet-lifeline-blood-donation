package edu.neu.csye6200.service;

import edu.neu.csye6200.dto.AppointmentDTO;
import edu.neu.csye6200.enums.AppointmentStatus;
import edu.neu.csye6200.exception.CustomException;
import edu.neu.csye6200.model.Appointment;
import edu.neu.csye6200.model.BloodBank;
import edu.neu.csye6200.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public Appointment createAppointment(String donorId, AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setDonorId(donorId);
        appointment.setBloodBankId(dto.getBloodBankId());
        appointment.setAppointmentDate(dto.getAppointmentDate());

        return appointmentRepository.save(appointment);
    }

    public Appointment updateStatus(String id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Appointment not found"));

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getDonorAppointments(String donorId) {
        return appointmentRepository.findByDonorId(donorId);
    }

    public List<Appointment> getBloodBankAppointments(String bloodBankId) {
        return appointmentRepository.findByBloodBankId(bloodBankId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointment(String id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Appointment not found"));
    }

    public void deleteAppointment(String id) {
        if (!appointmentRepository.existsById(id)) {
            throw new CustomException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }
}