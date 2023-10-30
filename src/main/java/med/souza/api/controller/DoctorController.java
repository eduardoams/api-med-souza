package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.doctor.Doctor;
import med.souza.api.doctor.DoctorRepository;
import med.souza.api.doctor.DoctorSaveData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}