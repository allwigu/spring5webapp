package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Publisher saimon = new Publisher("saimon", "Babostrasse 12", "Wintiiii", "Züriiii", "8400");
        publisherRepository.save(saimon);
        System.out.println("Number of publisher: " + publisherRepository.count());
        publisherRepository.findAll().forEach(System.out::println);

        Author simon = new Author("Simon", "Kuhn");
        Book ddd = new Book("Domain Driven Design", "123456");
        simon.getBooks().add(ddd);
        ddd.getAuthors().add(simon);
        ddd.setPublisher(saimon);
        saimon.getBooks().add(ddd);

        authorRepository.save(simon);
        bookRepository.save(ddd);
        publisherRepository.save(saimon);

        Author yuval = new Author("Yuval", "Harari");
        Book bhoh = new Book("Brief History Of Humandkind", "654321");
        yuval.getBooks().add(bhoh);
        bhoh.getAuthors().add(yuval);
        bhoh.setPublisher(saimon);
        saimon.getBooks().add(bhoh);

        authorRepository.save(yuval);
        bookRepository.save(bhoh);
        publisherRepository.save(saimon);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publisher: " + publisherRepository.count());
        Optional<Publisher> publisher = publisherRepository.findByName("saimon");
        publisher.ifPresent(pub -> System.out.println("Number of saimon's books: " + pub.getBooks().size()));
    }
}
