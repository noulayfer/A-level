package com.fedorenko.repository;


import com.fedorenko.model.Car;

public interface RepositoryInterface<T> {
    void save(final T t);
    T[] getAll();
    T getById(final String id);
    void delete(final String id);
    void insert(int index, final T t);

}
