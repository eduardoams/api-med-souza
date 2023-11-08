package med.souza.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressSaveData(
        @NotBlank(message = "{publicPlace.notblank}")
        String publicPlace,
        String number,
        String complement,
        @NotBlank(message = "{neighborhood.notblank}")
        String neighborhood,
        @NotBlank(message = "{city.notblank}")
        String city,
        @NotBlank(message = "{uf.notblank}")
        String uf,
        @NotBlank(message = "{cep.notblank}") @Pattern(regexp = "\\d{8}", message = "{cep.invalid}")
        String cep) {
}
