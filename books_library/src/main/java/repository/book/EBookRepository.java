package repository.book;

import model.EBook;

import java.sql.Connection;

public class EBookRepository extends AbstractRepository<EBook> {
    public EBookRepository(Connection connection) {
        super(connection, EBook.class);
    }
}
