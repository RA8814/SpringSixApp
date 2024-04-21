package guru.springframework.springsixapp.service;

import guru.springframework.springsixapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();

}
