package borges.dimitrus.rootcanalapi.repository;

import borges.dimitrus.rootcanalapi.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
