package practices.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practices.shopping.model.ListEntity;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {

}