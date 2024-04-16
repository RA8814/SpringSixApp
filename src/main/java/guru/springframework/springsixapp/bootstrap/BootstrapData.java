package guru.springframework.springsixapp.bootstrap;

import guru.springframework.springsixapp.domain.Author;
import guru.springframework.springsixapp.domain.Book;
import guru.springframework.springsixapp.domain.Publisher;
import guru.springframework.springsixapp.repositories.AuthorRepository;
import guru.springframework.springsixapp.repositories.BookRepository;
import guru.springframework.springsixapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
// ^^ Because this is marked as a component and the class only has one constructor, Spring Autowires all of the necessary components within that constructor in order for it to run
public class BootstrapData implements CommandLineRunner {
                            //^^ If this class is found within the context, pick it up and execute the run method
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
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

        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main");
        Publisher publisherSavedInstance = publisherRepository.save(publisher);
        ddd.setPublisher(publisherSavedInstance);

        // Combine (proof that eric and ericSavedInstance are the same object in memory):
        // This two-way save is necessary to populate the AUTHOR_BOOK join table
        ericSavedInstance.getBooks().add(dddSavedInstance);
        rodSavedInstance.getBooks().add(spfSavedInstance);
        dddSavedInstance.getAuthors().add(eric);
        spfSavedInstance.getAuthors().add(rod);

        // Potentially Useless Persist/Save, supposedly we would not have persisted the book associations buuuuuuuuut doubt it:
        authorRepository.save(ericSavedInstance);
        authorRepository.save(rodSavedInstance);
        publisherRepository.save(publisherSavedInstance);
        bookRepository.save(ddd);
        bookRepository.save(spf);

        System.out.println("In Bootstrap:");
        System.out.println("\tAuthor count: " + authorRepository.count());
        System.out.println("\tBook count: " + bookRepository.count());

        System.out.println(rod.equals(rodSavedInstance));
        //^^ VERY IMPORTANT: TRUE
    }
}
