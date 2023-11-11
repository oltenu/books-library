package repository.book.cache;

import repository.book.AbstractRepository;

import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator<T> extends BookRepositoryDecorator<T> {
    private final Cache<Long, T> cache;

    public BookRepositoryCacheDecorator(AbstractRepository<T> repository, Cache<Long, T> cache, Class<T> classType) {
        super(repository, classType);
        this.cache = cache;
    }

    @Override
    public List<T> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }

        List<T> books = decoratedRepository.findAll();
        cache.save(books);

        return books;
    }

    @Override
    public Optional<T> findById(Long id) {
        if(cache.hasResult()){
            return Optional.ofNullable(cache.loadMap().get(id));
        }

        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(T book) {
        cache.invalidateCache();
        return decoratedRepository.save(book);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}