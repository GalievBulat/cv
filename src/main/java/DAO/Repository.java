package DAO;

import java.util.List;

public interface Repository<T> {
    void update(T model) throws CommandHasntWorkedException;
    void delete(T model) throws CommandHasntWorkedException;
    void add(T model) throws CommandHasntWorkedException;
}
