package med.souza.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.souza.api.domain.address.AddressSaveData;

public record DoctorSaveData(
        @NotBlank(message = "{name.notblank}")
        String name,
        @NotBlank(message = "{email.notblank}") @Email(message = "{email.invalid}")
        String email,
        @NotBlank(message = "{telephone.notblank}")
        String telephone,
        @NotBlank(message = "{crm.notblank}") @Pattern(regexp = "\\d{4,6}", message = "{crm.invalid}")
        String crm,
        @NotNull(message = "{specialty.notnull}")
        SpecialtyEnum specialty,
        @NotNull(message = "{address.notnull}") @Valid
        AddressSaveData address) {
}
