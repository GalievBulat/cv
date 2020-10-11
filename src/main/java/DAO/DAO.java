package DAO;

import java.util.Optional;

public interface DAO<T> {
    Optional<T> find(long id);
    void update(T model);
    void delete(long id);
}
