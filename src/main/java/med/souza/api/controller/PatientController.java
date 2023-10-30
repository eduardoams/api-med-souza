package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.patient.Patient;
import med.souza.api.patient.PatientRepository;
import med.souza.api.patient.PatientSaveData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
