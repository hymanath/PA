package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
   	query.setDate("fromDate",fromDate );
   	
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
		|| userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
	 	     queryStr.append(" model1.constituencyId,");  
	   }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		     queryStr.append(" model3.assemblyId,") ; 
	   }
       queryStr.append(" sum(model.cadre2016) from TdpCadreDateWiseInfo model ");
    
      if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
   	      if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
      		 queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model.locationScopeId=4 and model1.electionScope.electionScopeId=2 and model1.deformDate is null ");; 
      	  }else{
      		 queryStr.append(" ,District model2 where model2.districtId = model.locationValue and model.locationScopeId=3 ");
      	  }  
      }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	  || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
   	       queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model1.electionScope.electionScopeId=2 and model.locationScopeId=4 and model1.deformDate is null "); 
      }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
         	  queryStr.append(" ,ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.locationScopeId=4 ");	 
      }
	    if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID ){
	     	  if(locationValue != null && locationValue.size() > 0){
	   	      queryStr.append("  and model1.district.districtId in (:locationValue) ");	
	     	  }
		 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
			 if(locationValue != null && locationValue.size() > 0){
				 queryStr.append(" and model3.parliamentId in (:locationValue)"); 
			 } 
		 }else if(locationValue != null && locationValue.size() > 0){
			 if(locationValue != null && locationValue.size() > 0){
	 	 	    queryStr.append(" and model.locationValue in (:locationValue)");  
			 }
	 	 }
	   	if(fromDate!= null && toDate!=null){
		      queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
	   	}

        if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		     if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
       		 queryStr.append(" group by model1.district.districtId ");; 
       	}else{
       		 queryStr.append(" group by model2.districtId ");
       	}   
	   }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
   	     || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
		 queryStr.append("  group by model1.constituencyId ");
	   }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		 queryStr.append("  group by  model3.assemblyId ");
	   }
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
   	     || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
       	    queryStr.append(" model1.constituencyId,");  
         }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
    	     queryStr.append(" model3.assemblyId,") ; 
         }
         queryStr.append(" sum(model.renewalCadre) from TdpCadreDateWiseInfo model ");
     
         if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
       	      if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
         		 queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model.locationScopeId=4 and model1.electionScope.electionScopeId=2 and model1.deformDate is null ");; 
         	  }else{
         		 queryStr.append(" ,District model2 where model2.districtId = model.locationValue and model.locationScopeId=3 ");
         	  }
         }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
   	      || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
       	     queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model1.electionScope.electionScopeId=2 and model.locationScopeId=4 and model1.deformDate is null ");
          }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
        	  queryStr.append(" ,ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.locationScopeId=4 ");	 
          }
        	 
         if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID ){
	     	  if(locationValue != null && locationValue.size() > 0){
	   	      queryStr.append("  and model1.district.districtId in (:locationValue) ");	
	     	  }
		 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
			 if(locationValue != null && locationValue.size() > 0){
				 queryStr.append(" and model3.parliamentId in (:locationValue)"); 
			 } 
		 }else if(locationValue != null && locationValue.size() > 0){
			 if(locationValue != null && locationValue.size() > 0){
	 	 	    queryStr.append(" and model.locationValue in (:locationValue)");  
			 }
	 	 }
        if(fromDate!= null && toDate!=null){
		    queryStr.append(" and date(model.surveyDate) between :fromDate and :toDate ");	 
		  }
	      
        if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	  	     if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
	      		 queryStr.append(" group by model1.district.districtId ");; 
	      	  }else{
	      		 queryStr.append(" group by model2.districtId ");
	      	  }   
		 }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	      || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
			 queryStr.append("  group by model1.constituencyId ");
		 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
			 queryStr.append("  group by  model3.assemblyId ");
		 }
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
				queryStr.append(" booth.boothId  as name  ,booth.partNo ,'POLLINGSTATION','','',''," +
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
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append("  and model.locationScopeId = 3 and model.locationValue = district.districtId and district.state.stateId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and district.districtId = :childLocationTypeId ");
					}
				}
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and  model.locationScopeId = 4 and model.locationValue = constituency.constituencyId  and constituency.district.districtId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and constituency.constituencyId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					
						queryStr.append(" and booth.constituency.constituencyId = :parentLocationTypeId ");
						if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN)){
							queryStr.append(" and model.locationScopeId = 9  and model.locationValue = booth.boothId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.boothId = :childLocationTypeId ");
							}
						}
						else if(inputVO.getAreaType().equalsIgnoreCase(IConstants.RURAL) ){
							queryStr.append(" and model.locationScopeId = 5 and model.locationValue = booth.tehsil.tehsilId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.tehsil.tehsilId = :childLocationTypeId ");
							}
						}
						else if((inputVO.getAreaType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ) ){
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
					queryStr.append(" and  model.locationScopeId = 6 and model.locationValue = booth.boothId and booth.panchayat.panchayatId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and booth.boothId = :childLocationTypeId ");
					}
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
				queryStr.append(" booth.boothId ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			if( inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if( inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());
			if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				query.setDate("startDate", format.parse(inputVO.getStartDate()));
				query.setDate("endDate", format.parse(inputVO.getEndDate()));
			}
			
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
}
