package com.fedorenko.repository;


import com.fedorenko.model.Car;

import java.util.List;

public interface RepositoryInterface<T> {
    void save(final T t);
    List<T> getAll();
    T getById(final String id);
    void delete(final String id);
    void insert(int index, final T t);

}
