package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.ConsultationSaveData;
import med.souza.api.domain.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OpeningHours implements SchedulingValidationInterface {

    /**
     * Verifica se a consulta está sendo agendada dentro do horário de funcionamento.
     */
    @Override
    public void validate(ConsultationSaveData data) {
        int startTime = 7;
        int endTime = 19;
        int fixedConsultationDuration = 1;

        boolean isSunday = data.date().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean isBefore = data.date().getHour() < startTime;
        boolean isAfter = data.date().getHour() > (endTime - fixedConsultationDuration);
        System.out.println(isSunday);
        System.out.println(isBefore);
        System.out.println(isAfter);

        if (isSunday || isBefore || isAfter) {
            String messageException = "O horário de funcionamento da clínica é de segunda à sábado entre " + startTime + "h e " + endTime + "h";
            throw new ValidationException(messageException);
        }
    }
}
