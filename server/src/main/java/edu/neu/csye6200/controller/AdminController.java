package edu.neu.csye6200.controller;

import edu.neu.csye6200.dto.AdminRegistrationDTO;
import edu.neu.csye6200.dto.LoginDTO;
import edu.neu.csye6200.dto.BloodBankDTO;
import edu.neu.csye6200.enums.AppointmentStatus;
import edu.neu.csye6200.enums.BloodGroup;
import edu.neu.csye6200.model.Admin;
import edu.neu.csye6200.model.Appointment;
import edu.neu.csye6200.model.BloodBank;
import edu.neu.csye6200.service.AdminService;
import edu.neu.csye6200.service.BloodBankService;
import edu.neu.csye6200.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin management APIs")
public class AdminController {
    private final AdminService adminService;
    private final BloodBankService bloodBankService;
    private final AppointmentService appointmentService;

    @PostMapping("/register")
    @Operation(summary = "Register new admin")
    public ResponseEntity<Admin> register(@Valid @RequestBody AdminRegistrationDTO dto) {
        return ResponseEntity.ok(adminService.register(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Admin login")
    public ResponseEntity<Admin> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(adminService.login(dto));
    }


    @PostMapping("/bloodbanks")
    @Operation(summary = "Add new blood bank")
    public ResponseEntity<BloodBank> addBloodBank(@Valid @RequestBody BloodBankDTO dto) {
        return ResponseEntity.ok(bloodBankService.addBloodBank(dto));
    }

    @DeleteMapping("/bloodbanks/{id}")
    @Operation(summary = "Remove blood bank")
    public ResponseEntity<Void> removeBloodBank(@PathVariable String id) {
        bloodBankService.deleteBloodBank(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/bloodbanks/{id}")
    @Operation(summary = "Edit blood bank details")
    public ResponseEntity<BloodBank> editBloodBank(
            @PathVariable String id,
            @Valid @RequestBody BloodBankDTO dto) {
        return ResponseEntity.ok(bloodBankService.editBloodBank(id, dto));
    }

    @PutMapping("/bloodbanks/{id}/inventory")
    @Operation(summary = "Update blood bank inventory")
    public ResponseEntity<BloodBank> updateInventory(
            @PathVariable String id,
            @RequestBody Map<BloodGroup, Integer> inventory) {
        return ResponseEntity.ok(bloodBankService.updateInventory(id, inventory));
    }

    @PutMapping("/appointments/{id}/status")
    @Operation(summary = "Update appointment status")
    public ResponseEntity<Appointment> updateAppointmentStatus(
            @PathVariable String id,
            @RequestParam AppointmentStatus status) {
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }

    @DeleteMapping("/appointments/{id}")
    @Operation(summary = "Delete appointment")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}