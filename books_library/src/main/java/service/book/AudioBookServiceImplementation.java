package service.book;

import model.AudioBook;
import repository.book.AbstractRepository;
import repository.book.AudioBookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AudioBookServiceImplementation implements AudioBookService {
    private final AbstractRepository<AudioBook> bookRepository;

    public AudioBookServiceImplementation(AbstractRepository<AudioBook> bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<AudioBook> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public AudioBook findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public boolean save(AudioBook book) {
        return bookRepository.save(book);
    }

    @Override
    public int getAgeOfBook(Long id) {
        AudioBook book = this.findById(id);

        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.YEARS.between(book.getPublishedDate(), now);
    }

    @Override
    public int getRunTime(Long id) {
        AudioBook audioBook = this.findById(id);

        return audioBook.getRunTime();
    }
}
