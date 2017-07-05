package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITehsilDAO;
import com.itgrids.model.Tehsil;

@Repository
public class TehsilDAO extends GenericDaoHibernate<Tehsil,Long> implements ITehsilDAO{
	
	@Autowired
	SessionFactory sessionFactory; 
	
	public TehsilDAO() {
		super(Tehsil.class);
	}
	
	public List<Object[]> getTehsilIdAndNameByIds(List<Long> tehsilIds){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.tehsilId,model.tehsilName from Tehsil model ");
	    		if(tehsilIds != null && tehsilIds.size()>0){
	    	    	sb.append(" where model.tehsilId in (:tehsilIds)");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(tehsilIds != null && tehsilIds.size()>0){
	    		   query.setParameterList("tehsilIds", tehsilIds);
	    	   }
	    	   
	    	   return query.list();
	}
	
	public List<Object[]> getTehsilsForConstituency(Long constituencyId){
		Query query = getSession().createQuery(" select model.tehsilId,model.tehsilName "
				+ " from Tehsil model,Constituency model1 "
				+ " where model.districtId = model1.districtId and model1.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
}
