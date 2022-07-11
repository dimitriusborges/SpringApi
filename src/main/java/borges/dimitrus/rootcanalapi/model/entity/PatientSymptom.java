package borges.dimitrus.rootcanalapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class PatientSymptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    private Symptom symptomType;


    @NotNull
    private LocalDate reportDate;

    @ManyToOne
    @NotNull
    private Patient patient;

    public PatientSymptom() {
    }

    public PatientSymptom(Long id, Symptom symptomType, LocalDate reportDate, Patient patient) {
        this.id = id;
        this.symptomType = symptomType;
        this.reportDate = reportDate;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Symptom getSymptom() {
        return symptomType;
    }

    public void setSymptom(Symptom symptomType) {
        this.symptomType = symptomType;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
