package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.model.Panchayat;

@Repository
public class PanchayatDAO extends GenericDaoHibernate<Panchayat, Long> implements	IPanchayatDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public PanchayatDAO() {
		super(Panchayat.class);

	}
	public List<Object[]> getPanchayatIdAndNameByIds(List<Long> panchayatIds){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.panchayatId,model.panchayatName from Panchayat model ");
	    		if(panchayatIds != null && panchayatIds.size()>0){
	    	    	sb.append(" where model.panchayatId in (:panchayatIds)");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(panchayatIds != null && panchayatIds.size()>0){
	    		   query.setParameterList("panchayatIds", panchayatIds);
	    	   }
	    	   
	    	   return query.list();
	}
	
}
