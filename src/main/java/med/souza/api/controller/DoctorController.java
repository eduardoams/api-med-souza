package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void save(@RequestBody @Valid DoctorSaveData data) {
        repository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DoctorListingData> list(@PageableDefault(size = 1, sort = {"name"}) Pageable p) {
        return repository.findAllByActiveTrue(p).map(DoctorListingData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateData data) {
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.delete();
    }
}
