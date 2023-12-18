package med.souza.api.domain.consultation;

import java.time.LocalDateTime;

public record ConsultationDetailingData(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
}
