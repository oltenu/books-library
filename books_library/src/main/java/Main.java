import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryMock;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepositoryMock();

        Book book = new BookBuilder()
                .setId(1L)
                .setTitle("To Die in Spring")
                .setAuthor("Ralf Rothmann")
                .setPublishedDate(LocalDate.of(2020, 10, 3))
                .build();

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
        System.out.println(bookRepository.findById(1L));

        JDBConnectionWrapper jdbConnectionWrapper = new JDBConnectionWrapper("test_library");

        System.out.println(jdbConnectionWrapper.getConnection());
    }
}
