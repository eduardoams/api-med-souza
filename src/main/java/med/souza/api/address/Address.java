package med.souza.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String publicPlace;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String uf;
    private String cep;

    public Address(AddressSaveData data) {
        this.publicPlace = data.publicPlace();
        this.number = data.number();
        this.complement = data.complement();
        this.neighborhood = data.neighborhood();
        this.city = data.city();
        this.uf = data.uf();
        this.cep = data.cep();
    }

    public void update(AddressSaveData data) {
        if (data.publicPlace() != null) {
            this.publicPlace = data.publicPlace();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
        if (data.complement() != null) {
            this.complement = data.complement();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.uf() != null) {
            this.uf = data.uf();
        }
        if (data.cep() != null) {
            this.cep = data.cep();
        }
    }
}
