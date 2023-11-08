package med.souza.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.souza.api.domain.address.AddressSaveData;

public record PatientSaveData(
    @NotBlank(message = "{name.notblank}")
    String name,
    @NotBlank(message = "{email.notblank}") @Email(message = "{email.invalid}")
    String email,
    @NotBlank(message = "{telephone.notblank}")
    String telephone,
    @NotBlank(message = "{cpf.notblank}") @Pattern(regexp = "\\d{11}", message = "{cpf.invalid}")
    String cpf,
    @NotNull(message = "{address.notnull}") @Valid
    AddressSaveData address) {
}
