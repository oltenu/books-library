package repository.book;

import model.AudioBook;

import java.sql.Connection;

public class AudioBookRepository extends AbstractRepository<AudioBook> {
    public AudioBookRepository(Connection connection){
        super(connection, AudioBook.class);
    }
}
