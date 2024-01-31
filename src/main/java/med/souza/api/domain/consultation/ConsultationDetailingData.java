package med.souza.api.domain.consultation;

import java.time.LocalDateTime;

public record ConsultationDetailingData(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
    public ConsultationDetailingData(Consultation consultation) {
        this(consultation.getId(), consultation.getDoctor().getId(), consultation.getPatient().getId(), consultation.getDate());
    }
}
