package med.souza.api.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressSaveData(
        @NotBlank
        String publicPlace,
        String number,
        String complement,
        @NotBlank
        String neighborhood,
        @NotBlank
        String city,
        @NotBlank
        String uf,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep) {
}
