package borges.dimitrus.rootcanalapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class PatientSymptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Symptom symptom;


    @NotNull
    private LocalDate reportDate;

    @ManyToOne
    @NotNull
    private Patient patient;

    public PatientSymptom() {
    }

    public PatientSymptom(Long id, Symptom symptom, LocalDate reportDate, Patient patient) {
        this.id = id;
        this.symptom = symptom;
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
        return symptom;
    }

    public void setSymptom(Symptom symptomType) {
        this.symptom = symptomType;
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
