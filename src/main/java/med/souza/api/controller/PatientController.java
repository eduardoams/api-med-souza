package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void save(@RequestBody @Valid PatientSaveData data) {
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<PatientListingData> list(Pageable p) {
        return repository.findAllByActiveTrue(p).map(PatientListingData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientUpdateData data) {
        Patient patient = repository.getReferenceById(data.id());
        patient.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Patient patient = repository.getReferenceById(id);
        patient.delete();
    }
}
