package borges.dimitrus.rootcanalapi.model.dto;

import borges.dimitrus.rootcanalapi.model.entity.Patient;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class PatientDto {

    @NotNull
    private String name;

    @NotNull
    private LocalDate birthdate;


    public PatientDto() {
    }

    public PatientDto(Patient patient) {
        this.name = patient.getName();
        this.birthdate = patient.getBirthdate();
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
        return new Patient(this.birthdate, this.name);
    }

    public static PatientDto fromEntity(Patient patient){
        return new PatientDto(patient);
    }

    @Override
    public String toString() {
        return "PatientDto{" +
                "birthDate=" + birthdate +
                ", name='" + name + '\'' +
                '}';
    }
}
