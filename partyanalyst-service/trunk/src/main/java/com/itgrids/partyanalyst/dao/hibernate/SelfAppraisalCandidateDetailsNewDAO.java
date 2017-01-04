package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsNewDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsNew;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateDetailsNewDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetailsNew, Long> implements ISelfAppraisalCandidateDetailsNewDAO {

	public SelfAppraisalCandidateDetailsNewDAO() {
		super(SelfAppraisalCandidateDetailsNew.class);
	}
	
	 public List<Object[]> getCategoryWiseTourSubmittedLeader(Date fromDate,Date toDate,String type,List<Long> monthYearIds){
		 
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		     " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," );
		   if(type.equalsIgnoreCase("tourType")){
			 queryStr.append("model.tourType.tourTypeId,");
		   }else{
			  queryStr.append("model.selfAppraisalTourCategory.selfAppraisalTourCategoryId,"); 
		   }
		   queryStr.append(" count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId) " +
			 " from SelfAppraisalCandidateDetailsNew model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
                queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
           }
		/*   if(type.equalsIgnoreCase("Govt")){
			   queryStr.append(" and model.tourTypeId = 2 ");
		   }*/
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId");
	      if(type.equalsIgnoreCase("tourType")){
			 queryStr.append(",model.tourTypeId");
		  }else{
			  queryStr.append(",model.selfAppraisalTourCategoryId"); 
		  }
		   queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId");
		  Query query = getSession().createQuery(queryStr.toString());
		  	if(monthYearIds != null && monthYearIds.size() > 0 ){
			  query.setParameterList("monthYearIds", monthYearIds);
		    }
		  return query.list();
 }
	  public List<Object[]> getToursSubmittedLeaderCntDesignationBy(List<Long> monthYearIds,Set<Long> candiateIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId) " +
			 " from SelfAppraisalCandidateDetailsNew model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
                queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
           }
		   if(candiateIds != null && candiateIds.size() > 0){
			  queryStr.append(" and model.selfAppraisalCandidateId in (:candiateIds)"); 
		   }
		  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(candiateIds != null && candiateIds.size() > 0){
			  query.setParameterList("candiateIds", candiateIds);
		   }
		  if(monthYearIds != null && monthYearIds.size() > 0 ){
				  query.setParameterList("monthYearIds", monthYearIds);
		  }
		  return query.list();
   }
	  public List<Object[]> getLeaderComplainceCntCategoryWise(List<Long> monthYearIds,String type,Long selfAppraisalCandiateId,Set<Long> candiateIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model.selfAppraisalCandidateId," );
			if(type.equalsIgnoreCase("tourCategory")){
			 queryStr.append(" model.selfAppraisalTourCategoryId,");	
			}else if(type.equalsIgnoreCase("tourType")){
			 queryStr.append(" model.tourTypeId,");	
			}
			queryStr.append(" sum(model.tourDays) " +
			 " from SelfAppraisalCandidateDetailsNew model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
                queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
           }
		   if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
			 queryStr.append(" and model.selfAppraisalCandidateId=:selfAppraisalCandidateId");  
		   }
		   if(candiateIds != null && candiateIds.size() > 0){
			   queryStr.append(" and model.selfAppraisalCandidateId in (:candiateIds)");    
		   }
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				   "  model.selfAppraisalCandidateId,");
		    if(type.equalsIgnoreCase("tourCategory")){
			 queryStr.append(" model.selfAppraisalTourCategoryId");	
			}else if(type.equalsIgnoreCase("tourType")){
			 queryStr.append(" model.tourTypeId");	
			}
		    queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				   "  model.selfAppraisalCandidateId ");
		  Query query = getSession().createQuery(queryStr.toString());
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
			query.setParameterList("monthYearIds", monthYearIds);
		   }
		  if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
			query.setParameter("selfAppraisalCandidateId", selfAppraisalCandiateId);  
		  }
		  if(candiateIds != null && candiateIds.size() > 0){
			  query.setParameterList("candiateIds", candiateIds);    
		   }
		  return query.list();
  }
 public List<Object[]> getTourSubmitteedDesignationWiseAllCandiateBasedOnUserAccessLevel(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId,List<Long> monthYearIds,List<Long> designationIds){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.designation," +//1
		   				" SACL.selfAppraisalCandidate.selfAppraisalCandidateId," +//2
		   				" SACL.selfAppraisalCandidate.tdpCadre.firstname " +//3 
		   		   		" from " +
				   		" SelfAppraisalCandidateLocationNew SACL,SelfAppraisalCandidateDetailsNew SACD " +
				   		" where SACL.selfAppraisalCandidate.selfAppraisalCandidateId = SACD.selfAppraisalCandidate.selfAppraisalCandidateId and  " +
				   		" SACL.selfAppraisalCandidate.isActive = 'Y' and " +  
				   		" SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' and " +
				   		" SACL.userAddress.state.stateId = :stateId ");
		   if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.state.stateId in (:userAccessLevelValues)");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.district.districtId in (:userAccessLevelValues)");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			   queryStr.append(" and SACL.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		   }
		   if(userTypeId != IConstants.STATE_USER_TYPE_ID || designationIds != null && designationIds.size() > 0){
				  queryStr.append(" and SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) "); 
		   }
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
               queryStr.append(" and SACD.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
          }
		   queryStr.append(" group by SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				   " SACL.selfAppraisalCandidate.selfAppraisalCandidateId");   
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		   if(locationValueSet != null && locationValueSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
		   }
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
				query.setParameterList("monthYearIds", monthYearIds);
		   }
		   if(stateId != null && stateId.longValue() > 0){
			   query.setParameter("stateId", stateId);
		   }
		   if(designationIds != null && designationIds.size() > 0){
			   query.setParameterList("designationIds",designationIds);   
		   }else{
			 /*  if(userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID){
			    	query.setParameterList("designationIds",Arrays.asList(IConstants.STATE_SUB_LEVEL_DESIG_IDS));
		       }else*/ if(userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.GENERAL_SECRETARY_SUB_LEVEL_DESIG_IDS));
		       }else if(userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.ORGANIZING_SECRETARY_SUB_LEVEL_DESIG_IDS));
		       }else if(userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.SECRETARY_SUB_LEVEL_DESIG_IDS));
		      }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.MP_SUB_LEVEL_DESIG_IDS));
		      }else if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.DISTRICT_PRESIDENT_SUB_LEVEL_DESIG_IDS));
		      }   
		   }
		
		   return query.list();  
	   }
 public List<Object[]> getMonthWiseTourSubmittedDetails(List<Long> monthYearIds,Long candidateId){
	    StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select " +
	  					" model.selfAppraisalToursMonth.toursMonth," + //0
	  					" selfAppraisalTourCategory.selfAppraisalTourCategoryId," +//1
	  					" selfAppraisalTourCategory.tourCategory," +//2
	  					" tourType.tourTypeId," +//3
	  					" tourType.tourType," +//4
	  					" model.remarks," +//5
	  					" selfAppraisalDesignation.selfAppraisalDesignationId," +//6
	  					" selfAppraisalDesignation.designation" +//7
	  					" from SelfAppraisalCandidateDetailsNew model " +
	  					" left join model.selfAppraisalTourCategory selfAppraisalTourCategory " +
	  					" left join model.tourType tourType" +
	  					" left join model.selfAppraisalDesignation selfAppraisalDesignation " +
	  					" where  " +
	  					" model.selfAppraisalCandidateId=:selfAppraisalCandidateId ");
				    if(monthYearIds != null && monthYearIds.size() > 0 ){
			            queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
			       }
                   Query query = getSession().createQuery(queryStr.toString());
                   query.setParameter("selfAppraisalCandidateId", candidateId);
                   if(monthYearIds != null && monthYearIds.size() > 0 ){
       				query.setParameterList("monthYearIds", monthYearIds);
       		           }
               return query.list();
}
}
