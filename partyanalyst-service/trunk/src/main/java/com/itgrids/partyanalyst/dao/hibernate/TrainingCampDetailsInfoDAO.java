package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampDetailsInfoDAO;
import com.itgrids.partyanalyst.model.TrainingCampDetailsInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TrainingCampDetailsInfoDAO extends GenericDaoHibernate<TrainingCampDetailsInfo, Long> implements ITrainingCampDetailsInfoDAO {
	public TrainingCampDetailsInfoDAO() {
		super(TrainingCampDetailsInfo.class);
	}
      public List<Object[]> getTrainingCampProgramEligibleAndAttendedDetails(Long locationScopeId,List<Long> locationValues,Date toDate,List<Long> enrollmentYearIds,List<Long> programIdList,List<Long> committeeLevelIds){
    	  StringBuilder queryStr = new StringBuilder();
    	  queryStr.append(" select model.trainingCampProgram.trainingCampProgramId," +//0
    	  				 "  model.trainingCampProgram.programName," +//1
    	  				 " sum(model.eligible)," +//2
    	  				 " sum(model.attended)," +//3
    	  				 " sum(model.yetToTrain) ," +//4
    	  				 " sum(model.invited) " +//5
    	  				 " from TrainingCampDetailsInfo model " +
    	  				 " where  model.trainingCampProgram.trainingCampProgramId is not null ");
    	  if(programIdList != null && programIdList.size()>0)
    		  queryStr.append(" and model.trainingCampProgram.trainingCampProgramId in (:programIdList) "); 
    	  if(locationScopeId != null && locationScopeId.longValue() > 0){
    		  queryStr.append(" and model.locationScopeId =:locationScopeId");
    	  }
    	  if(locationValues != null && locationValues.size() > 0){
    		  queryStr.append(" and model.locationValue in (:locationValues)");
    	  }
    	  if(committeeLevelIds != null && committeeLevelIds.size()>0){
    		  queryStr.append(" and model.tdpCommitteeLevelId in (:committeeLevelIds)");
    	  }
    	  queryStr.append(" group by model.trainingCampProgram.trainingCampProgramId ");
    	 Query query = getSession().createQuery(queryStr.toString());
    	  if(locationScopeId != null && locationScopeId.longValue() > 0){
    		  query.setParameter("locationScopeId", locationScopeId); 
    	  }
    	  if(locationValues != null && locationValues.size() > 0){
    		  query.setParameterList("locationValues", locationValues);  
    	  }
    	  if(committeeLevelIds != null && committeeLevelIds.size()>0){
    		  query.setParameterList("committeeLevelIds", committeeLevelIds);
    	  }
    	  if(programIdList != null && programIdList.size()>0){
    		 query.setParameterList("programIdList", programIdList);
    	  }
    	 return query.list();
    }
      public List<Object[]> getTrainingCampProgramEligibleAndAttendedMemberCommitteeLevelWise(Long locationScopeId,List<Long> locationValues,Date toDate,List<Long> enrollmentYearIds,List<Long> programIdList,List<Long> tdpCommitteeLevelIds){
    	  StringBuilder queryStr = new StringBuilder();
    	  queryStr.append(" select model.tdpCommitteeLevelId," +//0
    	  				  " sum(model.eligible)," +//1
    	  				  " sum(model.attended)," +//2
    	  				  " sum(model.yetToTrain)," +//3
    	  				  " sum(model.invited) " +//4
    	  				  " from TrainingCampDetailsInfo model  " +
    			  		  " where  model.trainingCampProgram.trainingCampProgramId is not null ");
    	  if(programIdList != null && programIdList.size()>0)
    		  queryStr.append(" and model.trainingCampProgram.trainingCampProgramId in (:programIdList) "); 
    	  if(locationScopeId != null && locationScopeId.longValue() > 0){
    		  queryStr.append(" and model.locationScopeId =:locationScopeId");
    	  }
    	  if(locationValues != null && locationValues.size() > 0){
    		  queryStr.append(" and model.locationValue in (:locationValues)");
    	  }
    	  if(tdpCommitteeLevelIds != null && tdpCommitteeLevelIds.size()>0){
    		  queryStr.append(" and model.tdpCommitteeLevelId in (:tdpCommitteeLevelIds)");
    	  }
    	  queryStr.append(" group by model.tdpCommitteeLevelId ");
    	 Query query = getSession().createQuery(queryStr.toString());
    	  if(locationScopeId != null && locationScopeId.longValue() > 0){
    		  query.setParameter("locationScopeId", locationScopeId); 
    	  }
    	  if(locationValues != null && locationValues.size() > 0){
    		  query.setParameterList("locationValues", locationValues);  
    	  }
    	  if(tdpCommitteeLevelIds != null && tdpCommitteeLevelIds.size()>0){
    		  query.setParameterList("tdpCommitteeLevelIds", tdpCommitteeLevelIds);
    	  }
    	  if(programIdList != null && programIdList.size()>0){
     		 query.setParameterList("programIdList", programIdList);
     	  }
    	 return query.list();
    }
     public List<Object[]> getTrainingCampProgramEligibleAndAttendedMemberLocationWise(Long locationScopeId,List<Long> locationValues,Date toDate,List<Long> enrollmentYearIds,List<Long> programIdList){
    	  StringBuilder queryStr = new StringBuilder();
    	  queryStr.append(" select model.locationValue," +//0
    	  				  " sum(model.eligible)," +//1
    	  				  " sum(model.attended)," +//2
    	  				  " sum(model.yetToTrain)  " +//3
    	  				  " from TrainingCampDetailsInfo model "+
    	  				  " where model.trainingCampProgramId is not null  ");
    	  if(programIdList != null && programIdList.size()>0)
    		  queryStr.append(" and model.trainingCampProgramId in (:programIdList) ");
    	  if(locationScopeId != null && locationScopeId.longValue() > 0){
    		  queryStr.append(" and model.locationScopeId =:locationScopeId");
    	  }
    	  if(locationValues != null && locationValues.size() > 0){
    		  queryStr.append(" and model.locationValue in (:locationValues)");
    	  }
    	  
    	  if(enrollmentYearIds != null && enrollmentYearIds.size() >0){
    		 // queryStr.append(" and model1.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)   " );
  		}
    	  queryStr.append(" group by model.locationValue ");
    	 Query query = getSession().createQuery(queryStr.toString());
    	  if(locationScopeId != null && locationScopeId.longValue() > 0){
    		  query.setParameter("locationScopeId", locationScopeId); 
    	  }
    	  if(locationValues != null && locationValues.size() > 0){
    		  query.setParameterList("locationValues", locationValues);  
    	  }
    	  if(enrollmentYearIds != null && enrollmentYearIds.size() >0){
  			//query.setParameterList("enrollmentYearIds", enrollmentYearIds);
  		}
    	  if(programIdList != null && programIdList.size()>0)
    		  query.setParameterList("programIdList", programIdList);
    	 return query.list();
    }
   public List<Object[]> getLocationWiseReportBasedOnUserType(Long locationScopeId,List<Long> locationValues,Long stateId,Long userTypeId,Long activityMemberId){
	   
	   StringBuilder queryStr = new StringBuilder();  
	    
       queryStr.append("select distinct ");
       queryStr.append(" model.trainingCampProgram.trainingCampProgramId,");
       queryStr.append(" model.trainingCampProgram.programName,");
       if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		 	 if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
		  		 queryStr.append(" model1.district.districtId,model1.district.districtName, ");; 
		  	  }else{
		  		 queryStr.append(" model2.districtId,model2.districtName, ");
		  	  }  
	   }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
		|| userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
	 	     queryStr.append(" model1.constituencyId,model1.name,");  
	   }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		     queryStr.append(" model3.assemblyId,model3.name,") ; 
	   }
       queryStr.append(" sum(model.eligible)," +
       				  "  sum(model.attended)," +
       				  "  sum(model.yetToTrain)  " +
       				  "  from TrainingCampDetailsInfo model ");
    
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
         queryStr.append(" and model.trainingCampProgramId=8 ");
        if(userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID){
        	if(stateId != null && stateId.longValue() == 1){
        		queryStr.append(" and model2.districtId > 10 and model2.state.stateId=1 ");
        	}else if(stateId != null && stateId.longValue() == 36){
        		queryStr.append(" and model2.districtId < 11 ");
        	}
        }else if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID ){
	     	  if(locationValues != null && locationValues.size() > 0){
	   	      queryStr.append("  and model1.district.districtId in (:locationValues) ");	
	     	  }
		 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
			 if(locationValues != null && locationValues.size() > 0){
				 queryStr.append(" and model3.parliamentId in (:locationValues)"); 
			 } 
		 }else if(locationValues != null && locationValues.size() > 0){
			    queryStr.append(" and model.locationValue in (:locationValues)");  
	 	 }
        queryStr.append(" group by model.trainingCampProgram.trainingCampProgramId ");
	    if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		     if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
       		 queryStr.append(",model1.district.districtId ");; 
       	}else{
       		 queryStr.append(",model2.districtId ");
       	}   
	   }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
   	     || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
		    queryStr.append(",model1.constituencyId ");
	   }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		    queryStr.append(",model3.assemblyId ");
	   }
	  Query query = getSession().createQuery(queryStr.toString());
	  if(userTypeId.longValue()!=IConstants.STATE_TYPE_USER_ID){
		  if(locationValues != null && locationValues.size() > 0){
			   query.setParameterList("locationValues", locationValues);  
		  }
	  }
	   return query.list();
   }
 public List<Object[]> getTrainingCampEligibleAndAttendedCntByLocationType(Long locationScopeId,List<Long> locationValues,Long stateId,Long userTypeId,Long activityMemberId,String locationType){
	   
	   StringBuilder queryStr = new StringBuilder();  
	    
       queryStr.append("select distinct ");
       if(locationType != null && locationType.equalsIgnoreCase("District")){
		 	 if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
		  		 queryStr.append(" model1.district.districtId,model1.district.districtName, ");; 
		  	  }else{
		  		 queryStr.append(" model2.districtId,model2.districtName, ");
		  	  }  
       }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
    	   if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
    		   queryStr.append(" model3.assembly.constituencyId,model3.assembly.name,") ;   
    	   }else{
    		   queryStr.append(" model1.constituencyId,model1.name,");   
    	   }
       }
        queryStr.append(" sum(model.eligible)," +
    	 	   		   " sum(model.attended)," +
				       " sum(model.yetToTrain)  " +
				       " from TrainingCampDetailsInfo model ");
         if(locationType != null && locationType.equalsIgnoreCase("District")){
			 	 if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
			  		 queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model.locationScopeId=4 and model1.electionScope.electionScopeId=2 and model1.deformDate is null ");; 
			  	  }else{
			  		 queryStr.append(" ,District model2 where model2.districtId = model.locationValue and model.locationScopeId=3 ");
			  	  }  
	      }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	  	   if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
	  		   queryStr.append(",ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.locationScopeId=4 ") ;   
	  	   }else{
	  		   queryStr.append(",Constituency model1 where model1.constituencyId = model.locationValue and model1.electionScope.electionScopeId=2 and model.locationScopeId=4 and model1.deformDate is null ");   
	  	   }
	      }
	       queryStr.append(" and model.trainingCampProgramId=8 ");
        if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID ){
	     	  if(locationValues != null && locationValues.size() > 0){
	   	      queryStr.append("  and model1.district.districtId in (:locationValues) ");	
	     	  }
		 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
			 if(locationValues != null && locationValues.size() > 0){
				 queryStr.append(" and model3.parliamentId in (:locationValues)"); 
			 } 
		 }else if(locationValues != null && locationValues.size() > 0){
				 if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
					 if(activityMemberId != null && activityMemberId.longValue() == 1l || activityMemberId.longValue() == 2l || activityMemberId.longValue()==3l){
						 queryStr.append(" and model1.district.districtId in (:locationValues)");
					 }else{
						 queryStr.append(" and model.locationValue in (:locationValues)");	 
					 }
				 }else{
					  queryStr.append(" and model.locationValue in (:locationValues)");	 
				 }
	 	 }
       
        if(locationType != null && locationType.equalsIgnoreCase("District")){
		 	 if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
		  		 queryStr.append(" group by model1.district.districtId ");; 
		  	  }else{
		  		 queryStr.append(" group by model2.districtId  ");
		  	  }  
     }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
  	   if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
  		   queryStr.append(" group by model3.assemblyId ") ;   
  	   }else{
  		   queryStr.append(" group by model1.constituencyId ");   
  	   }
     }
      Query query = getSession().createQuery(queryStr.toString());
	  if(locationValues != null && locationValues.size() > 0){
		   query.setParameterList("locationValues", locationValues);  
	  }
	  return query.list();
   }
 	public int pushTrainginCampDataLocationWiseByCommitteeLevel(){
		Query query = getSession().createSQLQuery("CALL training_camp_details_info(); ");
		return query.executeUpdate();  
	}
	@Override
	public List<Object[]> getBoothlevelEligibleCandidates(List<Long> enrollmentYearIds, Long accessLevelValue, List<Long> userAccessLevelValues,List<Long> programIdList){
		String  committeeEnrollmetYrId = "";
		   if(enrollmentYearIds.contains(4L) && enrollmentYearIds.contains(3L) ){
			   committeeEnrollmetYrId="1,2";
		   }else if(enrollmentYearIds.contains(3L)){
			   committeeEnrollmetYrId="1";
		   }else if(enrollmentYearIds.contains(4L)){
			   committeeEnrollmetYrId="2";
		   }else{
			   committeeEnrollmetYrId=enrollmentYearIds.get(0).toString();
		   }
		   
		   String userAccessLevelValue="";
		   if(userAccessLevelValues != null && userAccessLevelValues.size()>0){
			   for (Long batchId : userAccessLevelValues) {
				   if(userAccessLevelValue.trim().length()==0)
					   userAccessLevelValue = ""+batchId;
				   else
					   userAccessLevelValue = userAccessLevelValue+","+batchId;
			   }
		   }
		   
		    if(userAccessLevelValue.isEmpty())
		    	userAccessLevelValue = null;
		    
		  Query query = getSession().createSQLQuery("call get_booth_committee_eligible_members(:enrollmentYearIds,:accesslevelvalue,:locationValues,:programId)")
				  .addScalar("m_location_scope_id",Hibernate.LONG)
				  .addScalar("state_id",Hibernate.LONG)
				  .addScalar("scope_value",Hibernate.LONG)
				  .addScalar("eligible",Hibernate.LONG)
				  .addScalar("attended",Hibernate.LONG)
				   .addScalar("yet_to_earn",Hibernate.LONG);
		  query.setParameter("programId", programIdList.get(0))
		  .setParameter("enrollmentYearIds", committeeEnrollmetYrId)
		  .setParameter("accesslevelvalue", accessLevelValue)
		  .setParameter("locationValues", userAccessLevelValue); 
		
		return query.list();
	}
}
