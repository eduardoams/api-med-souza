package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.ConsultationSaveData;
import med.souza.api.domain.exception.ValidationException;
import med.souza.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InactivePatient implements ValidationInterface {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Verifica se o paciente está inativo.
     */
    public void validate(ConsultationSaveData data) {
        boolean isActive = patientRepository.findActiveById(data.idPatient());

        if (!isActive) {
            throw new ValidationException("Não é possível agendar consulta com um paciente inativo");
        }
    }
}
