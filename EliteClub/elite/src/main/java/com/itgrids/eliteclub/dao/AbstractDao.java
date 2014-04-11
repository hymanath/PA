package com.itgrids.eliteclub.dao;



import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<E, I extends Serializable> {

   public   E get(I id);
   public void saveOrUpdate(E e);
   public  void delete(E e);
   public List<E> findByCriteria(Criterion criterion);
   public  E save(E e);

    
   /* public T save(T object) {
        return (T) super.getHibernateTemplate().merge(object);
    }*/
    
    
/*    public T get(PK id) {
        T entity = (T) super.getHibernateTemplate().get(this.persistentClass, id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }*/
}
