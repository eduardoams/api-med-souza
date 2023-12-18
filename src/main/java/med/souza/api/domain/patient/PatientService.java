package med.souza.api.domain.patient;

import med.souza.api.domain.exception.IntegrityValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient findById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElseThrow(() -> new IntegrityValidationException("Nenhum paciente encontrado com o ID " + id));
    }
}
