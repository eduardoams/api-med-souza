package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.Consultation;
import med.souza.api.domain.consultation.ConsultationCancelData;

public interface CancellationValidationInterface {

    void validate(ConsultationCancelData data, Consultation consultation);
}
