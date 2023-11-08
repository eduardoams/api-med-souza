package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDetailingData> save(@RequestBody @Valid PatientSaveData data, UriComponentsBuilder ucb) {
        Patient patient = new Patient(data);
        repository.save(patient);

        URI uri = ucb.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailingData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListingData>> list(Pageable p) {
        return ResponseEntity.ok(repository.findAllByActiveTrue(p).map(PatientListingData::new));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDetailingData> detail(@PathVariable Long id) {
        Patient patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailingData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDetailingData> update(@RequestBody @Valid PatientUpdateData data) {
        Patient patient = repository.getReferenceById(data.id());
        patient.update(data);
        return ResponseEntity.ok(new PatientDetailingData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Patient patient = repository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }
}
