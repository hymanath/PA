package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreDateWiseInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreDateWiseInfoDAO extends GenericDaoHibernate<TdpCadreDateWiseInfo, Long> implements ITdpCadreDateWiseInfoDAO{

	public TdpCadreDateWiseInfoDAO() {
		super(TdpCadreDateWiseInfo.class);
	}
	
	public int deleteAllRecords(Date fromDate){
	    
		 StringBuilder sb = new StringBuilder();
		 sb.append(" delete from tdp_cadre_date_wise_info  ");
		 if(fromDate != null){
			sb.append(" where survey_date >= :fromDate "); 
		 }
   	Query query = getSession().createSQLQuery(sb.toString());
   	if(fromDate != null){
   		query.setDate("fromDate",fromDate );
   	}
   	
   	return query.executeUpdate();
   }
	 
	 public int setPrimaryKeyAutoIncrementToOne(){
	    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_date_wise_info AUTO_INCREMENT = 1 ");
	    	return query.executeUpdate();
	  }
    
    public List<Object[]> get2016TotalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Date fromDate,Date toDate){
		
	      StringBuilder queryStr = new StringBuilder();  
	      queryStr.append(" select model.locationValue,sum(model.cadre2016) from TdpCadreLocationInfo model where model.locationScopeId =:locationScopeId and model.type='Total' ");
	    /*  if(fromDate!= null && toDate!=null){
	   	    queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
	   	  }*/
	      if(locationValue != null && locationValue.size() > 0){
	    	queryStr.append(" and model.locationValue in (:locationValue)");  
	      }
		  queryStr.append(" group by model.locationValue order by model.locationValue asc");
		  
		  Query query = getSession().createQuery(queryStr.toString());
		    query.setParameter("locationScopeId", locationScopeId);
		    
		 /* if(fromDate!= null && toDate!=null){
			 query.setDate("fromDate", fromDate);
		     query.setDate("toDate", toDate);
		  }*/
		  if(locationValue != null && locationValue.size() > 0){
			query.setParameterList("locationValue", locationValue);  
		  }
		  
	     return query.list();
}
public List<Object[]> get2016TotalRenewalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Date fromDate,Date toDate){

StringBuilder queryStr = new StringBuilder();  

queryStr.append(" select model.locationValue,sum(model.renewalCadre) from TdpCadreLocationInfo model where model.locationScopeId =:locationScopeId and model.type='Total' ");

/*if(fromDate!= null && toDate!=null){
	queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
}*/
if(locationValue != null && locationValue.size() > 0){
	queryStr.append(" and model.locationValue in (:locationValue)");  
}
queryStr.append(" group by model.locationValue order by model.locationValue asc");

Query query = getSession().createQuery(queryStr.toString());
query.setParameter("locationScopeId", locationScopeId);

/*if(fromDate!= null && toDate!=null){
	 query.setDate("fromDate", fromDate);
 query.setDate("toDate", toDate);
}*/
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
   public List<Object[]> get2016TotalCadreCountBasedOnUserType(List<Long> locationValue,Date fromDate,Date toDate,Long userTypeId,Long activityMemberId){
	
		        StringBuilder queryStr = new StringBuilder();  
		        queryStr.append("select distinct ");
    		    if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
					 	  if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
					  		 queryStr.append(" model1.district.districtId, ");; 
					  	  }else{
					  		 queryStr.append(" model2.districtId, ");
					  	  }  
			   }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
				|| userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID 
				|| userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
					   if(activityMemberId != null && activityMemberId.longValue()==53l){
			      		  queryStr.append(" model4.constituencyId,");	  
			           }else{
				 	     queryStr.append(" model1.constituencyId,");  
			           }
			   }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
				     queryStr.append(" model3.assemblyId,") ; 
			   }
    		    	queryStr.append(" sum(model.cadre2016) from TdpCadreLocationInfo model ");
		      if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
			   	      if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
			      		 queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model.locationScopeId=4 and model1.electionScope.electionScopeId=2 and model1.deformDate is null ");; 
			      	  }else{
			      		 queryStr.append(" ,District model2 where model2.districtId = model.locationValue and model.locationScopeId=3 ");
			      	  }  
		      }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
			  || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
			  || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
			    	  if(activityMemberId != null && activityMemberId.longValue()==53l){//53 ActivityMemberId person is one District President.He has access some constituencies Those Constituencies has mapped in this Table.
			        	  queryStr.append(" ,DistrictConstituencies model4 where model4.constituencyId=model.locationValue and model.locationScopeId=4 ");
			          }else{
			   	       queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model1.electionScope.electionScopeId=2 and model.locationScopeId=4 and model1.deformDate is null ");
			          }
		      }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		         	  queryStr.append(" ,ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.locationScopeId=4 ");	 
		      }
		      if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID ){
		     	  if(locationValue != null && locationValue.size() > 0){
			     		 if(activityMemberId != null && activityMemberId.longValue()==53l){
		           			 queryStr.append("  and model4.districtId in (:locationValue) ");
		           		 }else{
		           			 queryStr.append("  and model1.district.districtId in (:locationValue) "); 
		           		 }
		   	      }
		     	 if(activityMemberId != null && activityMemberId.longValue()==50l){//50 ActivityMemberId person is one District President.He has Access only this constituency(354,355,356,357,358,368).so that we are removing remaining constituencies
			    	  queryStr.append(" and model1.constituencyId not in (133,134,135,136,137,138,140,141,359)");
			     }
			  }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
				 if(locationValue != null && locationValue.size() > 0){
					 queryStr.append(" and model3.parliamentId in (:locationValue)"); 
				 } 
			 }else if(userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID && locationValue != null && locationValue.size() > 0){
	         		queryStr.append(" and model1.district.districtId in (:locationValue) ");
	         }else if(locationValue != null && locationValue.size() > 0){
		 	 	    queryStr.append(" and model.locationValue in (:locationValue)");  
		 	 }
		     queryStr.append(" and model.type='Total' ");
		  /* 	if(fromDate!= null && toDate!=null){
			      queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
		   	}*/
			if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
			     if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
		   		 queryStr.append(" group by model1.district.districtId ");; 
			     }else{
		   		 queryStr.append(" group by model2.districtId ");
			     }   
		   }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
		     || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
		     || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID 
		     || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
			    if(activityMemberId != null && activityMemberId.longValue()==53l){
				 queryStr.append("  group by model4.constituencyId ");
			    }else{
			     queryStr.append("  group by model1.constituencyId ");
			    }
		   }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
			  queryStr.append("  group by  model3.assemblyId ");
		   }
		  Query query = getSession().createQuery(queryStr.toString());
		 /* if(fromDate!= null && toDate!=null){
			 query.setDate("fromDate", fromDate);
		     query.setDate("toDate", toDate);
		  }*/
		  if(locationValue != null && locationValue.size() > 0){
			query.setParameterList("locationValue", locationValue);  
		  }
  return query.list();
  }
  public List<Object[]> get2016TotalRenewalCadreCountBasedOnUserType(List<Long> locationValue,Date fromDate,Date toDate,Long userTypeId,Long activityMemberId){
		
         StringBuilder queryStr = new StringBuilder();  
         queryStr.append("select distinct ");
         if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
      	     if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
           		 queryStr.append(" model1.district.districtId, ");; 
           	  }else{
           		 queryStr.append(" model2.districtId, ");
           	  }   
         }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
   	     || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
   	     || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID 
   	     || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
        	 if(activityMemberId != null && activityMemberId.longValue()==53l){
         		  queryStr.append(" model4.constituencyId,");	  
              }else{
            	  queryStr.append(" model1.constituencyId,");  
              }
         }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
    	     queryStr.append(" model3.assemblyId,") ; 
         }
         queryStr.append(" sum(model.renewalCadre) from TdpCadreLocationInfo model ");
         if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
       	      if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
         		 queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model.locationScopeId=4 and model1.electionScope.electionScopeId=2 and model1.deformDate is null ");; 
         	  }else{
         		 queryStr.append(" ,District model2 where model2.districtId = model.locationValue and model.locationScopeId=3 ");
         	  }
         }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
   	      || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
   	      || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID 
   	      || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
        	 if(activityMemberId != null && activityMemberId.longValue()==53l){//53 ActivityMemberId person is one District President.He has access some constituencies Those Constituencies has mapped in this Table.
           	  queryStr.append(" ,DistrictConstituencies model4 where model4.constituencyId=model.locationValue and model.locationScopeId=4 ");
             }else{
       	     queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model1.electionScope.electionScopeId=2 and model.locationScopeId=4 and model1.deformDate is null ");
             }
          }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
        	  queryStr.append(" ,ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.locationScopeId=4 ");	 
          }
         if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID ){
	     	  if(locationValue != null && locationValue.size() > 0){
		     		 if(activityMemberId != null && activityMemberId.longValue()==53l){
	           			 queryStr.append("  and model4.districtId in (:locationValue) ");
	           		 }else{
	           			 queryStr.append("  and model1.district.districtId in (:locationValue) "); 
	           		 }	
	     	  }
	     	  if(activityMemberId != null && activityMemberId.longValue()==50l){//50 ActivityMemberId person is one District President.He has Access only this constituency(354,355,356,357,358,368).so that we are removing remaining constituencies
    	    	  queryStr.append(" and model1.constituencyId not in (133,134,135,136,137,138,140,141,359)");
    	      }
		 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
			 if(locationValue != null && locationValue.size() > 0){
				 queryStr.append(" and model3.parliamentId in (:locationValue)"); 
			 } 
		 }else if(userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID && locationValue != null && locationValue.size() > 0){
      		queryStr.append(" and model1.district.districtId in (:locationValue) ");
      	 }else if(locationValue != null && locationValue.size() > 0){
		 	    queryStr.append(" and model.locationValue in (:locationValue)");  
		 }
          queryStr.append(" and model.type='Total' ");
      /*  if(fromDate!= null && toDate!=null){
		    queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
		}*/
	    if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		  	     if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
		      		 queryStr.append(" group by model1.district.districtId ");; 
		      	  }else{
		      		 queryStr.append(" group by model2.districtId ");
		      	  }   
		 }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	      || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
	      || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID 
	      || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
			    if(activityMemberId != null && activityMemberId.longValue()==53l){
		  			 queryStr.append("  group by model4.constituencyId ");
		  		 }else{
				     queryStr.append("  group by model1.constituencyId ");
		  		 }
		 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
			   queryStr.append("  group by  model3.assemblyId ");
		 }
	   Query query = getSession().createQuery(queryStr.toString());
	/*  if(fromDate!= null && toDate!=null){
		 query.setDate("fromDate", fromDate);
	     query.setDate("toDate", toDate);
	  }*/
	  if(locationValue != null && locationValue.size() > 0){
		query.setParameterList("locationValue", locationValue);  
	  }
	   return query.list();
}

	public Long getTotalCadreCountLocationWise(Long accessLvlId, List<Long> accessLvlValue, Long stateId, Date frmDt, Date toDt){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(TCDWI.cadre2016) from TdpCadreDateWiseInfo TCDWI where ");
		if(accessLvlId.longValue() == 5l){
			accessLvlId = 4l;
		}else if(accessLvlId.longValue() == 4l){
			accessLvlId = 10l;
		}
		queryStr.append(" TCDWI.locationScopeId = :accessLvlId and " +    
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
		queryStr.append(" select sum(TCDWI.renewalCadre) from TdpCadreDateWiseInfo TCDWI where ");
		if(accessLvlId.longValue() == 5l){
			accessLvlId = 4l;
		}else if(accessLvlId.longValue() == 4l){
			accessLvlId = 10l;
		}
		queryStr.append(" TCDWI.locationScopeId = :accessLvlId and " +
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
	public List<Object[]> get2016TotalCadreCountLocationWiseCount(Long locationScopeId,List<Long> locationValue,Long stateId,String type,Date fromDate,Date toDate){
	    
		StringBuilder queryStr = new StringBuilder();  
      
	  if(locationValue != null && locationValue.size() > 0){
			queryStr.append(" select model.locationValue,sum(model.cadre2016) from TdpCadreLocationInfo model where model.locationScopeId =:locationScopeId ");
		}
      if(fromDate!= null && toDate!=null){ 
      	queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");   
      }
      if(locationValue != null && locationValue.size() > 0){
      	queryStr.append(" and model.locationValue in (:locationValue) ");  
      }
      if(type.equalsIgnoreCase("total")){
    	  queryStr.append(" and model.type = 'Total' ");
      }else{
    	  queryStr.append(" and model.type = 'Today' ");  
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
	}//aaaaa
	public List<Object[]> get2016TotalRenewalCadreCountLocationWiseCount(Long userAccessLevelId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate){

		StringBuilder queryStr = new StringBuilder(); 
		queryStr.append(" select ");
		if(userAccessLevelId.equals(4l)){
			queryStr.append(" C.district.districtId ");
		}else if(userAccessLevelId.equals(10l)){
			queryStr.append(" C.district.districtId ");
		}else{
			queryStr.append(" model.locationValue ");
		}
		queryStr.append(" ,sum(model.renewalCadre) "); 
		
		queryStr.append(" from TdpCadreDateWiseInfo model");
		if(userAccessLevelId.equals(4l)){
			queryStr.append(" , Constituency C ");
		}else if(userAccessLevelId.equals(10l)){
			queryStr.append(" ,ParliamentAssembly PA, Constituency C ");
		}
		queryStr.append(" where model.locationScopeId =:locationScopeId and ");
		if(userAccessLevelId.equals(4l)){
        	queryStr.append("  model.locationValue = C.constituencyId and ");
        }else if(userAccessLevelId.equals(10l)){
        	queryStr.append(" model.locationValue = PA.parliamentId " +
        					" and PA.assemblyId = C.constituencyId and ");
        }
		
      if(fromDate!= null && toDate!=null){  
      	queryStr.append("  date(model.surveyDate) between :fromDate and :toDate and ");   
      }
      if(locationValue != null && locationValue.size() > 0){
      	queryStr.append(" model.locationValue in (:locationValue)");    
      }
      if(userAccessLevelId.equals(4l)){
   	   queryStr.append(" group by C.district.districtId order by C.district.districtId asc ");
      }else if(userAccessLevelId.equals(10l)){
   	   queryStr.append(" group by C.district.districtId order by C.district.districtId asc ");
      }else{
   	   queryStr.append(" group by model.locationValue order by model.locationValue asc ");
      }
      Query query = getSession().createQuery(queryStr.toString());
      query.setParameter("locationScopeId", userAccessLevelId);
      
      if(fromDate!= null && toDate!=null){
      	query.setDate("fromDate", fromDate);  
      	query.setDate("toDate", toDate);
      }
      if(locationValue != null && locationValue.size() > 0){
      	query.setParameterList("locationValue", locationValue);  
      }
    
     return query.list();   
	}

    
public List<Object[]> getDateWiseLocationsRegistrationsDetails(GISVisualizationParameterVO inputVO){
		
		try {
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select distinct ");
				
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  district.districtId,district.districtName as name ,'','','',''," +
						" model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.constituencyId,constituency.name as name ,constituency.areaType,'','',''," +
						"model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
					queryStr.append(" booth.boothId  as name  ,booth.partNo ,'','','',''," +
							"model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" booth.tehsil.tehsilId , booth.tehsil.tehsilName  as name  ,'RURAL','','',''," +
							"model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL))
					queryStr.append(" '','','',booth.localBody.localElectionBodyId,booth.localBody.name,'"+IConstants.MUNCIPALITY_CORPORATION_LEVEL+"'," +
							"model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
				queryStr.append(" booth.panchayat.panchayatId , booth.panchayat.panchayatName  as name  ,'Panchayat','','',''," +
						"model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				/*queryStr.append(" booth.boothId  as name  ,booth.partNo ,'POLLINGSTATION','','',''," +
						"model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent");*/
				queryStr.append(" booth.panchayat.panchayatId , booth.panchayat.panchayatName  as name  ,'Panchayat','','',''," +
						"model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent");
			}/*else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" '','','',booth.localBody.localElectionBodyId,booth.localBody.name,'"+IConstants.MUNCIPALITY_CORPORATION_LEVEL+"'," +
						"model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent");
			}*/
			
			queryStr.append(" from ");
			queryStr.append(" TdpCadreDateWiseInfo model ");
			
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  ,District district ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT) ){			
				queryStr.append(" ,Constituency constituency ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){			
				queryStr.append(" ,Booth booth ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append(" ,Booth booth ");
			}
			
			queryStr.append(" where  ");
			
			if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
				queryStr.append("  (date(model.surveyDate) between :startDate and :endDate) ");
			}
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && 
					 inputVO.getDistrictId() != null && inputVO.getDistrictId().longValue()>0L ){
				queryStr.append(" and booth.constituency.district.districtId = :districtId ");
				queryStr.append(" and model.locationScopeId = 5 and model.locationValue = booth.tehsil.tehsilId  ");
			}
			else if(inputVO.getParentLocationType() != null && inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append("  and model.locationScopeId = 3 and model.locationValue = district.districtId and district.state.stateId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and district.districtId = :childLocationTypeId ");
					}
				}
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and  model.locationScopeId = 4 and model.locationValue = constituency.constituencyId  ");
					if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
						queryStr.append(" and (constituency.district.districtId between 11 and 23) ");
					else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
						queryStr.append(" and (constituency.district.districtId between 1 and 10) ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and constituency.constituencyId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					
						
						if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN)){
							queryStr.append(" and booth.constituency.constituencyId = :parentLocationTypeId ");
							queryStr.append(" and model.locationScopeId = 9  and model.locationValue = booth.boothId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.boothId = :childLocationTypeId ");
							}
						}
						else if(inputVO.getAreaType().equalsIgnoreCase(IConstants.RURAL) ){
							queryStr.append(" and booth.constituency.district.districtId = :districtId ");
							queryStr.append(" and model.locationScopeId = 5 and model.locationValue = booth.tehsil.tehsilId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.tehsil.tehsilId = :childLocationTypeId ");
							}
						}
						else if((inputVO.getAreaType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ) ){
							queryStr.append(" and booth.constituency.constituencyId = :parentLocationTypeId ");
							queryStr.append(" and model.locationScopeId = 7 and model.locationValue = booth.localBody.localElectionBodyId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append("  and booth.localBody.localElectionBodyId = :childLocationTypeId ");
							}
						}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append("   and model.locationScopeId = 6 and model.locationValue = booth.panchayat.panchayatId and  booth.tehsil.tehsilId = :parentLocationTypeId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and  booth.tehsil.tehsilId = :childLocationTypeId ");
					}
				}/*else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append(" and  model.locationScopeId = 7 and model.locationValue = booth.localBody.localElectionBodyId and booth.constituency.constituencyId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and booth.localBody.localElectionBodyId = :childLocationTypeId ");
					}
				}*/else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append("   and model.locationScopeId = 6 and model.locationValue = booth.panchayat.panchayatId and  booth.tehsil.tehsilId = :parentLocationTypeId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and  booth.tehsil.tehsilId = :childLocationTypeId ");
					}
					/*queryStr.append(" and  model.locationScopeId = 6 and model.locationValue = booth.boothId and booth.panchayat.panchayatId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and booth.boothId = :childLocationTypeId ");
					}*/
				}
			}
			
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
			if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
				queryStr.append(" and (district.districtId between 11 and 23) ");
			else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
				queryStr.append(" and (district.districtId between 1 and 10) ");
			}
			
			queryStr.append(" group by ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" district.districtId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.constituencyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
					queryStr.append(" booth.boothId ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" booth.tehsil.tehsilId ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL))
					queryStr.append(" booth.localBody.localElectionBodyId ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append(" booth.tehsil.tehsilId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" booth.localBody.localElectionBodyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append(" booth.panchayat.panchayatId ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT))
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if(inputVO.getChildLocationTypeId() != null && inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());
			if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				query.setDate("startDate", format.parse(inputVO.getStartDate()));
				query.setDate("endDate", format.parse(inputVO.getEndDate()));
			}
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && 
					 inputVO.getDistrictId() != null && inputVO.getDistrictId().longValue()>0L )
				query.setParameter("districtId", inputVO.getDistrictId());
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insertTdpCadreLocInfoDateWiseUpToConstituencyLevel(){
		
		Query query = getSession().createSQLQuery("" +
		"  INSERT INTO tdp_cadre_date_wise_info( survey_date,location_scope_id,location_value,cadre_2016,cadre_2016_percent," +
		"                                        new_cadre,new_cadre_percent,renewal_cadre,renewal_cadre_percent,inserted_time" +
		"                                       ) " +
	    "         SELECT TEMP.survey_date,TEMP.location_scope_id,TEMP.location_value,TEMP.cadre_2016,TEMP.cadre_2016_percent," +
	    "                TEMP.new_cadre,TEMP.new_cadre_percent,TEMP.renewal_cadre,TEMP.renewal_cadre_percent,TEMP.inserted_time " +
	    "         FROM   tdp_cadre_date_wise_info_temp TEMP " );
		return query.executeUpdate();
	}
	public List<Object[]> get2016TotalCadreCountLocationWiseCountSpecial(Long userAccessLevelId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate){
	    
		StringBuilder queryStr = new StringBuilder(); 
		queryStr.append(" select ");
		if(userAccessLevelId.equals(4l)){
			queryStr.append(" C.district.districtId ");
		}else if(userAccessLevelId.equals(10l)){
			queryStr.append(" C.district.districtId ");
		}else{
			queryStr.append(" model.locationValue ");
		}
		queryStr.append(" ,sum(model.cadre2016) ");
		
		queryStr.append(" from TdpCadreDateWiseInfo model");
		if(userAccessLevelId.equals(4l)){
			queryStr.append(" , Constituency C ");
		}else if(userAccessLevelId.equals(10l)){
			queryStr.append(" ,ParliamentAssembly PA, Constituency C ");
		}
		queryStr.append(" where model.locationScopeId =:locationScopeId and ");
		if(userAccessLevelId.equals(4l)){
        	queryStr.append("  model.locationValue = C.constituencyId and ");
        }else if(userAccessLevelId.equals(10l)){
        	queryStr.append(" model.locationValue = PA.parliamentId " +
        					" and PA.assemblyId = C.constituencyId and ");
        }
		
      if(fromDate!= null && toDate!=null){  
      	queryStr.append("  date(model.surveyDate) between :fromDate and :toDate and ");   
      }
      if(locationValue != null && locationValue.size() > 0){
      	queryStr.append(" model.locationValue in (:locationValue)");    
      }
      if(userAccessLevelId.equals(4l)){
   	   queryStr.append(" group by C.district.districtId order by C.district.districtId asc ");
      }else if(userAccessLevelId.equals(10l)){
   	   queryStr.append(" group by C.district.districtId order by C.district.districtId asc ");
      }else{
   	   queryStr.append(" group by model.locationValue order by model.locationValue asc ");
      }
      Query query = getSession().createQuery(queryStr.toString());
      query.setParameter("locationScopeId", userAccessLevelId);
      
      if(fromDate!= null && toDate!=null){
      	query.setDate("fromDate", fromDate);  
      	query.setDate("toDate", toDate);
      }
      if(locationValue != null && locationValue.size() > 0){
      	query.setParameterList("locationValue", locationValue);  
      }
    
     return query.list();  
	}
	 public List<Object[]> getConstitiuencyWise2016CadreCountBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue,Date fromDate,Date toDate){
	    	
	   	 StringBuilder queryStr = new StringBuilder();  
	   	    
	        queryStr.append(" select distinct ");
	        
	        if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
	        	queryStr.append(" model2.constituencyId,");
	        }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        	queryStr.append(" model3.assemblyId,");
	        }else{
	       	queryStr.append(" model.locationValue,"); 
	        }
	        queryStr.append(" sum(model.cadre2016) from TdpCadreLocationInfo model ");
	        
	       if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
	       	queryStr.append(",Constituency model2 where model2.constituencyId = model.locationValue and model2.electionScope.electionScopeId=2 and model2.deformDate is null and model.locationScopeId=4 ");
	       }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       	queryStr.append(",ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.locationScopeId=4d ");
	       }else {
	         queryStr.append(" where model.locationScopeId=4 ");
	       }
	     
	     /*  if(fromDate!= null && toDate!=null){
		   	    queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
		   	  }*/
	       
	       if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID && locationValue != null && locationValue.size() > 0){
	       	queryStr.append(" and model2.district.districtId in (:locationValue) ");
	       }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID && locationValue != null && locationValue.size() > 0){
	       	queryStr.append(" and model3.parliamentId in (:locationValue) ");
	       }else {
	       	if(locationValue != null && locationValue.size() > 0){
	   	 	 	queryStr.append(" and model.locationValue in (:locationValue)");  
	   	    }
	       }
	       queryStr.append(" and model.type='Total' ");
	       if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
	       	queryStr.append(" group by model2.constituencyId ");
	       }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       	queryStr.append(" group by model3.assemblyId ");
	       }else {
	       	queryStr.append(" group by  model.locationValue ");
	       }
	   	  Query query = getSession().createQuery(queryStr.toString());
	   /*	 if(fromDate!= null && toDate!=null){
			 query.setDate("fromDate", fromDate);
		     query.setDate("toDate", toDate);
		  }*/
	   	  if(locationValue != null && locationValue.size() > 0){
	   		query.setParameterList("locationValue", locationValue);  
	   	  }
	   	  return query.list();
	   }
	 public List<Object[]> getConstitiuencyWise2016RenewalCadreCountBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue,Date fromDate,Date toDate){
	    	
	   	 StringBuilder queryStr = new StringBuilder();  
	   	    
	        queryStr.append(" select distinct ");
	        
	        if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
	        	queryStr.append(" model2.constituencyId,");
	        }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        	queryStr.append(" model3.assemblyId,");
	        }else{
	       	    queryStr.append(" model.locationValue,"); 
	        }
	        queryStr.append(" sum(model.renewalCadre) from TdpCadreLocationInfo model ");
	        
	       if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
	       	queryStr.append(",Constituency model2 where model2.constituencyId = model.locationValue and model2.electionScope.electionScopeId=2 and model2.deformDate is null and model.locationScopeId=4 ");
	       }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       	queryStr.append(",ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.locationScopeId=4 ");
	       }else {
	         queryStr.append(" where model.locationScopeId=4 ");
	       }
	    /*   if(fromDate!= null && toDate!=null){
		   	    queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
		   	}*/
	       
	       if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID && locationValue != null && locationValue.size() > 0){
	       	queryStr.append(" and model2.district.districtId in (:locationValue) ");
	       }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID && locationValue != null && locationValue.size() > 0){
	       	queryStr.append(" and model3.parliamentId in (:locationValue) ");
	       }else {
	       	if(locationValue != null && locationValue.size() > 0){
	   	 	 	queryStr.append(" and model.locationValue in (:locationValue)");  
	   	    }
	       }
	       queryStr.append(" and model.type='Total' ");
	       if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
	       	queryStr.append(" group by model2.constituencyId ");
	       }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       	queryStr.append(" group by model3.assemblyId ");
	       }else {
	       	queryStr.append(" group by model.locationValue ");
	       }
	   	    Query query = getSession().createQuery(queryStr.toString());
	   	  /* if(fromDate!= null && toDate!=null){
			 query.setDate("fromDate", fromDate);
		     query.setDate("toDate", toDate);
		   }*/
	   	  if(locationValue != null && locationValue.size() > 0){
	   		query.setParameterList("locationValue", locationValue);  
	   	  }
	   	  return query.list();
	   }
	 	public Long getTotalCadreCountLocationWiseTS(Long stateId, Date frmDt, Date toDt){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select sum(TCDWI.cadre2016) from TdpCadreDateWiseInfo TCDWI where ");
			
			queryStr.append(" TCDWI.locationScopeId = :accessLvlId and " +    
							" TCDWI.locationValue in (:accessLvlValue)  ");
			if(frmDt != null && toDt != null){
				queryStr.append(" and (date(TCDWI.surveyDate) between :frmDt and :toDt) ");  
			}
			Query query = getSession().createQuery(queryStr.toString());  
			query.setParameter("accessLvlId", 2l);    
			//List<Long> stateIdList = new ArrayList<Long>
			query.setParameterList("accessLvlValue", new ArrayList<Long>(){{
				add(36l);
			}});
			if(frmDt != null && toDt != null){  
				query.setDate("frmDt", frmDt);  
				query.setDate("toDt", toDt);
			}
			return (Long) query.uniqueResult();  
		}
	 	public Long getTotalRenewlCadreLocationWiseTS(Long stateId, Date frmDt, Date toDt){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select sum(TCDWI.renewalCadre) from TdpCadreDateWiseInfo TCDWI where ");
			
			queryStr.append(" TCDWI.locationScopeId = :accessLvlId and " +
							" TCDWI.locationValue in (:accessLvlValue)  ");
			if(frmDt != null && toDt != null){
				queryStr.append(" and (date(TCDWI.surveyDate) between :frmDt and :toDt) ");
			}
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("accessLvlId", 2l);
			query.setParameterList("accessLvlValue", new ArrayList<Long>(){{add(36l);}});
			if(frmDt != null && toDt != null){
				query.setDate("frmDt", frmDt);  
				query.setDate("toDt", toDt);
			}
			return (Long) query.uniqueResult();
		} 
	 	
	 	public List<Object[]> getActualCountOfCadreSurveyUser(Long districtId,Long stateId,Date startDate,Date endDate,Long constituencyId){
	 		
	 		StringBuilder queryStr = new StringBuilder();
	 		queryStr.append(" select ");
	 		
	 		if(districtId != null && districtId.longValue()>0l){
	 			queryStr.append(" distinct c.constituencyId,c.name ");
	 		}else{
	 			queryStr.append(" distinct d.districtId,d.districtName ");
	 		}
	 		
	 		queryStr.append(" ,sum(TCDWI.cadre2016) " +
	 				" from TdpCadreDateWiseInfo TCDWI "); 
	 		if(constituencyId != null && constituencyId.longValue()>0l){
	 			queryStr.append(" ,Constituency c  where c.constituencyId = :constituencyId " );
	 		}else if(districtId != null && districtId.longValue()>0l){
	 			queryStr.append(" ,Constituency c where c.district.districtId = :districtId  " );
	 		}else{
	 			queryStr.append(" ,District d where d.districtId = TCDWI.locationValue " );
	 		}
	 		
	 		if(constituencyId != null && constituencyId.longValue()>0l){
	 			queryStr.append(" and TCDWI.locationScopeId = 4  and TCDWI.locationValue = :constituencyId ");
	 		}else if(districtId != null && districtId.longValue()>0l){
	 			queryStr.append(" and TCDWI.locationScopeId = 4 and  c.constituencyId = TCDWI.locationValue");
	 		}else{
	 			if(stateId != null && stateId.longValue() == 1l){
	 				queryStr.append(" and TCDWI.locationScopeId = 3 and (TCDWI.locationValue between 11 and 23) ");
	 			}else if(stateId != null && stateId.longValue() == 36l && stateId.longValue() == 2l ){
					queryStr.append(" and TCDWI.locationScopeId = 3 and (TCDWI.locationValue between  1 and 10 ) ");
				}
	 		}
	 		
	 		if(startDate != null && endDate != null){
	 			queryStr.append(" and (date(TCDWI.surveyDate) between :startDate and :endDate) ");
	 		 }
	 		if(constituencyId != null && constituencyId.longValue()>0l){
	 			queryStr.append(" group by c.constituencyId ");
	 		}else if(districtId != null && districtId.longValue()>0l){   
	 			queryStr.append(" group by c.constituencyId ");
	 		}else {
	 			queryStr.append(" group by d.districtId ");
	 		}
	 		
	 		Query query = getSession().createQuery(queryStr.toString());
	 		
	 		
	 	    if(constituencyId != null && constituencyId.longValue()>0l){
	 			query.setParameter("constituencyId", constituencyId);	
	 		}else if(districtId != null && districtId.longValue()>0l){
	 			query.setParameter("districtId", districtId);
	 		}
	 		/*if(stateId != null && stateId.longValue() > 0l){
	 			query.setParameter("stateId", stateId);
	 		}*/
	 		
	 		if(startDate != null && endDate != null){
	 			query.setDate("startDate", startDate);
	 			query.setDate("endDate", endDate);
	 		}
	 		return query.list();
	 		
	 	}
	 	public List<Object[]> get2016CadreCntDistWise(List<Long> constituencyIds,Date fromDate,Date toDate,String districtName){
			
		       StringBuilder queryStr = new StringBuilder();  
		        queryStr.append("select ");
		        
		       if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam")){
		    	   queryStr.append(" model1.district.districtId,");  
		       }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
		    	   queryStr.append(" model2.districtId,"); 
		       }
		        queryStr.append(" sum(model.cadre2016) from TdpCadreDateWiseInfo model ");
		        if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam")){
		         queryStr.append(" ,Constituency model1 where model1.constituencyId=model.locationValue and model1.electionScope.electionScopeId=2 " +
							    "  and model1.deformDate is null ");
		        }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
		        	 queryStr.append(" ,DistrictConstituencies model2 where model2.constituencyId=model.locationValue ");
		        }
		        if(fromDate!= null && toDate!=null){
			   	    queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
			   }
			   if(constituencyIds != null && constituencyIds.size() > 0){
		   	     queryStr.append(" and model.locationValue in(:constituencyIds) ");
		       }
		       queryStr.append(" and model.locationScopeId=:locationScopeId ");
		       if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam")){
		    	   queryStr.append(" group by model1.district.districtId ");  
		       }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
		    	   queryStr.append(" group by model2.districtId "); 
		       }
		       Query query = getSession().createQuery(queryStr.toString());
			   if(constituencyIds != null && constituencyIds.size() > 0){
			    query.setParameterList("constituencyIds", constituencyIds);
			   }
			   if(fromDate != null && toDate != null){
		 			query.setDate("fromDate", fromDate);
		 			query.setDate("toDate", toDate);
		 		}
			   query.setParameter("locationScopeId", 4l);
		   return query.list();
	}
	 	public List<Object[]> get2016RenewalCadreCntDistWise(List<Long> constituencyIds,Date fromDate,Date toDate,String districtName){
			
		       StringBuilder queryStr = new StringBuilder();  
		        queryStr.append("select ");
		        
		       if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam")){
		    	   queryStr.append(" model1.district.districtId,");  
		       }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
		    	   queryStr.append(" model2.districtId,"); 
		       }
		        queryStr.append(" sum(model.renewalCadre) from TdpCadreDateWiseInfo model ");
		        if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam") ){
		         queryStr.append(" ,Constituency model1 where model1.constituencyId=model.locationValue and model1.electionScope.electionScopeId=2 " +
							    "  and model1.deformDate is null ");
		        }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
		        	 queryStr.append(" ,DistrictConstituencies model2 where model2.constituencyId=model.locationValue ");
		        }
		        if(fromDate!= null && toDate!=null){
			   	    queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
			   }
			   if(constituencyIds != null && constituencyIds.size() > 0){
		   	     queryStr.append(" and model.locationValue in(:constituencyIds) ");
		       }
		       queryStr.append(" and model.locationScopeId=:locationScopeId ");
		       if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam")){
		    	   queryStr.append(" group by model1.district.districtId ");  
		       }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
		    	   queryStr.append(" group by model2.districtId "); 
		       }
		       Query query = getSession().createQuery(queryStr.toString());
			   if(constituencyIds != null && constituencyIds.size() > 0){
			    query.setParameterList("constituencyIds", constituencyIds);
			   }
			   if(fromDate != null && toDate != null){
		 			query.setDate("fromDate", fromDate);
		 			query.setDate("toDate", toDate);
		 		}
			   query.setParameter("locationScopeId", 4l);
		   return query.list();
	}
	 	
	 public List<Object[]> getLocationWiseDetailedOverViewDetails(Date fromDate,Date toDate,String locationType,Long locationVal){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select");
		 if(locationType != null && locationType.equalsIgnoreCase("district"))
			sb.append(" d.districtId,d.districtName,");
		 else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
			sb.append(" c.constituencyId,c.name,");
		 
		 sb.append(" sum(model.cadre2016)" +
		 			" from TdpCadreDateWiseInfo model");
		 if(locationType != null && locationType.equalsIgnoreCase("district")){
			 sb.append(" ,District d where model.locationScopeId = 3" +
			 		" and model.locationValue = d.districtId");
		 }
		 else if(locationType != null && locationType.equalsIgnoreCase("constituency")){
			sb.append(",Constituency c where model.locationScopeId = 4" +
					" and model.locationValue = c.constituencyId");
		 }
		 if(fromDate != null && toDate != null){
			 sb.append(" and date(model.surveyDate) between :fromDate and :toDate");
		 }
		 if(locationType != null && locationType.equalsIgnoreCase("district")){
			 if(locationVal != null && locationVal.longValue() == 1l){
				 sb.append(" and d.districtId between 11 and 23");
			 }
			 else if(locationVal != null && locationVal.longValue() == 36l){
				 sb.append(" and d.districtId between 1 and 10");
			 }
		 }
		 else if(locationType != null && locationType.equalsIgnoreCase("constituency") && locationVal != null && locationVal.longValue() > 0l)
			 sb.append(" and c.district.districtId = :locationVal");
		 
		 if(locationType != null && locationType.equalsIgnoreCase("district"))
			sb.append(" group by d.districtId order by d.districtName");
		 else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
			sb.append(" group by c.constituencyId order by c.name");
		 
		 Query query = getSession().createQuery(sb.toString());
		 if(locationType != null && locationType.equalsIgnoreCase("constituency") && locationVal != null && locationVal.longValue() > 0l)
			 query.setParameter("locationVal", locationVal);
		 if(fromDate != null && toDate != null){
			 query.setDate("fromDate", fromDate);
			 query.setDate("toDate", toDate);
		 }
		 return query.list();
	 }
}
