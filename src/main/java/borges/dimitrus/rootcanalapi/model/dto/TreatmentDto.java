package borges.dimitrus.rootcanalapi.model.dto;

import borges.dimitrus.rootcanalapi.model.entity.Treatment;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TreatmentDto {

    private long id;

    @NotNull
    private LocalDate procedureDate;

    @NotNull
    private PatientDto patient;

    @NotNull
    private RootFIleDto rootFile;

    @NotNull
    private StapleDto staple;

    @NotNull
    @Max(32)
    @Min(1)
    private Integer tooth;

    private Integer canal1;
    private Integer canal2;
    private Integer canal3;
    private Integer canal4;
    private Integer canal5;

    private String observation;

    public TreatmentDto() {
    }

    public TreatmentDto(Treatment treatment) {

        this.id = treatment.getId();
        this.procedureDate = treatment.getProcedureDate();
        this.patient = new PatientDto(treatment.getPatient());
        this.rootFile = new RootFIleDto(treatment.getRootFile());
        this.staple = new StapleDto(treatment.getStaple());
        this.tooth = treatment.getTooth();
        this.canal1 = treatment.getCanal1();
        this.canal2 = treatment.getCanal2();
        this.canal3 = treatment.getCanal3();
        this.canal4 = treatment.getCanal4();
        this.canal5 = treatment.getCanal5();
        this.observation = treatment.getObservation();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getProcedureDate() {
        return procedureDate;
    }

    public void setProcedureDate(LocalDate procedureDate) {
        this.procedureDate = procedureDate;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public RootFIleDto getRootFile() {
        return rootFile;
    }

    public void setRootFile(RootFIleDto rootFile) {
        this.rootFile = rootFile;
    }

    public StapleDto getStaple() {
        return staple;
    }

    public void setStaple(StapleDto staple) {
        this.staple = staple;
    }

    public Integer getTooth() {
        return tooth;
    }

    public void setTooth(Integer tooth) {
        this.tooth = tooth;
    }

    public Integer getCanal1() {
        return canal1;
    }

    public void setCanal1(Integer canal1) {
        this.canal1 = canal1;
    }

    public Integer getCanal2() {
        return canal2;
    }

    public void setCanal2(Integer canal2) {
        this.canal2 = canal2;
    }

    public Integer getCanal3() {
        return canal3;
    }

    public void setCanal3(Integer canal3) {
        this.canal3 = canal3;
    }

    public Integer getCanal4() {
        return canal4;
    }

    public void setCanal4(Integer canal4) {
        this.canal4 = canal4;
    }

    public Integer getCanal5() {
        return canal5;
    }

    public void setCanal5(Integer canal5) {
        this.canal5 = canal5;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Treatment toEntity(){
        return new Treatment(
                this.id, this.procedureDate, this.patient.toEntity(), this.rootFile.toEntinty(), this.staple.toEntity(), this.tooth,
                this.canal1, this.canal2, this.canal3, this.canal4, this.canal5, this.observation);
    }
}
