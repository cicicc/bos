package com.afeng.bos.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    void delete(T entity);
    void save(T entity);
    void update(T entity);
    T findById(Serializable id);
    List<T> findAll();
    void saveOrUpdate(T entity);
}
