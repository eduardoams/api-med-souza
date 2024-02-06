package med.souza.api.domain.doctor;

import med.souza.api.domain.address.AddressSaveData;
import med.souza.api.domain.consultation.Consultation;
import med.souza.api.domain.consultation.ConsultationRepository;
import med.souza.api.domain.patient.Patient;
import med.souza.api.domain.patient.PatientRepository;
import med.souza.api.domain.patient.PatientSaveData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    //Próxima segunda-feira às 15h
    private static final LocalDateTime consultationDate = LocalDate.now()
            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            .atTime(15, 0);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Test
    @DisplayName("Deve devolver null quando único médico cadastrado não está disponível na data")
    void chooseAvailableDoctor1() {
        //Given
        SpecialtyEnum specialty = SpecialtyEnum.CARDIOLOGIA;
        Doctor doctor = registerDoctor("César Sampaio", "cesar@medsouza.com", specialty);
        Patient patient = registerPatient("José Francisco", "jose@gmail.com", "45356439032");
        registerConsultation(doctor, patient, consultationDate);

        //When
        Doctor freeDoctor = doctorRepository.chooseAvailableDoctor(specialty, consultationDate);

        //Then
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deve devolver o médico quando for o único cadastrado e estiver disponível na data")
    void chooseAvailableDoctor2() {
        //Given
        SpecialtyEnum specialty = SpecialtyEnum.CARDIOLOGIA;
        Doctor doctor = registerDoctor("César Sampaio", "cesar@medsouza.com", specialty);

        //When
        Doctor freeDoctor = doctorRepository.chooseAvailableDoctor(specialty, consultationDate);

        //Then
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    @Test
    @DisplayName("Deve devolver null quando não há médico cadastrado")
    void chooseAvailableDoctor3() {
        //When
        Doctor freeDoctor = doctorRepository.chooseAvailableDoctor(SpecialtyEnum.CARDIOLOGIA, consultationDate);

        //Then
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deve devolver null quando há médico cadastrado, porém, inativo")
    void chooseAvailableDoctor4() {
        //Given
        SpecialtyEnum specialty = SpecialtyEnum.CARDIOLOGIA;
        Doctor doctor = registerDoctor("César Sampaio", "cesar@medsouza.com", specialty);
        deleteDoctor(doctor.getId());

        //When
        Doctor freeDoctor = doctorRepository.chooseAvailableDoctor(specialty, consultationDate);

        //Then
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deve devolver o médico quando está disponível em outro horário do mesmo dia")
    void chooseAvailableDoctor5() {
        //Given
        //Próxima segunda-feira às 14h
        LocalDateTime consultationDateJose = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(14, 0);

        SpecialtyEnum specialty = SpecialtyEnum.DERMATOLOGIA;
        Doctor doctor = registerDoctor("César Sampaio", "cesar@medsouza.com", specialty);

        Patient patientJose = registerPatient("José Francisco", "jose@gmail.com", "45356439032");
        registerConsultation(doctor, patientJose, consultationDateJose);

        registerPatient("Yuri Martins", "yuri@gmail.com", "45356439078");

        //When
        Doctor freeDoctor = doctorRepository.chooseAvailableDoctor(specialty, consultationDate);

        //Then
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private Doctor registerDoctor(String name, String email, SpecialtyEnum specialty) {
        DoctorSaveData data = dataDoctor(name, email, specialty);
        Doctor doctor = new Doctor(data);
        return doctorRepository.save(doctor);
    }

    private Patient registerPatient(String name, String email, String cpf) {
        PatientSaveData data = dataPatient(name, email, cpf);
        Patient patient = new Patient(data);
        return patientRepository.save(patient);
    }

    private void registerConsultation(Doctor doctor, Patient patient, LocalDateTime date) {
        Consultation consultation = new Consultation(null, doctor, patient, date, null, true);
        consultationRepository.save(consultation);
    }

    private AddressSaveData dataAddress() {
        return new AddressSaveData(
                "Rua do Maracujá",
                "123",
                null,
                "Jardim Morango",
                "Aracaju",
                "SE",
                "49042010"
        );
    }

    private DoctorSaveData dataDoctor(String name, String email, SpecialtyEnum specialty) {
        return new DoctorSaveData(name, email, "11999832129", "32987", specialty, dataAddress());
    }

    private PatientSaveData dataPatient(String name, String email, String cpf) {
        return new PatientSaveData(name, email, "11952920203", cpf, dataAddress());
    }

    @Transactional
    protected void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
    }
}