package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
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
    
    
}
