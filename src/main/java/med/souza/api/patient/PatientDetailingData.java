package med.souza.api.patient;

import med.souza.api.address.Address;
import med.souza.api.patient.Patient;

public record PatientDetailingData(Long id, String name, String email, String cpf, String telephone, Address address) {

    public PatientDetailingData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getTelephone(), patient.getAddress());
    }
}
