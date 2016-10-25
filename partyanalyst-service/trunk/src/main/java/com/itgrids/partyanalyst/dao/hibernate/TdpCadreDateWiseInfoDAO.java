package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreDateWiseInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreDateWiseInfoDAO extends GenericDaoHibernate<TdpCadreDateWiseInfo, Long> implements ITdpCadreDateWiseInfoDAO{

	public TdpCadreDateWiseInfoDAO() {
		super(TdpCadreDateWiseInfo.class);
	}
	
	 public int deleteAllRecords(){
	    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_date_wise_info ");
    	return query.executeUpdate();
    }
	 
    public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_date_wise_info AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
    }
    public List<Object[]> get2016TotalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Date fromDate,Date toDate){
		
	      StringBuilder queryStr = new StringBuilder();  
	      queryStr.append(" select model.locationValue,sum(model.cadre2016) from TdpCadreDateWiseInfo model where model.locationScopeId =:locationScopeId ");
	      if(fromDate!= null && toDate!=null){
	   	    queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
	   	  }
	      if(locationValue != null && locationValue.size() > 0){
	    	queryStr.append(" and model.locationValue in (:locationValue)");  
	      }
		  queryStr.append(" group by model.locationValue order by model.locationValue asc");
		  
		  Query query = getSession().createQuery(queryStr.toString());
		    query.setParameter("locationScopeId", locationScopeId);
		    
		  if(fromDate!= null && toDate!=null){
			 query.setDate("fromDate", fromDate);
		     query.setDate("toDate", toDate);
		  }
		  if(locationValue != null && locationValue.size() > 0){
			query.setParameterList("locationValue", locationValue);  
		  }
		  
	     return query.list();
}
public List<Object[]> get2016TotalRenewalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Date fromDate,Date toDate){

StringBuilder queryStr = new StringBuilder();  

  queryStr.append(" select model.locationValue,sum(model.renewalCadre) from TdpCadreDateWiseInfo model where model.locationScopeId =:locationScopeId ");

  if(fromDate!= null && toDate!=null){
   queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
 }
if(locationValue != null && locationValue.size() > 0){
  queryStr.append(" and model.locationValue in (:locationValue)");  
}
queryStr.append(" group by model.locationValue order by model.locationValue asc");

Query query = getSession().createQuery(queryStr.toString());
  query.setParameter("locationScopeId", locationScopeId);
  
if(fromDate!= null && toDate!=null){
	 query.setDate("fromDate", fromDate);
   query.setDate("toDate", toDate);
}
if(locationValue != null && locationValue.size() > 0){
	query.setParameterList("locationValue", locationValue);  
}

return query.list();
}
public List<Object[]> get2016TotalNewCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate){

StringBuilder queryStr = new StringBuilder();  

queryStr.append(" select model.locationValue,sum(model.newCadre) from TdpCadreDateWiseInfo model where model.locationScopeId =:locationScopeId ");

if(fromDate!= null && toDate!=null){
   queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
 }
if(locationValue != null && locationValue.size() > 0){
  queryStr.append(" and model.locationValue in (:locationValue)");  
}
queryStr.append(" group by model.locationValue order by model.locationValue asc");

Query query = getSession().createQuery(queryStr.toString());
  query.setParameter("locationScopeId", locationScopeId);
  
if(fromDate!= null && toDate!=null){
	 query.setDate("fromDate", fromDate);
   query.setDate("toDate", toDate);
}
if(locationValue != null && locationValue.size() > 0){
	query.setParameterList("locationValue", locationValue);  
}

return query.list();
}
public List<Object[]> get2016TotalCadreCountBasedOnUserType(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate,Long userType){

StringBuilder queryStr = new StringBuilder();  

  queryStr.append(" select model.locationValue,sum(model.cadre2016) from TdpCadreDateWiseInfo model ");

 if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
    queryStr.append(" where model.locationScopeId =3 ");
}else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
  || userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
    queryStr.append(" where model.locationScopeId =4 ");
}
if(fromDate!= null && toDate!=null){
  queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
}
if(locationValue != null && locationValue.size() > 0){
queryStr.append(" and model.locationValue in (:locationValue)");  
}

queryStr.append(" group by model.locationValue order by model.locationValue asc ");

Query query = getSession().createQuery(queryStr.toString());
if(fromDate!= null && toDate!=null){
	 query.setDate("fromDate", fromDate);
   query.setDate("toDate", toDate);
}
if(locationValue != null && locationValue.size() > 0){
	query.setParameterList("locationValue", locationValue);  
}
 return query.list();
}
public List<Object[]> get2016TotalRenewalCadreCountBasedOnUserType(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate,Long userType){

StringBuilder queryStr = new StringBuilder();  

queryStr.append(" select model.locationValue,sum(model.renewalCadre) from TdpCadreDateWiseInfo model ");

if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
    queryStr.append(" where model.locationScopeId =3 ");
}else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
  || userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
    queryStr.append(" where model.locationScopeId =4 ");
}
if(fromDate!= null && toDate!=null){
  queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
}
if(locationValue != null && locationValue.size() > 0){
queryStr.append(" and model.locationValue in (:locationValue)");  
}

queryStr.append(" group by model.locationValue order by model.locationValue asc ");

Query query = getSession().createQuery(queryStr.toString());
if(fromDate!= null && toDate!=null){
	 query.setDate("fromDate", fromDate);
   query.setDate("toDate", toDate);
}
if(locationValue != null && locationValue.size() > 0){
	query.setParameterList("locationValue", locationValue);  
}
 return query.list();
}

	public Long getTotalCadreCountLocationWise(Long accessLvlId, List<Long> accessLvlValue, Long stateId, Date frmDt, Date toDt){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(TCDWI.cadre2016) from TdpCadreDateWiseInfo TCDWI where " +
						" TCDWI.locationScopeId = :accessLvlId and " +
						" TCDWI.locationValue in (:accessLvlValue)  ");
		if(frmDt != null && toDt != null){
			queryStr.append(" and (date(TCDWI.surveyDate) between :frmDt and :toDt) ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("accessLvlId", accessLvlId);
		query.setParameterList("accessLvlValue", accessLvlValue);
		if(frmDt != null && toDt != null){
			query.setDate("frmDt", frmDt);
			query.setDate("toDt", toDt);
		}
		return (Long) query.uniqueResult();  
	}
	public Long getTotalRenewlCadreLocationWise(Long accessLvlId, List<Long> accessLvlValue,Long stateId, Date frmDt, Date toDt){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(TCDWI.renewalCadre) from TdpCadreDateWiseInfo TCDWI where " +
						" TCDWI.locationScopeId = :accessLvlId and " +
						" TCDWI.locationValue in (:accessLvlValue)  ");
		if(frmDt != null && toDt != null){
			queryStr.append(" and (date(TCDWI.surveyDate) between :frmDt and :toDt) ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("accessLvlId", accessLvlId);
		query.setParameterList("accessLvlValue", accessLvlValue);
		if(frmDt != null && toDt != null){
			query.setDate("frmDt", frmDt);
			query.setDate("toDt", toDt);
		}
		return (Long) query.uniqueResult();
	} 
	public Long getTotalConstituencyForCdrRegStarted(Long StateId){  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct TCDWI.locationValue) from TdpCadreDateWiseInfo TCDWI where TCDWI.locationScopeId = 4 ");
		Query query = getSession().createQuery(queryStr.toString()); 
		return (Long) query.uniqueResult();    
	}
	public List<Object[]> get2016TotalCadreCountLocationWiseCount(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate){
	    
		StringBuilder queryStr = new StringBuilder();  
        
		if(locationValue != null && locationValue.size() > 0){
			queryStr.append(" select model.locationValue,sum(model.cadre2016) from TdpCadreDateWiseInfo model where model.locationScopeId =:locationScopeId ");
		}
        if(fromDate!= null && toDate!=null){
        	queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");   
        }
        if(locationValue != null && locationValue.size() > 0){
        	queryStr.append(" and model.locationValue in (:locationValue)");  
        }
        queryStr.append(" group by model.locationValue order by model.locationValue asc");
      
        Query query = getSession().createQuery(queryStr.toString());
        query.setParameter("locationScopeId", locationScopeId);
        
        if(fromDate!= null && toDate!=null){
        	query.setDate("fromDate", fromDate);  
        	query.setDate("toDate", toDate);
        }
        if(locationValue != null && locationValue.size() > 0){
        	query.setParameterList("locationValue", locationValue);  
        }
      
       return query.list();
	}
	public List<Object[]> get2016TotalRenewalCadreCountLocationWiseCount(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate){

		StringBuilder queryStr = new StringBuilder();  
  
		if(locationValue != null && locationValue.size() > 0){
			queryStr.append(" select model.locationValue,sum(model.renewalCadre) from TdpCadreDateWiseInfo model where model.locationScopeId =:locationScopeId ");
		}
		if(fromDate!= null && toDate!=null){
			queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");   
		}
		if(locationValue != null && locationValue.size() > 0){
			queryStr.append(" and model.locationValue in (:locationValue)");  
		}
		queryStr.append(" group by model.locationValue order by model.locationValue asc");
  
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("locationScopeId", locationScopeId);
    
		if(fromDate!= null && toDate!=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(locationValue != null && locationValue.size() > 0){
			query.setParameterList("locationValue", locationValue);  
		}
  
		return query.list();  
	}

}
