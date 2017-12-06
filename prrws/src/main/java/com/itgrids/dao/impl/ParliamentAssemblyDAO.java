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
	public List<Object[]> getParliamentByConstIdAndName(Long parliamentId,String type){
		StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.assembly.constituencyId,model.assembly.name from ParliamentAssembly model ");
	    		if(parliamentId != null && parliamentId.longValue()>0){
	    	    	sb.append(" where model.parliament.constituencyId =:parliamentId ");
	    	    }
	    		
	    		if(type != null && !type.trim().isEmpty())
	    			sb.append(" and model.assembly.areaType != :type  ");
	    		
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(parliamentId != null && parliamentId.longValue()>0){
	    		   query.setParameter("parliamentId", parliamentId);
	    	   }
	    	   
	    	   if(type != null && !type.trim().isEmpty())
	    		   query.setParameter("type", type);
	    		   
	    	   return query.list();
	}
	public List<Object[]> getConsParlimentIds(){
		Query query = getSession().createQuery("select model.assembly.prConstituency.constituencyCode,"
				+ " model.parliamentId "
				+ " from ParliamentAssembly model ");
		return query.list();
	}

	
	/**
	 * @Author Srishailam Pittala
	 * @Date 7th July ,2017
	 * return @String  
	 */
	public List<Object[]> getFMSLocationIdfromRWSLocationId(String searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.parliament.constituencyId from ParliamentAssembly model where "
				+ " model.parliament.prConstituency.constituencyCode = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.parliament.prConstituency.constituencyCode  from ParliamentAssembly model where "
				+ " model.parliament.constituencyId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
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
		queryStr.append(" select distinct model.districtId from District model where model.districtId = :searchLevelValue");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("searchLevelValue", searchLevelValue);
		return query.list();
	}
	public List<Object[]> getAllParliaments(){
		Query query = getSession().createQuery(" select distinct model.parliamentId,model.parliament.name " +
				" from ParliamentAssembly model ");
		
		return query.list();
	}
	public List<Object[]> getParliamentWiseConstituency(Long locationId,String type) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model1.parliamentId,model2.name,model3.constituencyId,model3.name,model3.district.districtId,model3.district.districtName,model3.prConstituency.constituencyCode " +
				       " from  ParliamentAssembly model1,Constituency model2,Constituency model3 " +
					   " where " +
					   " model1.parliamentId=model2.constituencyId and model1.assemblyId=model3.constituencyId  ");
		if(type != null && type.equalsIgnoreCase("district") && locationId != null && locationId.longValue() > 0) {
			queryStr.append(" and ,model3.district.districtId =:locationId");
		} else if (type != null && type.equalsIgnoreCase("parliament") && locationId != null && locationId.longValue() > 0){
			queryStr.append(" and model1.parliamentId =:locationId");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if (locationId != null && locationId.longValue() > 0 ){
			query.setParameter("locationId", locationId);
		}
	    return query.list();
	}
	public List<Object[]> getParliamentIds(Long parliamentId) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model1.parliamentId,model1.parliamentId " +
				       " from  ParliamentAssembly model1,Constituency model2 " +
					   " where " +
					   " model1.parliamentId=model2.constituencyId ");
		if (parliamentId != null && parliamentId.longValue() > 0 ){
			queryStr.append(" and model1.parliamentId =:parliamentId");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if (parliamentId != null && parliamentId.longValue() > 0 ){
			query.setParameter("parliamentId", parliamentId);
		}
	    return query.list();
	}
	public List<Object[]> getParliamentIdsByConstituencyList(List<Long> parliamentIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.locationAddress.parliament.constituencyId, model.locationAddress.parliament.name from PetitionReffererCandidate model ");
		if (parliamentIdsList != null && parliamentIdsList.size() > 0 ){
		  sb.append(" where model.locationAddress.parliament.constituencyId in (:parliamentIdsList) ");
		}
		Query query = getSession().createQuery(sb.toString());
		if (parliamentIdsList != null && parliamentIdsList.size() > 0 ){
			query.setParameterList("parliamentIdsList", parliamentIdsList);
		}
		return query.list();
	}
}
