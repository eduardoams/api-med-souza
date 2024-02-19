package med.souza.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.souza.api.domain.consultation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultations")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultationDetailingData> scheduleAppointment(@RequestBody @Valid ConsultationSaveData data) {
        ConsultationDetailingData dto = consultationService.toSchedule(data);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelAppointment(@RequestBody @Valid ConsultationCancelData data) {
        consultationService.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
