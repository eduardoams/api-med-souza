package med.souza.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.souza.api.domain.doctor.SpecialtyEnum;

import java.time.LocalDateTime;

public record ConsultationSaveData(
        Long idDoctor,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date,

        SpecialtyEnum specialty) {
}
