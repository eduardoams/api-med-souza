package med.souza.api.domain.consultation.validation;

import med.souza.api.domain.consultation.ConsultationSaveData;

public interface ValidationInterface {

    void validate(ConsultationSaveData data);
}
