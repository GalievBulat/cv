package Interfaces;

import java.util.Optional;

public interface Repository<T> {
    void update(T model) ;
    void delete(T model) ;
    void add(T model) ;
    Optional<T> get(long num);
}
