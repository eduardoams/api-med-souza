package med.souza.api.domain.doctor;

import med.souza.api.domain.address.AddressSaveData;
import med.souza.api.domain.consultation.Consultation;
import med.souza.api.domain.consultation.ConsultationRepository;
import med.souza.api.domain.consultation.ConsultationSaveData;
import med.souza.api.domain.patient.Patient;
import med.souza.api.domain.patient.PatientRepository;
import med.souza.api.domain.patient.PatientSaveData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

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
        //Próxima segunda-feira às 15h
        LocalDateTime date = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(15, 0);

        SpecialtyEnum specialty = SpecialtyEnum.CARDIOLOGIA;
        Doctor doctor = registerDoctor("César Sampaio", "cesar@medsouza.com", specialty);
        Patient patient = registerPatient("José Francisco", "jose@gmail.com", "45356439032");
        registerConsultation(doctor, patient, date);

        //When
        Doctor freeDoctor = doctorRepository.chooseAvailableDoctor(specialty, date);

        //Then
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deve devolver o médico quando for o único cadastrado e estiver disponível na data")
    void chooseAvailableDoctor2() {
        //Given
        //Próxima segunda-feira às 15h
        LocalDateTime date = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(15, 0);

        SpecialtyEnum specialty = SpecialtyEnum.CARDIOLOGIA;
        Doctor doctor = registerDoctor("César Sampaio", "cesar@medsouza.com", specialty);

        //When
        Doctor freeDoctor = doctorRepository.chooseAvailableDoctor(specialty, date);

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
        return new PatientSaveData(name, email, "11952920203", "31718053088", dataAddress());
    }

    private ConsultationSaveData dataConsultation(Long idDoctor, Long idPatient, LocalDateTime date, SpecialtyEnum specialty) {
        return new ConsultationSaveData(idDoctor, idPatient, date, specialty);
    }
}