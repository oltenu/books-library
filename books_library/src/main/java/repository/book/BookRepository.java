package repository.book;

import model.Book;

import java.sql.Connection;

public class BookRepository extends AbstractRepository<Book> {
    public BookRepository(Connection connection) {
        super(connection, Book.class);
    }
}
