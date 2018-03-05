package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDivisionDAO;
import com.itgrids.model.Division;

@Repository
public class DivisionDAO extends GenericDaoHibernate<Division, Long> implements IDivisionDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public DivisionDAO() {
		super(Division.class);
    }
	
	public List<Object[]> getDivisionIdAndNameByIds(List<Long> divisonIds){
		Query query = getSession().createQuery(" select model.divisionId,model.divisionName "
				+ " from Division model "
				+ " where model.divisionId in (:divisonIds) ");
		query.setParameterList("divisonIds", divisonIds);
		return query.list();
	}
	
}
