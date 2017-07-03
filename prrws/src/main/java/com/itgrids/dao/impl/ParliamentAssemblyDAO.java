package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.model.ParliamentAssembly;

@Repository
public class ParliamentAssemblyDAO extends GenericDaoHibernate<ParliamentAssembly, Long> implements IParliamentAssemblyDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ParliamentAssemblyDAO() {
		super(ParliamentAssembly.class);

	}
	public List<Object[]> getParliamentIdAndName(Long districtId){
		StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.parliament.constituencyId,model.parliament.name from ParliamentAssembly model ");
	    		if(districtId != null && districtId.longValue()>0){
	    	    	sb.append(" where model.assembly.districtId =:districtId ");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(districtId != null && districtId.longValue()>0){
	    		   query.setParameter("districtId", districtId);
	    	   }
	    	   return query.list();
	}
	public List<Object[]> getParliamentByConstIdAndName(Long parliamentId){
		StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.assembly.constituencyId,model.assembly.name from ParliamentAssembly model ");
	    		if(parliamentId != null && parliamentId.longValue()>0){
	    	    	sb.append(" where model.parliament.constituencyId =:parliamentId ");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(parliamentId != null && parliamentId.longValue()>0){
	    		   query.setParameter("parliamentId", parliamentId);
	    	   }
	    	   return query.list();
	}

}
