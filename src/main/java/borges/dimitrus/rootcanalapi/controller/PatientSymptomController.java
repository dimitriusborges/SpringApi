package borges.dimitrus.rootcanalapi.controller;

import borges.dimitrus.rootcanalapi.model.dto.PatientSymptomDto;
import borges.dimitrus.rootcanalapi.model.entity.Patient;
import borges.dimitrus.rootcanalapi.model.entity.PatientSymptom;
import borges.dimitrus.rootcanalapi.model.entity.Symptom;
import borges.dimitrus.rootcanalapi.repository.PatientRepository;
import borges.dimitrus.rootcanalapi.repository.PatientSymptomRepository;
import borges.dimitrus.rootcanalapi.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("patientsymptoms")
public class PatientSymptomController {

    @Autowired
    PatientSymptomRepository patientSymptomRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    SymptomRepository symptomRepository;

    @GetMapping
    public ResponseEntity<List<PatientSymptomDto>> getAllPatientsSymptoms(){
        List<PatientSymptom> allPatientSymptoms = this.patientSymptomRepository.findAll();

        return ResponseEntity.ok(allPatientSymptoms.stream().map(PatientSymptomDto::new).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientSymptomDto> getByPatientSymptomId(@PathVariable Long id){
        Optional<PatientSymptom> patientSymptom = this.patientSymptomRepository.findById(id);

        if(patientSymptom.isPresent()){
            return ResponseEntity.ok(new PatientSymptomDto (patientSymptom.get()));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> insertPatientSymptom(@RequestBody @Valid PatientSymptomDto patientSymptomDto){

        Optional<Patient> patient = this.patientRepository.findById(patientSymptomDto.getPatient().getId());
        Optional<Symptom> symptom = this.symptomRepository.findById(patientSymptomDto.getSymptom().getId());

        if(patient.isPresent() && symptom.isPresent()){

            PatientSymptom patientSymptom = patientSymptomDto.toEntity();

            patientSymptom.setPatient(patient.get());
            patientSymptom.setSymptom(symptom.get());

            this.patientSymptomRepository.save(patientSymptom);

            return new ResponseEntity<>("", HttpStatus.CREATED);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<String> updatePatientSymptom(@RequestBody @Valid PatientSymptomDto patientSymptomDto, @PathVariable Long id) {

        Optional<PatientSymptom> patientSymptomToUpdate = this.patientSymptomRepository.findById(id);

        if (patientSymptomToUpdate.isPresent()) {

            PatientSymptom patientSymptomUpdating = patientSymptomToUpdate.get();
            PatientSymptom patientSymptomNewData = patientSymptomDto.toEntity();

            patientSymptomUpdating.setReportDate(patientSymptomNewData.getReportDate());

            patientSymptomUpdating.setPatient(patientSymptomNewData.getPatient());
            patientSymptomUpdating.setSymptom(patientSymptomNewData.getSymptom());

            return ResponseEntity.accepted().build();

        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatientSymptom(@PathVariable Long id){
        Optional<PatientSymptom> patientSymptomToDelete = this.patientSymptomRepository.findById(id);

        if(patientSymptomToDelete.isPresent()){
            this.patientSymptomRepository.deleteById(id);

            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
}
