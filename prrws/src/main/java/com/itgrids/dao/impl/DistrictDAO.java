package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDistrictDAO;
import com.itgrids.model.District;

@Repository
public class DistrictDAO extends GenericDaoHibernate<District, Long> implements IDistrictDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public DistrictDAO() {
		super(District.class);
      
		
	}
	@Override
	public List<Object[]> getDistrictIdName(Long stateId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select model.districtId,model.districtName from District model "+
	               " where model.stateId=:stateId");
	    Query query = getSession().createQuery(sb.toString());
	    query.setParameter("stateId",stateId);
	    return query.list();
	}
	
	public List<Long> getDistrictIdDetailsByDistrictIds(String districtIds){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.districtId from District model "+
	               " where model.districtId in ("+districtIds+")");
	    Query query = getSession().createQuery(sb.toString());
	    return query.list();
	}
	
	public List<Object[]> getDistrictIdAndNameByDistrictIds(List<Long> districtIds){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.districtId,model.districtName from District model ");
	    if(districtIds != null && districtIds.size()>0){
	    	sb.append(" where model.districtId in (:districtIds)");
	    }
	    Query query = getSession().createQuery(sb.toString());
	    
	   if(districtIds != null && districtIds.size()>0){
		   query.setParameterList("districtIds", districtIds);
	   }
	    
	    return query.list();
	}
	
	public List<Object[]> getAllDistricts(){
		Query query = getSession().createQuery(" select distinct model.districtId,model.districtName from District model ");
		return query.list();
	}
	public String getAssignedSearchIdByDistrictId(Long searchLevelValue,String fromPage){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select model.prDistrict.districtCode from District model ");
		 if(searchLevelValue != null && searchLevelValue.longValue()>0){
			 sb.append(" where model.districtId =:searchLevelValue ");
		 }
		 Query query = getSession().createQuery(sb.toString());
		 if(searchLevelValue != null && searchLevelValue.longValue()>0){
			 query.setParameter("searchLevelValue", searchLevelValue);
		 }
		return (String)query.uniqueResult();	
	}
	public String getAssignedSearchDistrictId(Long searchLevelValue,String fromPage){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select model.prDistrictId from District model ");
		 if(searchLevelValue != null && searchLevelValue.longValue()>0){
			 sb.append(" where model.prDistrict.districtCode =:searchLevelValue ");
		 }
		 Query query = getSession().createQuery(sb.toString());
		 if(searchLevelValue != null && searchLevelValue.longValue()>0){
			 query.setParameter("searchLevelValue", searchLevelValue);
		 }
		return (String)query.uniqueResult();
		
	}
}
