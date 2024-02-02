package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.ConsultationSaveData;
import med.souza.api.domain.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class MinimumAdvanceMinutes implements SchedulingValidationInterface {

    /**
     * Verifica se a consulta está respeitando o tempo mínimo de antecedência.
     */
    @Override
    public void validate(ConsultationSaveData data) {
        int minimumAdvanceMinutes = 30;
        LocalDateTime consultationDate = data.date();
        LocalDateTime now = LocalDateTime.now();
        long differenceMinutes = Duration.between(now, consultationDate).toMinutes();

        if (differenceMinutes < minimumAdvanceMinutes) {
            String messageException = "Não é possível agendar uma consulta com menos de " + minimumAdvanceMinutes + " minutos de antecedência";
            throw new ValidationException(messageException);
        }
    }
}
