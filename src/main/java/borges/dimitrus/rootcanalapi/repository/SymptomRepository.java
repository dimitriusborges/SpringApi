package borges.dimitrus.rootcanalapi.repository;

import borges.dimitrus.rootcanalapi.model.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
}
