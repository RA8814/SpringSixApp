package guru.springframework.springsixapp.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity //<-- allows jakarta to persist this as an entity within a database
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // <-- States that jakarta should use its own id generation logic for unique identifiers
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "authors") // <-- notice that "authors" is the same name as the property that is a Set of <Author> within the Book class!
    private Set<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;

        return getId() != null ? getId().equals(author.getId()) : author.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
