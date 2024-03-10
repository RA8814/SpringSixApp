package guru.springframework.springsixapp.bootstrap;

import guru.springframework.springsixapp.domain.Author;
import guru.springframework.springsixapp.domain.Book;
import guru.springframework.springsixapp.repositories.AuthorRepository;
import guru.springframework.springsixapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
// ^^ Because this is marked as a component and the class only has one constructor, Spring Autowires all of the necessary components within that constructor in order for it to run
public class BootstrapData implements CommandLineRunner {
                            //^^ If this class is found within the context, pick it up and execute the run method
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author(); eric.setFirstName("Eric"); eric.setLastName("Evans");
        Author rod = new Author(); rod.setFirstName("Rod"); rod.setLastName("Johnson");

        Book ddd = new Book(); ddd.setTitle("Domain Driven Design"); ddd.setIsbn("123456");
        Book spf = new Book(); spf.setTitle("Spring Frame Work"); spf.setIsbn("998877");

        // Persist/Save:
        Author ericSavedInstance = authorRepository.save(eric); Book dddSavedInstance = bookRepository.save(ddd);
        Author rodSavedInstance = authorRepository.save(rod); Book spfSavedInstance = bookRepository.save(spf);

        // Combine:
        ericSavedInstance.getBooks().add(dddSavedInstance);
        rodSavedInstance.getBooks().add(spfSavedInstance);
        // Potentially Useless Persist/Save, supposedly we would not have persisted the book associations buuuuuuuuut doubt it:
        authorRepository.save(ericSavedInstance);
        authorRepository.save(rodSavedInstance);

        System.out.println("In Bootstrap:");
        System.out.println("\tAuthor count: " + authorRepository.count());
        System.out.println("\tBook count: " + bookRepository.count());

        System.out.println(rod.equals(rodSavedInstance));
        //^^ VERY IMPORTANT: TRUE
    }
}
