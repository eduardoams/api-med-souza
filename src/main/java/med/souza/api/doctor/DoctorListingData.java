package med.souza.api.doctor;

public record DoctorListingData(Long id, String name, String email, String crm, SpecialtyEnum specialty) {

    public DoctorListingData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
