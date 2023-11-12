package service.book;

import model.EBook;
import repository.book.AbstractRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EBookServiceImplementation implements EBookService {
    private final AbstractRepository<EBook> EBookRepository;

    public EBookServiceImplementation(AbstractRepository<EBook> bookRepository) {
        this.EBookRepository = bookRepository;
    }

    @Override
    public List<EBook> findAll() {
        return EBookRepository.findAll();
    }

    @Override
    public EBook findById(Long id) {
        return EBookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public boolean save(EBook book) {
        return EBookRepository.save(book);
    }

    @Override
    public int getAgeOfBook(Long id) {
        EBook book = this.findById(id);

        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.YEARS.between(book.getPublishedDate(), now);
    }

    @Override
    public String getFormat(Long id) {
        EBook eBook = this.findById(id);

        return eBook.getFormat();
    }
}
