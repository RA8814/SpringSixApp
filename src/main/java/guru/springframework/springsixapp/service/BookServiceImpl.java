package guru.springframework.springsixapp.service;

import guru.springframework.springsixapp.domain.Book;
import guru.springframework.springsixapp.repositories.BookRepository;
import org.springframework.stereotype.Service;

/*Simple demonstrative implementation. Typically, there will be more concrete business logic that isn't covered by the Repo.*/
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
}
