import database.DatabaseConnectionFactory;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;

import javax.xml.crypto.Data;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Book book = new BookBuilder()
                .setTitle("HACKERMANN")
                .setAuthor("', '', null); DROP TABLE book; -- ")
                .setPublishedDate(LocalDate.of(2010, 10, 10))
                .build();

        BookRepository repository = new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection());

        repository.save(book);
        repository.findAll();
    }
}
