package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Author simon = new Author("Simon", "Kuhn");
        Book ddd = new Book("Domain Driven Design", "123456");
        simon.getBooks().add(ddd);
        ddd.getAuthors().add(simon);

        authorRepository.save(simon);
        bookRepository.save(ddd);

        Author yuval = new Author("Yuval", "Harari");
        Book bhoh = new Book("Brief History Of Humandkind", "654321");
        yuval.getBooks().add(bhoh);
        bhoh.getAuthors().add(yuval);

        authorRepository.save(yuval);
        bookRepository.save(bhoh);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
    }
}
