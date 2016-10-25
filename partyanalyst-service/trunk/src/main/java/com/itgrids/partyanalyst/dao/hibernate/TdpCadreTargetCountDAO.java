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
	public List<Object[]> getTotalCadreTargetCountLocationWise(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long enrollmentYearId){
		
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
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
					queryStr.append(" booth.boothId  as name  ,booth.partNo ,model.targetCount ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" tehsil.tehsilId , tehsil.tehsilName  as name  ,model.targetCount ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL))
					queryStr.append(" localElectionBody.localElectionBodyId,localElectionBody.name,model.targetCount");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
				queryStr.append(" tehsil.tehsilId , tehsil.tehsilName  as name  ,model.targetCount ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" localElectionBody.localElectionBodyId,localElectionBody.name,model.targetCount ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append(" booth.boothId  as name  ,booth.partNo ,'POLLINGSTATION',model.targetCount ");
			}
			
			queryStr.append(" from ");
			queryStr.append(" TdpCadreTargetCount model ");
			
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  ,District district ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){			
				queryStr.append(" ,District district  ,Constituency constituency ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){			
				queryStr.append(" ,Constituency constituency,LocalElectionBody localElectionBody ,Tehsil tehsil,Booth booth ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
				queryStr.append(" ,Constituency constituency,Tehsil tehsil ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" ,Constituency constituency,LocalElectionBody localElectionBody ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append(" ,Panchayat panchayat,Booth booth  ");
			}
			
			queryStr.append(" where  ");
			
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append("  model.locationScopeId = 3 and model.locationValue = district.districtId and district.state.stateId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and district.districtId = :childLocationTypeId ");
					}
				}
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append("  model.locationScopeId = 4 and model.locationValue = constituency.constituencyId  and district.districtId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and constituency.constituencyId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					
						queryStr.append("  constituency.constituencyId = :parentLocationTypeId ");
						if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN)){
							queryStr.append(" and model.locationScopeId = 9 and model.locationValue = booth.boothId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.boothId = :childLocationTypeId ");
							}
						}
						else if(inputVO.getAreaType().equalsIgnoreCase(IConstants.RURAL) ){
							queryStr.append(" and model.locationScopeId = 5 and model.locationValue = tehsil.tehsilId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and tehsil.tehsilId = :childLocationTypeId ");
							}
						}
						else if((inputVO.getAreaType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ) ){
							queryStr.append(" and model.locationScopeId = 7 and model.locationValue = localElectionBody.localElectionBodyId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append("  and localElectionBody.localElectionBodyId = :childLocationTypeId ");
							}
						}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append("  and  model.locationScopeId = 5 and model.locationValue = tehsil.tehsilId and  constituency.constituencyId = :parentLocationTypeId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and tehsil.tehsilId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append(" and  model.locationScopeId = 7 and model.locationValue = localElectionBody.localElectionBodyId and constituency.constituencyId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and localElectionBody.localElectionBodyId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
					queryStr.append(" and  model.locationScopeId = 6 and model.locationValue = booth.boothId and panchayat.panchayatId = :parentLocationTypeId ");
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
			
			queryStr.append(" and model.isDeleted = 'N' group by ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" district.districtId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.constituencyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
					queryStr.append(" booth.boothId ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" tehsil.tehsilId ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL))
					queryStr.append(" localElectionBody.localElectionBodyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
				queryStr.append(" tehsil.tehsilId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" localElectionBody.localElectionBodyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append(" booth.boothId ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			if( inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if( inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());
			
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
   
}
