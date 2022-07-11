package borges.dimitrus.rootcanalapi.repository;

import borges.dimitrus.rootcanalapi.model.entity.PatientSymptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientSymptomRepository extends JpaRepository<PatientSymptom, Long> {
}
