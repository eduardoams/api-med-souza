package med.souza.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.souza.api.address.Address;
import med.souza.api.address.AddressSaveData;

@Entity
@Table(name = "tb_doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String crm;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private SpecialtyEnum specialty;

    @Embedded
    private Address address;

    public Doctor (DoctorSaveData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public void update(DoctorUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.telephone() != null) {
            this.telephone = data.telephone();
        }
        if (data.address() != null) {
            this.address.update(data.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
