package borges.dimitrus.rootcanalapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate procedureDate;

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
    private Integer tooth;

    private Integer canal1;

    private Integer canal2;

    private Integer canal3;

    private Integer canal4;

    private Integer canal5;

    private String observation;

    public Treatment() {
    }

    public Treatment(Long id, LocalDate procedureDate, Patient patient, RootFile rootFile, Staple staple, Integer tooth,
                     Integer canal1,
                     Integer canal2,
                     Integer canal3,
                     Integer canal4,
                     Integer canal5, String observation) {
        this.id = id;
        this.procedureDate = procedureDate;
        this.patient = patient;
        this.rootFile = rootFile;
        this.staple = staple;
        this.tooth = tooth;
        this.canal1 = canal1;
        this.canal2 = canal2;
        this.canal3 = canal3;
        this.canal4 = canal4;
        this.canal5 = canal5;
        this.observation = observation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getProcedureDate() {
        return procedureDate;
    }

    public void setProcedureDate(LocalDate procedureDate) {
        this.procedureDate = procedureDate;
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
