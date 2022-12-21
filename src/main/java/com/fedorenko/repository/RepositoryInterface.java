package com.fedorenko.repository;


public interface RepositoryInterface<T> {
    void save(final T t);
    T[] getAll();
    T getById(final String id);
    void delete(final String id);
    void insert(int index, final T t);

}
