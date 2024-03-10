package guru.springframework.springsixapp.repositories;

import guru.springframework.springsixapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    // ^^ Type of what we want to store, the type of the id within the class

}
