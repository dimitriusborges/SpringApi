package borges.dimitrus.rootcanalapi.model.dto;

import borges.dimitrus.rootcanalapi.model.entity.Patient;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class PatientDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate birthdate;


    public PatientDto() {
    }

    public PatientDto(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.birthdate = patient.getBirthdate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    public Patient toEntity(){
        return new Patient(this.id, this.birthdate, this.name);
    }

    @Override
    public String toString() {
        return "PatientDto{" +
                "birthDate=" + birthdate +
                ", name='" + name + '\'' +
                '}';
    }
}
