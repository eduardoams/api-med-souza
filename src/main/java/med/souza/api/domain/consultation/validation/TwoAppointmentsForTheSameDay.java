package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.ConsultationRepository;
import med.souza.api.domain.consultation.ConsultationSaveData;
import med.souza.api.domain.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TwoAppointmentsForTheSameDay implements ValidationInterface {

    @Autowired
    private ConsultationRepository consultationRepository;

    /**
     * Verifica se o paciente possui consulta agendada no dia.
     */
    public void validate(ConsultationSaveData data) {
        LocalDateTime firstTime = data.date().withHour(7);
        LocalDateTime endTime = data.date().withHour(18);
        boolean hasConsultation = consultationRepository.existsByPatientIdAndDateBetween(data.idPatient(), firstTime, endTime);

        if (hasConsultation) {
            throw new ValidationException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
