package borges.dimitrus.rootcanalapi.model.dto;

import borges.dimitrus.rootcanalapi.model.entity.Symptom;

import javax.validation.constraints.NotNull;

public class SymptomDto {

    private Long id;
    @NotNull
    private String description;

    public SymptomDto() {
    }

    public SymptomDto(Symptom symptom) {
        this.id = symptom.getId();
        this.description = symptom.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Symptom toEntity(){
        return new Symptom(this.id, this.description);
    }

}
