package med.souza.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.souza.api.domain.address.AddressSaveData;

public record PatientUpdateData(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressSaveData address) {
}
