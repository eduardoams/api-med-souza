package med.souza.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.souza.api.address.AddressSaveData;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressSaveData address) {
}
