package borges.dimitrus.rootcanalapi.controller;

import borges.dimitrus.rootcanalapi.model.dto.StapleDto;
import borges.dimitrus.rootcanalapi.model.entity.Staple;
import borges.dimitrus.rootcanalapi.repository.StapleRepository;
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
@RequestMapping("/staples")
public class StapleController {

    @Autowired
    private StapleRepository stapleRepository;

    @GetMapping
    public ResponseEntity<List<StapleDto>> getAllStaples(){
        List<Staple> allStaples = stapleRepository.findAll();

        return ResponseEntity.ok(allStaples.stream().map(StapleDto::new).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<StapleDto> getSingleStale(@PathVariable Long id){
        Optional<Staple> staple = stapleRepository.findById(id);

        if(staple.isPresent()){
            return ResponseEntity.ok(new StapleDto(staple.get()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> insertNewStaple(@RequestBody @Valid StapleDto stapleToInsert){
        stapleRepository.save(stapleToInsert.toEntity());

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<String> updateStaple(@RequestBody @Valid StapleDto stapleNewDataDto, @PathVariable Long id){
        Optional<Staple> stapleToUpdate = stapleRepository.findById(id);

        if(stapleToUpdate.isPresent()){
            Staple stapleUpdating = stapleToUpdate.get();
            Staple stapleNewData = stapleNewDataDto.toEntity();

            stapleUpdating.setType(stapleNewData.getType());

            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStaple(@PathVariable Long id){
        Optional<Staple> stapleToDelete = stapleRepository.findById(id);

        if(stapleToDelete.isPresent()){
            stapleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
}
