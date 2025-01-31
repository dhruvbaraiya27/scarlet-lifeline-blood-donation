package edu.neu.csye6200.controller;

import edu.neu.csye6200.model.Appointment;
import edu.neu.csye6200.model.BloodBank;
import edu.neu.csye6200.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointment", description = "Appointment management APIs")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    @Operation(summary = "Get all blood appointment")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment details")
    public ResponseEntity<Appointment> getAppointment(@PathVariable String id) {
        return ResponseEntity.ok(appointmentService.getAppointment(id));
    }

}
