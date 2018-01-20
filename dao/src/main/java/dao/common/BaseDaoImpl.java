package dao.common;

import entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.util.List;


public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> modelClass;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        modelClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }

    @Override
    public T findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(modelClass, id);
    }

    @Override
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM " + modelClass.getSimpleName(), modelClass).list();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
