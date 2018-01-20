package dao.common;

import entity.BaseEntity;

import java.util.List;


public interface BaseDao<T extends BaseEntity> {

    T findById(Long id);

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    List<T> findAll();
}
