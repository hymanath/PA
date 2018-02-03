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
		Query query = getSession().createQuery(" select model.tehsil.tehsilId,tehsil.tehsilName "
				+ " from TehsilConstituency model "
				+ " where model.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		
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
		queryStr.append(" select distinct model.districtId from District model where model.prDistrict.districtCode = :searchLevelValue");
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
		queryStr.append(" select distinct model.prDistrict.districtCode from District model where model.districtId = :searchLevelValue");
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
	
	public List<Object[]> getMgnregsTehsilMappingCode(Long tehsilId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.tehsilId,model.prTehsil.uniqueCode from Tehsil model");
		if(tehsilId != null && tehsilId.longValue() > 0 ){
			queryStr.append(" where model.tehsilId=:tehsilId");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(tehsilId != null && tehsilId.longValue() > 0 ){
			query.setParameter("tehsilId", tehsilId);
		}
		return query.list();
	}

	@Override
	public List<Object[]> getEncMandals() {
		Query query = getSession().createQuery("select model.encTehsilId,model.tehsilName from Tehsil model where model.encTehsilId is not null" );
		return query.list();
	}
	
	public List<Object[]> getTehsilsFrDistrict(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.tehsilId,"
				+ "model.tehsilName"
				+ " from Tehsil model");
		if(districtId != null && districtId.longValue() > 0L){
			sb.append(" where model.district.districtId = :districtId");
		}
		sb.append(" order by model.tehsilName asc");
		Query query = getSession().createQuery(sb.toString());
		if(districtId != null && districtId.longValue() > 0L)
			query.setParameter("districtId", districtId);
		
		return query.list();
	}
}
