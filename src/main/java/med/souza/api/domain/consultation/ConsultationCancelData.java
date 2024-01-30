package med.souza.api.domain.consultation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsultationCancelData(
        @NotNull
        Long id,
        @NotNull(message = "{reason.notnull}")
        ReasonEnum reason) {
}
