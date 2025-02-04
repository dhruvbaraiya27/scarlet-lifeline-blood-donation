package edu.neu.csye6200.controller;

import edu.neu.csye6200.service.AppointmentService;
import edu.neu.csye6200.dto.AppointmentDTO;
import edu.neu.csye6200.dto.DonorRegistrationDTO;
import edu.neu.csye6200.dto.LoginDTO;
import edu.neu.csye6200.model.Appointment;
import edu.neu.csye6200.model.Donor;
import edu.neu.csye6200.service.DonorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
@RequiredArgsConstructor
@Tag(name = "Donor", description = "Donor management APIs")
public class DonorController {
    private final DonorService donorService;
    private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    @Operation(summary = "Get donor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donor found"),
            @ApiResponse(responseCode = "404", description = "Donor not found")
    })
    public ResponseEntity<Donor> getDonor(@PathVariable String id) {
        return ResponseEntity.ok(donorService.getDonorById(id));
    }

    @PostMapping("/register")
    @Operation(summary = "Register new donor")
    public ResponseEntity<Donor> register(@Valid @RequestBody DonorRegistrationDTO dto) {
        return ResponseEntity.ok(donorService.register(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Donor login")
    public ResponseEntity<Donor> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(donorService.login(dto));
    }

    @PostMapping("/appointments")
    @Operation(summary = "Create appointment")
    public ResponseEntity<Appointment> createAppointment(
            @RequestParam String donorId,
            @Valid @RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentService.createAppointment(donorId, dto));
    }

    @GetMapping("/{id}/appointments")
    @Operation(summary = "Get donor appointments")
    public ResponseEntity<List<Appointment>> getDonorAppointments(@PathVariable String id) {
        return ResponseEntity.ok(appointmentService.getDonorAppointments(id));
    }
}
