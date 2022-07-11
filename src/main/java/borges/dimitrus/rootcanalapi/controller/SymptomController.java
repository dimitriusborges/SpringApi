package borges.dimitrus.rootcanalapi.controller;

import borges.dimitrus.rootcanalapi.model.dto.SymptomDto;
import borges.dimitrus.rootcanalapi.model.entity.Symptom;
import borges.dimitrus.rootcanalapi.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("symptoms")
public class SymptomController {

    @Autowired
    SymptomRepository symptomRepository;

    @GetMapping
    public ResponseEntity<List<SymptomDto>> getAllSymptoms(){
        List<Symptom> allSymptoms = symptomRepository.findAll();

        return ResponseEntity.ok(
                allSymptoms.stream().map(SymptomDto::new).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<SymptomDto> getSingleSymptom(@PathVariable Long id){
        Optional<Symptom> symptom = symptomRepository.findById(id);

        if(symptom.isPresent()){
            return ResponseEntity.ok(new SymptomDto(symptom.get()));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> inserNewSymptom(@RequestBody @Valid SymptomDto symptomDto){
        symptomRepository.save(symptomDto.toEntity());

        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<String> updateSymptom(@RequestBody @Valid SymptomDto symptomNewDataDto, @PathVariable Long id){
        Optional<Symptom> symptomToUdate = symptomRepository.findById(id);

        if(symptomToUdate.isPresent()){
            Symptom symptomUpdating = symptomToUdate.get();
            Symptom symptomNewData = symptomNewDataDto.toEntity();

            symptomUpdating.setDescription(symptomNewData.getDescription());

            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSymptom(@PathVariable long id){
        Optional<Symptom> symptomToDelete = symptomRepository.findById(id);

        if(symptomToDelete.isPresent()){
            symptomRepository.deleteById(id);

            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
}
