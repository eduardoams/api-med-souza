package med.souza.api.domain.consultation;

import med.souza.api.domain.doctor.Doctor;
import med.souza.api.domain.doctor.DoctorRepository;
import med.souza.api.domain.doctor.DoctorService;
import med.souza.api.domain.exception.IntegrityValidationException;
import med.souza.api.domain.patient.Patient;
import med.souza.api.domain.patient.PatientRepository;
import med.souza.api.domain.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    public void toSchedule(ConsultationSaveData data) {

        Patient patient = patientService.findById(data.idPatient());

        Doctor doctor = chooseAvailableDoctor(data);
        System.out.println("teste teste teste teste teste");
        System.out.println(doctor);


        /*Consultation consultation = new Consultation(null, doctor, patient, data.date());

        consultationRepository.save(consultation);*/
    }

    public Doctor chooseAvailableDoctor(ConsultationSaveData data) {
        if (data.idDoctor() != null) {
            return doctorService.findById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new IntegrityValidationException("Especialidade obrigatória quando médico não é escolhido");
        }

        return doctorService.chooseAvailableDoctor(data.specialty(), data.date());
    }

    public void cancel(ConsultationCancelData data) {
        Consultation consultation = findByIdAndActiveTrue(data.id());
        Duration advanceTime = Duration.between(LocalDateTime.now(), consultation.getDate());

        if (advanceTime.toMinutes() > 1440) {
            consultation.cancel(data);
        } else {
            throw new RuntimeException("Não é possível cancelar uma consulta com menos de 24 horas de antecedência");
        }
    }

    public Consultation findByIdAndActiveTrue(Long id) {
        Optional<Consultation> consultation = consultationRepository.findByIdAndActiveTrue(id);
        return consultation.orElseThrow(() -> new IntegrityValidationException("Nenhuma consulta em aberto encontrada com o ID " + id));
    }
}
