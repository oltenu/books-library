import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryMock;
import repository.book.BookRepositoryMySQL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        BookRepository repository = new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection());
        //repository.removeAll();
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
