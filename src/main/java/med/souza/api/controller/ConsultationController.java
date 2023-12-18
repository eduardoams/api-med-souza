package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.domain.consultation.ConsultationService;
import med.souza.api.domain.consultation.ConsultationDetailingData;
import med.souza.api.domain.consultation.ConsultationSaveData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
