package com.itgrids.eliteclub.dao;



import org.hibernate.Session;
import org.hibernate.criterion.Criterion;


import java.io.Serializable;
import java.util.List;

public interface AbstractDao<E, I extends Serializable> {

   public   E get(I id);
   public void saveOrUpdate(E e);
   public  void delete(E e);
   public List<E> findByCriteria(Criterion criterion);
   public  E save(E e);
   public Session getCurrentSession();


}
