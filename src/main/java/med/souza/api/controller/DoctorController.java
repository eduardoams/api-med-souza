package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailingData> save(@RequestBody @Valid DoctorSaveData data, UriComponentsBuilder ucb) {
        Doctor doctor = new Doctor(data);
        repository.save(doctor);

        URI uri = ucb.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailingData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListingData>> list(@PageableDefault(size = 1, sort = {"name"}) Pageable p) {
        return ResponseEntity.ok(repository.findAllByActiveTrue(p).map(DoctorListingData::new));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDetailingData> detail(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailingData(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailingData> update(@RequestBody @Valid DoctorUpdateData data) {
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.update(data);
        return ResponseEntity.ok(new DoctorDetailingData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }
}
