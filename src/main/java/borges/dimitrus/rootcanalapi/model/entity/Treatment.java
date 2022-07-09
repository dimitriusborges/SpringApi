package borges.dimitrus.rootcanalapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate treatmentDate;

    @NotNull
    @ManyToOne
    private Patient patient;

    @NotNull
    @ManyToOne
    private RootFile rootFile;

    @NotNull
    @ManyToOne
    private Staple staple;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public RootFile getRootFile() {
        return rootFile;
    }

    public void setRootFile(RootFile rootFile) {
        this.rootFile = rootFile;
    }

    public Staple getStaple() {
        return staple;
    }

    public void setStaple(Staple staple) {
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
}
