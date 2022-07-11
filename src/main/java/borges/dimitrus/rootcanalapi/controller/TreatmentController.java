package borges.dimitrus.rootcanalapi.controller;

import borges.dimitrus.rootcanalapi.model.dto.TreatmentDto;
import borges.dimitrus.rootcanalapi.model.entity.Patient;
import borges.dimitrus.rootcanalapi.model.entity.RootFile;
import borges.dimitrus.rootcanalapi.model.entity.Staple;
import borges.dimitrus.rootcanalapi.model.entity.Treatment;
import borges.dimitrus.rootcanalapi.repository.PatientRepository;
import borges.dimitrus.rootcanalapi.repository.RootFileRepository;
import borges.dimitrus.rootcanalapi.repository.StapleRepository;
import borges.dimitrus.rootcanalapi.repository.TreatmentRepository;
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
@RequestMapping("treatments")
public class TreatmentController {

    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RootFileRepository rootFileRepository;

    @Autowired
    StapleRepository stapleRepository;

    @GetMapping
    public ResponseEntity<List<TreatmentDto>> getAllTreatments(){
        List<Treatment> allTreatments = this.treatmentRepository.findAll();

        return ResponseEntity.ok(allTreatments.stream().map(TreatmentDto::new).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<TreatmentDto> getSIngleTreatment(@PathVariable Long id){
        Optional<Treatment> treatment = this.treatmentRepository.findById(id);

        if(treatment.isPresent()){
            return ResponseEntity.ok(new TreatmentDto(treatment.get()));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> insertTreatment(@RequestBody @Valid TreatmentDto treatmentDto){

        Optional<Patient> patient = this.patientRepository.findById(treatmentDto.getPatient().getId());
        Optional<RootFile> rootFile = this.rootFileRepository.findById(treatmentDto.getRootFile().getId());
        Optional<Staple> staple = this.stapleRepository.findById(treatmentDto.getStaple().getId());


        if(patient.isPresent() && rootFile.isPresent() && staple.isPresent()){
            Treatment treatmentNew = treatmentDto.toEntity();

            treatmentNew.setPatient(patient.get());
            treatmentNew.setRootFile(rootFile.get());
            treatmentNew.setStaple(staple.get());

            this.treatmentRepository.save(treatmentNew);

            return new ResponseEntity<>("", HttpStatus.CREATED);

        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<String> updateTreatment(@RequestBody @Valid TreatmentDto treatmentDto, @PathVariable Long id){
        Optional<Treatment> treatmentToUpdate = this.treatmentRepository.findById(id);

        if(treatmentToUpdate.isPresent()){
            Treatment treatmentUpdating = treatmentToUpdate.get();
            Treatment treatmentNewData = treatmentDto.toEntity();

            Optional<Patient> patient = this.patientRepository.findById(treatmentNewData.getPatient().getId());
            Optional<RootFile> rootFile = this.rootFileRepository.findById(treatmentNewData.getRootFile().getId());
            Optional<Staple> staple = this.stapleRepository.findById(treatmentNewData.getStaple().getId());

            if(patient.isPresent() && rootFile.isPresent() && staple.isPresent()){
                treatmentUpdating.setPatient(patient.get());
                treatmentUpdating.setRootFile(rootFile.get());
                treatmentUpdating.setStaple(staple.get());

                treatmentUpdating.setProcedureDate(treatmentNewData.getProcedureDate());
                treatmentUpdating.setTooth(treatmentNewData.getTooth());
                treatmentUpdating.setCanal1(treatmentNewData.getCanal1());
                treatmentUpdating.setCanal2(treatmentNewData.getCanal2());
                treatmentUpdating.setCanal3(treatmentNewData.getCanal3());
                treatmentUpdating.setCanal4(treatmentNewData.getCanal4());
                treatmentUpdating.setCanal5(treatmentNewData.getCanal5());
                treatmentUpdating.setObservation(treatmentNewData.getObservation());

                return ResponseEntity.accepted().build();
            }
            else {
                return ResponseEntity.badRequest().build();
            }

        }
        else {
            return ResponseEntity.noContent().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTreatment(@PathVariable Long id){
        Optional<Treatment> treatment = this.treatmentRepository.findById(id);

        if(treatment.isPresent()){
            this.treatmentRepository.deleteById(id);

            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }
}
