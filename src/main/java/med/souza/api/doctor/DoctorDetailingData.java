package med.souza.api.doctor;

import med.souza.api.address.Address;

public record DoctorDetailingData(Long id, String name, String email, String crm, String telephone, SpecialtyEnum specialty, Address address) {

    public DoctorDetailingData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getTelephone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
