package DAO;

import java.util.Optional;

public interface DAO<T> {
    Optional<T> find(long id) throws CommandHasntWorkedException;
    void update(T model,long tc) throws CommandHasntWorkedException;
    void delete(long id) throws CommandHasntWorkedException;
    void add(T model) throws CommandHasntWorkedException;
    boolean checkIfPresentedByTC(long tc) throws CommandHasntWorkedException;
    Object getConnection();
}
