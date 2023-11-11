package repository.book.cache;

import repository.book.AbstractRepository;

public abstract class BookRepositoryDecorator<T> extends AbstractRepository<T> {
    protected AbstractRepository<T> decoratedRepository;

    public BookRepositoryDecorator(AbstractRepository<T> decoratedRepository, Class<T> classType) {
        super(classType);
        this.decoratedRepository = decoratedRepository;
    }
}
