package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreTargetCount;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreTargetCountDAO extends GenericDaoHibernate<TdpCadreTargetCount, Long> implements ITdpCadreTargetCountDAO {

	public TdpCadreTargetCountDAO() {
		super(TdpCadreTargetCount.class);
	}
	public List<Object[]> getTotalCadreTargetCountLocationWise(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long enrollmentYearId,Long activityMemberId){
		
		StringBuilder queryStr = new StringBuilder();  
	        queryStr.append(" select");
		  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	         queryStr.append(" model.constituency.state.stateId,");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	             queryStr.append(" model.constituency.district.districtId,");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	          queryStr.append(" model.parliamentConstituency.constituencyId, ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	          queryStr.append(" model.constituency.constituencyId, ");   
		  }
		   queryStr.append(" sum(model.targetCount) " +
				" from " +
				" TdpCadreTargetCount model " +
				" where " +
				" model.enrollmentYear.enrollmentYearId=:enrollmentYearId ");
           if(stateId != null && stateId.longValue() > 0){
				 if(stateId != null && stateId.longValue() > 0){
					   if(stateId.longValue()==1l){
							queryStr.append(" and  model.constituency.district.districtId > 10 and  model.constituency.state.stateId = 1 ");
						}else if(stateId.longValue()==36l){
							queryStr.append(" and  model.constituency.district.districtId < 11 ");
						}
				 } 
		   }
           if(activityMemberId != null && activityMemberId.longValue()==4l){
        	  queryStr.append(" and model.constituencyId not in(244,246,248,250,251,252) ");//activity memberId 4 has access only 4 constituency access of kadapa district so we are ignoring 6 constituency of kadapa district
           }else if(activityMemberId != null && activityMemberId.longValue()==5l){
        	  queryStr.append(" and model.constituencyId not in(242,243,245,249) ");//activity memberId 5 has access only 6 constituency access of kadapa district so we are ignoring 4 constituency of kadapa district
           }
		  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	         queryStr.append(" group by model.constituency.state.stateId ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	             queryStr.append(" group by model.constituency.district.districtId ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	             queryStr.append(" group by model.parliamentConstituency.constituencyId ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	          queryStr.append(" group by model.constituency.constituencyId ");  
		  }
		   Query query = getSession().createQuery(queryStr.toString());
		    query.setParameter("enrollmentYearId", enrollmentYearId);
		 
		   return query.list();
	}
	
	public Long getTargetCount(Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(TCTC.targetCount) " +
						" from TdpCadreTargetCount TCTC " +
						" where TCTC.enrollmentYearId = :enrollmentYearId and ");
		if(stateId == 1l){
			queryStr.append(" TCTC.constituency.district.districtId between 11 and 23 ");
		}else{
			queryStr.append(" TCTC.constituency.district.districtId between 1 and 10 ");
		}			
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("enrollmentYearId", IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
		return (Long) query.uniqueResult();
	}
	
    public List<Object[]> getTotalCadreTargetCountLocationType(String locationType,Long stateId,Long entollmentTearId){
	         
          StringBuilder queryStr = new StringBuilder();
             
         queryStr.append(" select ");
         if(locationType != null && locationType.equalsIgnoreCase("District")){
        	 queryStr.append(" model.constituency.district.districtId,");
		  }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append(" model.constituency.district.districtId,");  
		  }
              queryStr.append(" sum(model.targetCount) " +
			  " from " +
			  " TdpCadreTargetCount model where model.enrollmentYear.enrollmentYearId=:entollmentTearId ");
		   
        if(stateId != null && stateId.longValue() > 0){
			   if(stateId.longValue()==1l){
					queryStr.append(" and  model.constituency.district.districtId > 10 and  model.constituency.state.stateId = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and  model.constituency.district.districtId < 11 ");
				}
		 } 
  
        if(locationType != null && locationType.equalsIgnoreCase("District")){
	            queryStr.append(" group by model.constituency.state.stateId");  
        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	            queryStr.append(" group by model.constituency.district.districtId");  
		  }
    
       Query query = getSession().createQuery(queryStr.toString());
      query.setParameter("entollmentTearId", entollmentTearId);
	   return query.list();
}
    
    public List<Object[]> getTdpCadreTargetCountLocationWise(Long enrollmentYearId){
    	
    	Query query = getSession().createQuery("" +
    	" select model.locationScopeId,model.locationValue,model.targetCount " +
    	" from   TdpCadreTargetCount model  " +
    	" where  model.isDeleted = 'N' and model.enrollmentYearId = :enrollmentYearId ");
    	query.setParameter("enrollmentYearId", enrollmentYearId);
    	return query.list();
    }
    
public List<Object[]> getTargetCountForLocationsWise(GISVisualizationParameterVO inputVO){
		
		try {
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select distinct ");
				
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  district.districtId,district.districtName as name ,model.targetCount ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.constituencyId,constituency.name as name ,model.targetCount ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN) || inputVO.getChildLocationType().equalsIgnoreCase("pollingstation"))
					queryStr.append(" booth.boothId  as name  ,booth.partNo ,model.targetCount ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" booth.tehsil.tehsilId , booth.tehsil.tehsilName  as name  ,model.targetCount ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) || inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURALURBAN))
					queryStr.append(" booth.localBody.localElectionBodyId,booth.localBody.name,model.targetCount");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
				queryStr.append(" booth.panchayat.panchayatId , booth.panchayat.panchayatName  as name  ,model.targetCount ");
			}/*else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" booth.localBody.localElectionBodyId,booth.localBody.name,model.targetCount ");
			}*/else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" booth.boothId  as name  ,booth.partNo ,'POLLINGSTATION',model.targetCount ");
			}
			
			queryStr.append(" from ");
			queryStr.append(" TdpCadreTargetCount model ");
			
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
			
			queryStr.append(" where model.enrollmentYearId = 4 and ");
			
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append("  model.locationScopeId = 3 and model.locationValue = district.districtId and district.state.stateId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and district.districtId = :childLocationTypeId ");
					}
				}
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append("  model.locationScopeId = 4 and model.locationValue = constituency.constituencyId  ");
					if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
						queryStr.append(" and (constituency.district.districtId between 11 and 23) ");
					else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
						queryStr.append(" and (constituency.district.districtId between 1 and 10) ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and constituency.constituencyId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					
						queryStr.append(" booth.constituency.constituencyId = :parentLocationTypeId   ");
						if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN)  || inputVO.getChildLocationType().equalsIgnoreCase("pollingstation")){
							queryStr.append(" and model.locationScopeId = 9 and model.locationValue = booth.boothId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.boothId = :childLocationTypeId ");
							}
						}
						else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL) ){
							queryStr.append(" and model.locationScopeId = 5 and model.locationValue = booth.tehsil.tehsilId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.tehsil.tehsilId = :childLocationTypeId ");
							}
						}
						else if((inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ||inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURALURBAN) ) ){
							queryStr.append(" and model.locationScopeId = 7 and model.locationValue = booth.localBody.localElectionBodyId  and booth.localBody is not null ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.localBody.localElectionBodyId = :childLocationTypeId ");
							}
						}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append("    model.locationScopeId = 6 and model.locationValue = booth.panchayat.panchayatId and  booth.tehsil.tehsilId = :parentLocationTypeId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and  booth.tehsil.tehsilId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append(" and model.locationScopeId = 6 and model.locationValue = booth.boothId and booth.panchayat.panchayatId = :parentLocationTypeId ");
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
			
			queryStr.append(" and model.isDeleted = 'N' ");
			
			Query query = getSession().createQuery(queryStr.toString());
			if( inputVO.getParentLocationTypeId().longValue()>0L && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT))
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if( inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());
			
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
public List<Object[]> getTtalCadreTargetCountScopeWise(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long enrollmentYearId,Long activityMemberId,String reportType){
	
	StringBuilder queryStr = new StringBuilder();  
        queryStr.append(" select");
		if(reportType != null && reportType.equalsIgnoreCase("District")){
			queryStr.append(" distinct model1.district.districtId,");
		}else{
		    queryStr.append(" model.locationValue,");	
		}
	    queryStr.append(" sum(model.targetCount) from TdpCadreTargetCount model  ");
		
		if(reportType != null && reportType.equalsIgnoreCase("District")){
			queryStr.append(" ,Constituency model1 where model1.constituencyId=model.locationValue and model1.electionScope.electionScopeId=2 and model1.deformDate is null and model.isDeleted='N' and model.enrollmentYear.enrollmentYearId=:enrollmentYearId  ");
		}else{
			queryStr.append(" where model.isDeleted='N' and model.enrollmentYear.enrollmentYearId=:enrollmentYearId ");	
		}
		if(reportType != null && reportType.equalsIgnoreCase("District")){
	       if(activityMemberId != null && activityMemberId.longValue()==4l){
	    	  queryStr.append(" and model1.constituencyId not in(244,246,248,250,251,252) ");//activity memberId 4 has access only 4 constituency access of kadapa district so we are ignoring 6 constituency of kadapa district
	       }else if(activityMemberId != null && activityMemberId.longValue()==5l){
	    	  queryStr.append(" and model1.constituencyId not in(242,243,245,249) ");//activity memberId 5 has access only 6 constituency access of kadapa district so we are ignoring 4 constituency of kadapa district
	       }
		}
       if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
         	queryStr.append(" and model.locationValue in (:locationValue)");  
       }
       if(userAccessLevelId != null && userAccessLevelId.longValue() > 0){
    	   queryStr.append(" and model.locationScopeId=:locationScopeId ");
       }
	   	if(reportType != null && reportType.equalsIgnoreCase("District")){
	   	 queryStr.append(" group by model1.district.districtId order by model1.district.districtId asc ");	
	   	}else{
	   	 queryStr.append(" group by model.locationValue order by model.locationValue asc ");	
	   	}
        Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("enrollmentYearId", enrollmentYearId);
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	    	query.setParameterList("locationValue", userAccessLevelValues);
	    }
	    if(userAccessLevelId != null && userAccessLevelId.longValue() > 0){
	      query.setParameter("locationScopeId", userAccessLevelId);
	    }
	   return query.list();
}
public List<Object[]> getTtalCadreTargetCountScopeWiseCount(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long enrollmentYearId){
	
	StringBuilder queryStr = new StringBuilder();  
        queryStr.append(" select");
		
        queryStr.append(" model.locationValue,sum(model.targetCount) " +
        		" from " +
        		" TdpCadreTargetCount model " +  
        		" where " +
        		" model.isDeleted='N' and model.enrollmentYear.enrollmentYearId=:enrollmentYearId ");
	   
       if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){    
         	queryStr.append(" and model.locationValue in (:locationValue)");  
       }
       if(userAccessLevelId != null && userAccessLevelId.longValue() > 0){
    	   queryStr.append(" and model.locationScopeId=:locationScopeId ");
       }
       queryStr.append(" group by model.locationValue order by model.locationValue asc ");  
	   Query query = getSession().createQuery(queryStr.toString());  
	    query.setParameter("enrollmentYearId", enrollmentYearId);
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	    	query.setParameterList("locationValue", userAccessLevelValues);
	    }
	    if(userAccessLevelId != null && userAccessLevelId.longValue() > 0){
	      query.setParameter("locationScopeId", userAccessLevelId);
	    }
	   return query.list();  
}
public List<Object[]> getTtalCadreTargetCountScopeWiseCountSpecial(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long enrollmentYearId){
	
	StringBuilder queryStr = new StringBuilder();  
        queryStr.append(" select");
        if(userAccessLevelId.equals(4l)){ 
     	   queryStr.append(" C.district.districtId ");    
        }else if(userAccessLevelId.equals(10l)){
     	   queryStr.append(" C.district.districtId ");
        }else{
     	   queryStr.append(" model.locationValue ");
        }
       
        queryStr.append(" ,sum(model.targetCount) " +  
        				" from " +
        				" TdpCadreTargetCount model ");
        if(userAccessLevelId.equals(4l)){
        	queryStr.append(", Constituency C ");
        }
        if(userAccessLevelId.equals(10l)){
        	queryStr.append(" ,ParliamentAssembly PA, Constituency C ");
        }
        queryStr.append(" where " +
        				" model.isDeleted='N' and model.enrollmentYear.enrollmentYearId=:enrollmentYearId ");
        if(userAccessLevelId.equals(4l)){
        	queryStr.append(" and model.locationValue = C.constituencyId ");
        }else if(userAccessLevelId.equals(10l)){
        	queryStr.append(" and model.locationValue = PA.parliamentId " +
        					" and PA.assemblyId = C.constituencyId ");
        }
        
       if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
         	queryStr.append(" and model.locationValue in (:locationValue)");  
       }
       if(userAccessLevelId != null && userAccessLevelId.longValue() > 0){
    	   queryStr.append(" and model.locationScopeId=:locationScopeId ");
       }
       if(userAccessLevelId.equals(4l)){
    	   queryStr.append(" group by C.district.districtId order by C.district.districtId asc ");
       }else if(userAccessLevelId.equals(10l)){
    	   queryStr.append(" group by C.district.districtId order by C.district.districtId asc ");
       }else{
    	   queryStr.append(" group by model.locationValue order by model.locationValue asc ");
       }
        
	  
	    Query query = getSession().createQuery(queryStr.toString());  
	    query.setParameter("enrollmentYearId", enrollmentYearId);
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	    	query.setParameterList("locationValue", userAccessLevelValues);
	    }
	    if(userAccessLevelId != null && userAccessLevelId.longValue() > 0){
	      query.setParameter("locationScopeId", userAccessLevelId);
	    }  
	   return query.list();  
}

public List<Object[]> getConstitiuencyWiseTargetBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue,Long enrollmentYearId){
	
	 StringBuilder queryStr = new StringBuilder();  
	    
     queryStr.append(" select distinct ");
     
     if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
     	queryStr.append(" model2.constituencyId,");
     }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
     	queryStr.append(" model3.assemblyId,");
     }else{
    	queryStr.append(" model.locationValue,"); 
     }
     queryStr.append(" sum(model.targetCount) from TdpCadreTargetCount model ");
     
    if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
    	queryStr.append(",Constituency model2 where model2.constituencyId = model.locationValue and model2.electionScope.electionScopeId=2 and model2.deformDate is null and model.enrollmentYearId=:enrollmentYearId ");
    }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
    	queryStr.append(",ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.enrollmentYearId=:enrollmentYearId ");
    }else {
      queryStr.append(" where model.enrollmentYearId=:enrollmentYearId ");
    }
    queryStr.append(" and model.locationScopeId=4 ");
    if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID && locationValue != null && locationValue.size() > 0){
    	queryStr.append(" and model2.district.districtId in (:locationValue) ");
    }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID && locationValue != null && locationValue.size() > 0){
    	queryStr.append(" and model3.parliamentId in (:locationValue) ");
    }else {
    	if(locationValue != null && locationValue.size() > 0){
	 	 	queryStr.append(" and model.locationValue in (:locationValue)");  
	    }
    }
    if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
    	queryStr.append(" group by model2.constituencyId ");
    }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
    	queryStr.append(" group by model3.assemblyId ");
    }else {
    	queryStr.append(" group by model.locationValue ");
    }
	  Query query = getSession().createQuery(queryStr.toString());
	   query.setParameter("enrollmentYearId", enrollmentYearId);
	  if(locationValue != null && locationValue.size() > 0){
		query.setParameterList("locationValue", locationValue);  
	  }
	  return query.list();
}

  public List<Object[]> getDistrictsTargetCountForTodayAndOverAll(Long stateId){
	StringBuilder sb = new StringBuilder();
	sb.append("select model.locationValue," +
			" model.targetCount" +
			" from TdpCadreTargetCount model" +
			" where model.locationScopeId = 3 and model.isDeleted = 'N' ");
	if(stateId != null && stateId.longValue() == 1l)
		sb.append(" and model.locationValue between 11 and 23");
	else if(stateId != null && stateId.longValue() == 36l)
		sb.append(" and model.locationValue between 1 and 10");
	Query query = getSession().createQuery(sb.toString());
	return query.list();
}

   public List<Object[]> getConstitencysTargetCountForTodayAndOverAll(Long stateId){
	StringBuilder sb = new StringBuilder();
	sb.append("select model.locationValue," +
			" model.targetCount" +
			" from TdpCadreTargetCount model,Constituency C" +
			" where model.locationScopeId = 4 and model.isDeleted = 'N' " +
			"and model.locationValue = C.constituencyId");
	if(stateId != null && stateId.longValue() == 1l)
		sb.append(" and C.district.districtId between 11 and 23");
	else if(stateId != null && stateId.longValue() == 36l)
		sb.append(" and C.district.districtId between 1 and 10");
	Query query = getSession().createQuery(sb.toString());
	return query.list();
}
   public List<Object[]> getTtalCadreTargetCntDistWise(Long enrollmentYearId,List<Long> constituencyIds,String districtName){
		
		       StringBuilder queryStr = new StringBuilder();  
		        queryStr.append("select ");
		        
		       if(districtName != null && districtName.equalsIgnoreCase("Adilabad")){
		    	   queryStr.append(" model1.district.districtId,");  
		       }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial")){
		    	   queryStr.append(" model2.districtId,"); 
		       }
		        queryStr.append(" sum(model.targetCount) from TdpCadreTargetCount model ");
		        if(districtName != null && districtName.equalsIgnoreCase("Adilabad")){
		         queryStr.append(" ,Constituency model1 where model1.constituencyId=model.locationValue and model1.electionScope.electionScopeId=2 " +
							    "  and model1.deformDate is null ");
		        }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial")){
		        	 queryStr.append(" ,DistrictConstituencies model2 where model2.constituencyId=model.locationValue ");
		        }
				queryStr.append(" and model.enrollmentYear.enrollmentYearId=:enrollmentYearId and model.isDeleted='N' ");
		       
			  if(constituencyIds != null && constituencyIds.size() > 0){
		   	     queryStr.append(" and model.locationValue in(:constituencyIds) ");
		       }
		       queryStr.append(" and model.locationScopeId=:locationScopeId ");
		       if(districtName != null && districtName.equalsIgnoreCase("Adilabad")){
		    	   queryStr.append(" group by model1.district.districtId ");  
		       }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial")){
		    	   queryStr.append(" group by model2.districtId "); 
		       }
		       Query query = getSession().createQuery(queryStr.toString());
		       query.setParameter("enrollmentYearId", enrollmentYearId);
			   if(constituencyIds != null && constituencyIds.size() > 0){
			    query.setParameterList("constituencyIds", constituencyIds);
			   }
			   query.setParameter("locationScopeId", 4l);
		   return query.list();
	}
}
