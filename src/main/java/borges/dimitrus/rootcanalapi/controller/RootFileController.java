package borges.dimitrus.rootcanalapi.controller;

import borges.dimitrus.rootcanalapi.model.dto.RootFIleDto;
import borges.dimitrus.rootcanalapi.model.entity.RootFile;
import borges.dimitrus.rootcanalapi.repository.RootFileRepository;
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
@RequestMapping("/rootfiles")
public class RootFileController {

    @Autowired
    RootFileRepository rootFileRepository;

    @GetMapping
    public ResponseEntity<List<RootFIleDto>> getAllRootFiles(){
        List<RootFile> allRootFiles = rootFileRepository.findAll();

        return ResponseEntity.ok(allRootFiles.stream().map(RootFIleDto::new).collect(Collectors.toList()));

    }

    @GetMapping("{id}")
    public ResponseEntity<RootFIleDto> getSingleRootFile(@PathVariable Long id){
        Optional<RootFile> rootFile = rootFileRepository.findById(id);

        if(rootFile.isPresent()){
            return ResponseEntity.ok(new RootFIleDto(rootFile.get()));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> insertNewRootFile(@RequestBody @Valid RootFIleDto newRootFileDto){

        rootFileRepository.save(newRootFileDto.toEntinty());

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<String> updateRootFile(@RequestBody @Valid RootFIleDto rootFileNewDataDto, @PathVariable Long id){

        Optional<RootFile> rootFileToUpdate = rootFileRepository.findById(id);

        if(rootFileToUpdate.isPresent()){
            RootFile rootFileUpdating = rootFileToUpdate.get();
            RootFile  rootFileNewData = rootFileNewDataDto.toEntinty();

            rootFileUpdating.setTypeName(rootFileNewData.getTypeName());

            String brand = rootFileUpdating.getBrand();
            if(brand != null){
                rootFileUpdating.setBrand(rootFileNewData.getBrand());
            }

            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.noContent().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRootFile(@PathVariable Long id){
        Optional<RootFile> rootFileToDelete = rootFileRepository.findById(id);

        if(rootFileToDelete.isPresent()){
            rootFileRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }
}
