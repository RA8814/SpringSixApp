package guru.springframework.springsixapp.repositories;

import guru.springframework.springsixapp.domain.Author;
import org.springframework.data.repository.CrudRepository;


public interface AuthorRepository extends CrudRepository<Author,Long> {
                                            // ^^ Type of what we want to store, the type of the id within the class

}
