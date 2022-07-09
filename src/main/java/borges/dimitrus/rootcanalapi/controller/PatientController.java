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
@RequestMapping("/patients")
public class PatientController {


    @Autowired
    private PatientRepository patientRepository;

    @GetMapping()
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        List<Patient> allPatients = patientRepository.findAll();

        return ResponseEntity.ok(allPatients.stream().map(PatientDto::new).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientDto> getSinglePatient(@PathVariable Long id){
        Optional<Patient> patient = patientRepository.findById(id);

        if(patient.isPresent()){
            return ResponseEntity.ok(new PatientDto(patient.get()));
        }
        else{
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping
    public ResponseEntity<String> insertNewPatient(@RequestBody @Valid PatientDto patientToInsert){

        patientRepository.save(patientToInsert.toEntity());

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Transactional
    public  ResponseEntity<String> updatePatient(@RequestBody @Valid PatientDto patientNewDataDto, @PathVariable Long id){
        Optional<Patient> patientToUpdate = patientRepository.findById(id);

        if(patientToUpdate.isPresent()){

            Patient patientNewData = patientNewDataDto.toEntity();
            Patient patientUpdating = patientToUpdate.get();

            patientUpdating.setBirthdate(patientNewData.getBirthdate());
            patientUpdating.setName(patientNewData.getName());

            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){

        Optional<Patient> patientDoDelete = patientRepository.findById(id);

        if(patientDoDelete.isPresent()){
            patientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }

}
