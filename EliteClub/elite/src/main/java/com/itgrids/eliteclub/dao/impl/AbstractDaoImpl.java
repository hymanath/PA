package com.itgrids.eliteclub.dao.impl;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.eliteclub.dao.AbstractDao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E,I> {
    protected final Log log = LogFactory.getLog(getClass());

    private Class<E> entityClass;

    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

   // @Autowired
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @SuppressWarnings("unchecked")
	public E get(I id) {
       return (E) getCurrentSession().get(entityClass, id);
    	/*E entity = (E) getCurrentSession().get(this.entityClass, id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.entityClass + "' object with id '" + id + "' not found...");
            throw new org.s(this.entityClass, id);
        }

        return entity;*/
    }

   
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }

  
    public void delete(E e) {
        getCurrentSession().delete(e);
    }

  
    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }
    
    
    public E save(E object) {
        return (E)getCurrentSession().merge(object);
    }
}
