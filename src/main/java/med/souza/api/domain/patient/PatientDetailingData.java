package med.souza.api.domain.patient;

import med.souza.api.domain.address.Address;

public record PatientDetailingData(Long id, String name, String email, String cpf, String telephone, Address address) {

    public PatientDetailingData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getTelephone(), patient.getAddress());
    }
}
