package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.domain.consultation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService appointmentScheduleService;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultationDetailingData> scheduleAppointment(@RequestBody @Valid ConsultationSaveData data) {
        appointmentScheduleService.toSchedule(data);
        return ResponseEntity.ok(new ConsultationDetailingData(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelAppointment(@RequestBody @Valid ConsultationCancelData data) {
        appointmentScheduleService.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
