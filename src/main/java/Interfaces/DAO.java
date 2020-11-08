package Interfaces;

import java.util.Optional;

public interface DAO<T> {
    Optional<T> find(long id) ;
    void update(T model,long tc) ;
    void delete(long id);
    void add(T model);
    boolean checkIfPresentedByTC(long tc);
}
