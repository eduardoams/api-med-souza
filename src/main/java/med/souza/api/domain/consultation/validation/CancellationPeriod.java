package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.Consultation;
import med.souza.api.domain.consultation.ConsultationCancelData;
import med.souza.api.domain.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancellationPeriod implements CancellationValidationInterface {

    @Override
    public void validate(ConsultationCancelData data, Consultation consultation) {
        Duration advanceTime = Duration.between(LocalDateTime.now(), consultation.getDate());

        int hours = 24;

        if (advanceTime.toMinutes() < (hours * 60)) {
            throw new ValidationException("Não é possível cancelar uma consulta com menos de " + hours + " horas de antecedência");
        }
    }
}
