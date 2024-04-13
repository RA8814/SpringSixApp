package guru.springframework.springsixapp.repositories;

import guru.springframework.springsixapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
