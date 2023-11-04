package med.souza.api.patient;

import jakarta.validation.constraints.NotNull;
import med.souza.api.address.AddressSaveData;

public record PatientUpdateData(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressSaveData address) {
}
