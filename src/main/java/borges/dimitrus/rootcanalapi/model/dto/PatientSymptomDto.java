package borges.dimitrus.rootcanalapi.model.dto;

import borges.dimitrus.rootcanalapi.model.entity.PatientSymptom;

import java.time.LocalDate;

public class PatientSymptomDto {

    private Long id;
    private SymptomDto symptom;
    private LocalDate reportDate;
    private PatientDto patient;

    public PatientSymptomDto() {
    }

    public PatientSymptomDto(PatientSymptom patientSymptom){
        this.id = patientSymptom.getId();
        this.symptom = new SymptomDto(patientSymptom.getSymptom());
        this.reportDate = patientSymptom.getReportDate();
        this.patient = new PatientDto(patientSymptom.getPatient());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SymptomDto getSymptom() {
        return symptom;
    }

    public void setSymptom(SymptomDto symptom) {
        this.symptom = symptom;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public PatientSymptom toEntity(){
        return new PatientSymptom(this.id, this.symptom.toEntity(), this.reportDate, this.patient.toEntity());
    }
}
