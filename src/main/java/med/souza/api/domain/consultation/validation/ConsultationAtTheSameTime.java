package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.ConsultationRepository;
import med.souza.api.domain.consultation.ConsultationSaveData;
import med.souza.api.domain.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultationAtTheSameTime implements ValidationInterface {

    @Autowired
    private ConsultationRepository consultationRepository;

    /**
     * Verifica se já existe uma consulta agendada no horário.
     */
    public void validate(ConsultationSaveData data) {
        boolean hasConsultation = consultationRepository.existsByDoctorIdAndDate(data.idDoctor(), data.date());

        if (hasConsultation) {
            throw new ValidationException("Já existe uma consulta agendada neste horário");
        }
    }
}
