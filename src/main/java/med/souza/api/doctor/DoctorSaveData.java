package med.souza.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.souza.api.address.AddressSaveData;

public record DoctorSaveData(
        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank
        String telephone,
        @NotBlank @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        SpecialtyEnum specialty,
        @NotNull @Valid
        AddressSaveData address) {
}
