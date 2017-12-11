package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaComponentStatusDAO;
import com.itgrids.model.NregaComponentStatus;

@Repository
public class NregaComponentStatusDAO extends GenericDaoHibernate<NregaComponentStatus, Long> implements INregaComponentStatusDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public NregaComponentStatusDAO() {
		super(NregaComponentStatus.class);

	}
	public List<Object[]> getNregaComponentStatus() {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select model.nregaComponentStatusId,model.status " +
		 		" from NregaComponentStatus model " );
		 
		 Query query = getSession().createQuery(queryStr.toString());
		 
		 return query.list();
	}
	
	

}
