package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.model.Constituency;

@Repository
public class ConstituencyDAO extends GenericDaoHibernate<Constituency, Long> implements IConstituencyDAO {

	@Autowired
	SessionFactory sessionFactory;
	public ConstituencyDAO() {
		super(Constituency.class);

	}
	public List<Object[]> getConstituencies(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.constituencyId,model.name from Constituency model ");
		if(districtId !=null && districtId.longValue()>0){
			sb.append(" where model.districtId=:districtId ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(districtId !=null && districtId.longValue()>0){
		    query.setParameter("districtId", districtId);
		}
		return query.list(); 
	}
	public List<Long> getConstituencyList(String districtIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.constituencyId from Constituency model "+
				" where model.districtId in ("+districtIds+") ");
		Query query = getSession().createQuery(sb.toString());
		//query.setParameter("districtIds", districtIds);
		return query.list(); 
	}
	
	public List<Object[]> getConstIdAndNameByConstIds(List<Long> constIds){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.constituencyId,model.name from Constituency model ");
	    		if(constIds != null && constIds.size()>0){
	    	    	sb.append(" where model.constituencyId in (:constIds)");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(constIds != null && constIds.size()>0){
	    		   query.setParameterList("constIds", constIds);
	    	   }
	    	   
	    	   return query.list();
	}
	public List<Object[]> getConstIdAndName(Long constId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.constituencyId,model.name from Constituency model ");
	    		if(constId != null && constId.longValue()>0){
	    	    	sb.append(" where model.constituencyId =: constId ");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(constId != null && constId.longValue()>0){
	    		   query.setParameter("constId", constId);
	    	   }
	    	   
	    	   return query.list();
	}
	public String getAssignedSearchIdByConstituencyId(Long searchLevelValue,String fromPage){
		 StringBuilder sb = new StringBuilder();
		    sb.append(" select model.prConstituency.constituencyCode from Constituency model ");
		    		if(searchLevelValue != null && searchLevelValue.longValue()>0){
		    	    	sb.append(" where model.constituencyId =: searchLevelValue ");
		    	    }
		    	    Query query = getSession().createQuery(sb.toString());
		    	    
		    	   if(searchLevelValue != null && searchLevelValue.longValue()>0){
		    		   query.setParameter("searchLevelValue", searchLevelValue);
		    	   }
		return (String)query.uniqueResult();
		
		
	}
	public String getAssignedSearchConstituencyId(Long searchLevelValue,String fromPage){
		 StringBuilder sb = new StringBuilder();
		    sb.append(" select model.prConstituencyId from Constituency model ");
		    		if(searchLevelValue != null && searchLevelValue.longValue()>0){
		    	    	sb.append(" where model.prConstituency.constituencyCode  =: searchLevelValue ");
		    	    }
		    	    Query query = getSession().createQuery(sb.toString());
		    	    
		    	   if(searchLevelValue != null && searchLevelValue.longValue()>0){
		    		   query.setParameter("searchLevelValue", searchLevelValue);
		    	   }
		return (String)query.uniqueResult();
	}
}
