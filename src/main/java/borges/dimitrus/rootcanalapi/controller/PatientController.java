package borges.dimitrus.rootcanalapi.controller;


import borges.dimitrus.rootcanalapi.model.dto.PatientDto;
import borges.dimitrus.rootcanalapi.model.entity.Patient;
import borges.dimitrus.rootcanalapi.repository.PatientRepository;
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
@RequestMapping("/canal_api/patients")
public class PatientController {


    @Autowired
    private PatientRepository patientRepository;

    @GetMapping()
    public ResponseEntity<List<PatientDto>> getPatientsList(){
        List<Patient> allPatients = patientRepository.findAll();

        return ResponseEntity.ok(allPatients.stream().map(PatientDto::fromEntity).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientDto> getSinglePatient(@PathVariable Long id){
        Optional<Patient> patient = patientRepository.findById(id);

        if(patient.isPresent()){
            return ResponseEntity.ok(PatientDto.fromEntity(patient.get()));
        }
        else{
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping
    public ResponseEntity<String> insertNewPatient(@RequestBody @Valid PatientDto patientToInsert){

        patientRepository.save(patientToInsert.toEntity());

        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    @Transactional
    public  ResponseEntity<String> updatePatient(@RequestBody PatientDto newData, @PathVariable Long id){
        Optional<Patient> patientToUpdate = patientRepository.findById(id);

        if(patientToUpdate.isPresent()){

            Patient patientNewData = newData.toEntity();
            Patient patientUpdating = patientToUpdate.get();

            patientUpdating.setBirthdate(patientNewData.getBirthdate());
            patientUpdating.setName(patientNewData.getName());

            return new ResponseEntity<>("", HttpStatus.ACCEPTED);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){

        Optional<Patient> patientDoDelete = patientRepository.findById(id);

        if(patientDoDelete.isPresent()){
            Patient patient = patientDoDelete.get();

            patientRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }

}
