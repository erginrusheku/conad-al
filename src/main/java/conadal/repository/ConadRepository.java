package conadal.repository;

import conadal.model.Conad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConadRepository extends JpaRepository<Conad, Long> {
}
