package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.ConsultationSaveData;
import med.souza.api.domain.doctor.DoctorRepository;
import med.souza.api.domain.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InactiveDoctor implements ValidationInterface {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Verifica se o médico está inativo.
     */
    public void validate(ConsultationSaveData data) {
        //Escolha de médico opcional
        if (data.idDoctor() == null) {
            return;
        }

        boolean isActive = doctorRepository.findActiveById(data.idDoctor());

        if (!isActive) {
            throw new ValidationException("Não é possível agendar consulta com um médico inativo");
        }
    }
}
