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
        BookRepository bookRepository = new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection());

        Book book = new BookBuilder()
                .setId(1L)
                .setTitle("To Die in Spring")
                .setAuthor("Ralf Rothmann")
                .setPublishedDate(LocalDate.of(2020, 10, 3))
                .build();

        //bookRepository.save(book);
        //System.out.println(bookRepository.findAll());
        System.out.println(bookRepository.findById(2L));

        JDBConnectionWrapper jdbConnectionWrapper = new JDBConnectionWrapper("test_library");

        try {
            System.out.println(jdbConnectionWrapper.testConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
