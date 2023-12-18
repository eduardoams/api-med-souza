package med.souza.api.domain.doctor;

import med.souza.api.domain.exception.IntegrityValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Doctor findById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElseThrow(() -> new IntegrityValidationException("Nenhum médico encontrado com o ID " + id));
    }

    public Doctor chooseAvailableDoctor(SpecialtyEnum specialty, LocalDateTime date) {
        return doctorRepository.chooseAvailableDoctor(specialty, date);
    }
}
