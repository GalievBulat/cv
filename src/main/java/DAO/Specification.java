package DAO;

public interface Specification<T> {
    boolean check(T model);
}
