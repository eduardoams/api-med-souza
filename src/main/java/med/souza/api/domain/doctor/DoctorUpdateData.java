package med.souza.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.souza.api.domain.address.AddressSaveData;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressSaveData address) {
}
