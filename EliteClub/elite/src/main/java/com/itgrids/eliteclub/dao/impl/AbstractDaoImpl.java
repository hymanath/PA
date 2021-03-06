package com.itgrids.eliteclub.dao.impl;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.dao.AbstractDao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
@Transactional
public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E,I> {
    protected final Log log = LogFactory.getLog(getClass());

    private Class<E> entityClass;

    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    //@Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
   @Transactional
    public Session getCurrentSession() {
    	
    	 Session session = null;
    	 try{
    	     session = sessionFactory.getCurrentSession();
    	 }catch(HibernateException hex){
    	     hex.printStackTrace();
    	 }
    	 catch(Exception e){
    	     e.printStackTrace();
    	 }
    	 if(session == null ){
    	     session = sessionFactory.openSession();
    	 }
    	 
    	 return session;
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
    //@Override
    public void saveOrUpdateForTrancasction(E e) {
    	sessionFactory.getCurrentSession().saveOrUpdate(e);
    }
  
    public void delete(E e) {
        getCurrentSession().delete(e);
    }

  
    @SuppressWarnings("unchecked")
	public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }
    
    
    @SuppressWarnings("unchecked")
	public E save(E object) {
       // return (E)getCurrentSession().saveOrUpdate(object);.merge(object);
    	
    	Session session = getCurrentSession();
    	session.saveOrUpdate(object);
    	return (E) session.merge(object);
    }
}
