package repository.book;

import database.DatabaseConnectionFactory;
import model.Book;
import model.builder.BookBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMySQLTest {
    private static final BookRepository repository = new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection());

    @BeforeEach
    public void setUp(){
        populateDatabase();
    }

    @AfterEach
    public void cleanUp(){
        repository.removeAll();
    }

    @Test
    public void testSaveToDatabase(){
        //given
        Book bookToInsert = new BookBuilder()
                .setTitle("The Boy in the Striped Pyjamas")
                .setAuthor("John Boyne")
                .setPublishedDate(LocalDate.of(2006, 1, 5))
                .build();

        Book bookToEqual = new BookBuilder()
                .setId(6L)
                .setTitle("The Boy in the Striped Pyjamas")
                .setAuthor("John Boyne")
                .setPublishedDate(LocalDate.of(2006, 1, 5))
                .build();

        //when
        repository.save(bookToInsert);

        //then
        Optional<Book> optional = repository.findById(6L);
        Book book = null;

        if(optional.isPresent()){
            book = optional.get();
        }

        assertEquals(book, bookToEqual);
    }

    @Test
    public void testFindById(){
        //given
        Book bookToEqual = new BookBuilder()
                .setId(3L)
                .setTitle("The Book Thief")
                .setAuthor("Markus Zusak")
                .setPublishedDate(LocalDate.of(2014, 2, 7))
                .build();

        //when
        Optional<Book> optional = repository.findById(3L);
        Book book = null;
        if(optional.isPresent())
            book = optional.get();

        //then
        assertEquals(book, bookToEqual);
    }

    @Test
    public void testFindAll(){
        //given
        Book book1 = new BookBuilder()
                .setId(1L)
                .setTitle("To Die in Spring")
                .setAuthor("Ralf Rothmann")
                .setPublishedDate(LocalDate.of(2015, 10, 3))
                .build();

        Book book2 = new BookBuilder()
                .setId(2L)
                .setTitle("Sapiens: A Brief History of Humankind")
                .setAuthor("Yuval Noah")
                .setPublishedDate(LocalDate.of(2011, 4, 25))
                .build();

        Book book3 = new BookBuilder()
                .setId(3L)
                .setTitle("The Book Thief")
                .setAuthor("Markus Zusak")
                .setPublishedDate(LocalDate.of(2014, 2, 7))
                .build();

        Book book4 = new BookBuilder()
                .setId(4L)
                .setTitle("A Tree Grows in Brooklyn")
                .setAuthor("Betty Smith")
                .setPublishedDate(LocalDate.of(1943, 7, 25))
                .build();

        Book book5 = new BookBuilder()
                .setId(5L)
                .setTitle("Black and White")
                .setAuthor("Caitlin Kittredge and Jackie Kessler")
                .setPublishedDate(LocalDate.of(2009, 12, 10))
                .build();

        List<Book> booksToEqual = new ArrayList<>();
        booksToEqual.add(book1);
        booksToEqual.add(book2);
        booksToEqual.add(book3);
        booksToEqual.add(book4);
        booksToEqual.add(book5);

        //when
        List<Book> books = repository.findAll();

        //then
        assertEquals(books, booksToEqual);
    }

    @Test
    public void testRemoveAll(){
        //given

        //when
        repository.removeAll();

        //then
        assertTrue(repository.findAll().isEmpty());
    }

    private static void populateDatabase() {
        Book book1 = new BookBuilder()
                .setTitle("To Die in Spring")
                .setAuthor("Ralf Rothmann")
                .setPublishedDate(LocalDate.of(2015, 10, 3))
                .build();

        Book book2 = new BookBuilder()
                .setTitle("Sapiens: A Brief History of Humankind")
                .setAuthor("Yuval Noah")
                .setPublishedDate(LocalDate.of(2011, 4, 25))
                .build();

        Book book3 = new BookBuilder()
                .setTitle("The Book Thief")
                .setAuthor("Markus Zusak")
                .setPublishedDate(LocalDate.of(2014, 2, 7))
                .build();

        Book book4 = new BookBuilder()
                .setTitle("A Tree Grows in Brooklyn")
                .setAuthor("Betty Smith")
                .setPublishedDate(LocalDate.of(1943, 7, 25))
                .build();

        Book book5 = new BookBuilder()
                .setTitle("Black and White")
                .setAuthor("Caitlin Kittredge and Jackie Kessler")
                .setPublishedDate(LocalDate.of(2009, 12, 10))
                .build();

        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
    }
}
