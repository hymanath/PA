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
	public List<Object[]> getPanchayatIdAndName(Long tehsilId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.panchayatId,model.panchayatName from Panchayat model ");
	    		if(tehsilId != null && tehsilId.longValue()>0){
	    	    	sb.append(" where model.tehsilId =:tehsilId ");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	   if(tehsilId != null && tehsilId.longValue()>0){
	    		   query.setParameter("tehsilId", tehsilId);
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
	public List<Object[]> getMgnregsPanchayatMappingCode(Long panchayatId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.panchayat.panchayatId,model.uniquecode from PrPanchayat model ");
		if(panchayatId != null && panchayatId.longValue() > 0) {
			queryStr.append(" where model.panchayat.panchayatId=:panchayatId");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(panchayatId != null && panchayatId.longValue() > 0) {
			query.setParameter("panchayatId", panchayatId);
		}
		return query.list();
	}
	public List<Object[]> getPanchayatsByMandald(Long tehsilId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.panchayatId,model.panchayatName "
	    			+ " from Panchayat model ");
    		if(tehsilId != null && tehsilId.longValue()>0){
    	    	sb.append(" where model.tehsilId =:tehsilId ");
    	    }
    		sb.append(" order by model.panchayatName asc");
    		
    	    Query query = getSession().createQuery(sb.toString());
    	    if(tehsilId != null && tehsilId.longValue()>0){
    		   query.setParameter("tehsilId", tehsilId);
    	   }
    	   
    	   return query.list();
		}
}
