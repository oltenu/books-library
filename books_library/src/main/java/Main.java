import database.DatabaseConnectionFactory;
import model.AudioBook;
import model.Book;
import model.EBook;
import model.builder.AudioBookBuilder;
import model.builder.BookBuilder;
import model.builder.EBookBuilder;
import repository.book.AbstractRepository;
import repository.book.AudioBookRepository;
import repository.book.BookRepository;
import repository.book.EBookRepository;
import repository.book.cache.RepositoryCacheDecorator;
import repository.book.cache.Cache;
import service.book.*;

import java.sql.Connection;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();
        Cache<Long, Book> cache = new Cache<>();
        Cache<Long, EBook> eCache = new Cache<>();
        Cache<Long, AudioBook> audioCache = new Cache<>();

        AbstractRepository<Book> bookRepository = new RepositoryCacheDecorator<>(
                new BookRepository(connection), cache, Book.class);

        AbstractRepository<EBook> eBookRepository = new RepositoryCacheDecorator<>(
                new EBookRepository(connection), eCache, EBook.class);

        AbstractRepository<AudioBook> audioBookRepository = new RepositoryCacheDecorator<>(
                new AudioBookRepository(connection), audioCache, AudioBook.class);

        BookService bookService = new BookServiceImplementation(bookRepository);
        EBookService eBookService = new EBookServiceImplementation(eBookRepository);
        AudioBookService audioBookService = new AudioBookServiceImplementation(audioBookRepository);


        Book book = new BookBuilder()
                .setId(10L)
                .setTitle("Book")
                .setAuthor("Author1")
                .setPublishedDate(LocalDate.now())
                .build();

        EBook eBook = new EBookBuilder()
                .setId(10L)
                .setTitle("EBook")
                .setAuthor("Author2")
                .setPublishedDate(LocalDate.now())
                .setFormat("pdf")
                .build();

        AudioBook audioBook = new AudioBookBuilder()
                .setId(10L)
                .setTitle("AudioBook")
                .setAuthor("Author3")
                .setPublishedDate(LocalDate.now())
                .setRunTime(60)
                .build();

        bookService.save(book);
        eBookService.save(eBook);
        audioBookService.save(audioBook);

        System.out.println(bookService.findById(1L));
        System.out.println(eBookService.findById(1L));
        System.out.println(audioBookService.findById(1L));

        System.out.println(bookService.findAll());
        System.out.println(eBookService.findAll());
        System.out.println(audioBookService.findAll());

        System.out.println(bookService.getAgeOfBook(1L));
        System.out.println(eBookService.getFormat(1L));
        System.out.println(audioBookService.getRunTime(1L));
    }
}
