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
	public List<Object[]> getConstituencies(Long districtId,String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.constituencyId,model.name from Constituency model ");
		if(districtId !=null && districtId.longValue()>0){
			sb.append(" where model.districtId=:districtId ");
		}
		if(type != null && !type.trim().isEmpty()){
			sb.append(" and model.areaType != :type ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(districtId !=null && districtId.longValue()>0){
		    query.setParameter("districtId", districtId);
		}
		if(type != null && !type.trim().isEmpty()){
			  query.setParameter("type", type);
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
	public List<Object[]> getParlmentNames(List<Long> parlIds){
		StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.constituencyId,model.name from Constituency model ");
		if(parlIds != null && parlIds.size() >0){
	    	sb.append(" where model.constituencyId in (:parlIds) ");
	    }
	    	sb.append(" and model.electionScope.electionScopeId = 1");
		
	    Query query = getSession().createQuery(sb.toString());
	    
	    if(parlIds != null && parlIds.size() >0){
		   query.setParameterList("parlIds", parlIds);
	   }
	   
	   return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getFMSLocationIdfromRWSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getFMSLocationIdfromMGNREGSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getFMSLocationIdfromPRISLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.prConstituency.constituencyCode = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getFMSLocationIdfromENCLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getRWSLocationIdfromFMSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getRWSLocationIdfromMGNREGSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getRWSLocationIdfromPRISLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getRWSLocationIdfromENCLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getMGNREGSLocationIdfromFMSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getMGNREGSLocationIdfromRWSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getMGNREGSLocationIdfromPRISLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getMGNREGSLocationIdfromENCLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getPRISLocationIdfromFMSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.prConstituency.constituencyCode from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getPRISLocationIdfromRWSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getPRISLocationIdfromMGNREGSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getPRISLocationIdfromENCLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getENCLocationIdfromFMSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getENCLocationIdfromRWSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getENCLocationIdfromMGNREGSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getENCLocationIdfromPRISLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId from Constituency model where model.constituencyId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getFMSLocationIdfromFMSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getRWSLocationIdfromRWSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getMGNREGSLocationIdfromMGNREGSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getPRISLocationIdfromPRISLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getENSLocationIdfromENSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyListByDistrictId(Long districtId,String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.constituencyId,model.name " +
				  " from Constituency model " +
				  " where model.electionScope.electionScopeId = 2 and model.district.districtId = :districtId ");
		if(type != null && !type.trim().isEmpty()){
			sb.append(" and model.areaType != :type ");
		}
		  Query query = getSession().createQuery(sb.toString());
		  
		  query.setParameter("districtId", districtId);
		  if(type != null && !type.trim().isEmpty()){
			  query.setParameter("type", type);
		  }
		  return query.list();
		  
	  }
	public List<Object[]> getMgnregsConstituencyMappingCode(Long constituencyId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId,model.prConstituency.constituencyCode from Constituency model ");
		if (constituencyId != null && constituencyId.longValue() > 0) {
			queryStr.append(" where model.constituencyId =:constituencyId");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if (constituencyId != null && constituencyId.longValue() > 0) {
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	public List<Object[]> getConstituencyDistrictId(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituencyId,model.districtId from Constituency model ");
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	public List<Object[]> getLocationsNamesBySubLocation(Long districtId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.constituencyId,model.name from Constituency model ");
		queryStr.append("where ((model.areaType like '%rural%') or (model.areaType like '%rural-urban%')) ");
		if(districtId != null && districtId.longValue() >0){
			queryStr.append("and model.districtId=:districtId ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(districtId != null && districtId.longValue() >0){
			query.setParameter("districtId", districtId);
		}
		return query.list();

	}
	public List<Object[]> getConstituencyNamesByDistrictId(Long districtId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.constituencyId,model.name from Constituency model where " +
				" ( model.districtId between 11 and 23 ) ");
		if(districtId != null && districtId.longValue() >0){
			queryStr.append(" and   model.districtId=:districtId ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(districtId != null && districtId.longValue() >0){
			query.setParameter("districtId", districtId);
		}
		return query.list();

	}
	
	public List<Object[]> getPetitionsConstituencyList(Long districtId,String searchType,Long searchId){
		StringBuilder sb = new StringBuilder();
		if(searchId != null && (searchId.longValue() ==4L || searchId.longValue() ==11L ))
			sb.append(" select distinct model.pmRefCandidate.address.parliament.constituencyId,model.pmRefCandidate.address.parliament.name " );
		else
			sb.append(" select distinct model.pmRefCandidate.address.constituency.constituencyId,model.pmRefCandidate.address.constituency.name " );
		
			sb.append("  from PmRefCandidateDesignation model where model.isDeleted='N' ");
			if(districtId !=null && districtId.longValue()>0){
				sb.append(" and  model.pmRefCandidate.address.district.districtId=:districtId ");
			}
			if(searchId != null && searchId.longValue()>0L)
	    	 sb.append(" and  model.pmDesignation.pmDesignationId=:searchId");
			
	    Query query = getSession().createQuery(sb.toString());
	    if(districtId !=null && districtId.longValue()>0){
		    query.setParameter("districtId", districtId);
		}
	    if(searchId != null && searchId.longValue()>0L)
	    	 query.setParameter("searchId",searchId);
	    
		return query.list(); 
	}
	@Override
	public List<Object[]> getEncconstituencies() {
		
		Query query = getSession().createQuery("select model.constituency.encConstituencyId,model.constituency.name,model.tehsil.encTehsilId, model.tehsil.tehsilName " +
				"from TehsilConstituency model where model.constituency.encConstituencyId is not null");
		return query.list();
	}
	@Override
	public List<Long> getEncConstituencyIds() {
		Query query = getSession().createQuery("select model.encConstituencyId " +
				"from Constituency model where model.constituencyId is not null");
		return query.list();
	}
	
	public Long getConstituencyIdFromPRConstituencyId(String locationIdStr){
		Query query = getSession().createQuery("select distinct model.constituencyId from Constituency model where model.prConstituency.constituencyCode = :locationIdStr");
		query.setParameter("locationIdStr", locationIdStr);
		return (Long) query.uniqueResult();
	}
}
