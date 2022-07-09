package borges.dimitrus.rootcanalapi.repository;

import borges.dimitrus.rootcanalapi.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
