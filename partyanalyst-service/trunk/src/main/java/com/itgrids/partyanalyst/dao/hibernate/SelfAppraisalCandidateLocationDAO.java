package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateLocationDAO extends GenericDaoHibernate<SelfAppraisalCandidateLocation, Long> implements
		ISelfAppraisalCandidateLocationDAO {
	   public SelfAppraisalCandidateLocationDAO() {
			super(SelfAppraisalCandidateLocation.class);
		 }
	   
	   public List<Object[]> getCandiateLocationScopeIdAndValues(Long candidateId){
		   StringBuilder queryStr = new StringBuilder();
		    queryStr.append(" select " +
		    				" model.selfAppraisalLocationScopeId," +
		    				" model.locationValue," +
		    				" model.type " +
		    				" from SelfAppraisalCandidateLocation model" +
		    				" where " +
		    				" model.selfAppraisalCandidateId=:candidateId ");
		    Query query = getSession().createQuery(queryStr.toString());
		      query.setParameter("candidateId", candidateId);
		       return query.list();
	   }
	   public List<Object[]> getNoOfLeadersCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId){
		     StringBuilder queryStr = new StringBuilder();
		     queryStr.append(" select " +
		     		 		" model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		     		        " model.selfAppraisalCandidate.selfAppraisalDesignation.designation," +
		     		        " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)" +
		     		        " from SelfAppraisalCandidateLocation model " +
		     		        " where " +
		     		        " model.selfAppraisalCandidate.isActive='Y' " +
		     		        " and model.selfAppraisalCandidate.selfAppraisalDesignation.isActive='Y' ");
			     if(stateId != null && stateId.longValue() > 0){
							queryStr.append(" and model.userAddress.state.stateId =:stateId ");
				 }
			     
		     	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
				   queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				   queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			        queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			        queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
				    queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
				    queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
				    queryStr.append(" and model.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
				    queryStr.append(" and model.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
				 }
		     	
		     	  queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId ");
		     	  Query query = getSession().createQuery(queryStr.toString());
		     		
		 		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		 			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 		 }
		 		if(stateId != null && stateId.longValue() > 0){
		 			 query.setParameter("stateId", stateId);
		 		}
		 		 return query.list();
	   }
	   public List<Object[]> getDesigWiseAllCandidate(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,List<Long> desigList){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" SAC.selfAppraisalDesignation.selfAppraisalDesignationId, " +
		   				" SAC.selfAppraisalDesignation.designation, " +
		   				" SAC.selfAppraisalCandidateId, " +
		   				" SACL.selfAppraisalLocationScopeId, " +
		   				" SAC.tdpCadre.firstname, " + 
		   				" SAC.activityMemberId " +
				   		" from " +
				   		" SelfAppraisalCandidateLocation SACL, SelfAppraisalCandidate SAC " +
				   		" where " +
				   		" SACL.selfAppraisalCandidateId = SAC.selfAppraisalCandidateId and" +
				   		" SAC.isActive = 'Y' and " +  
				   		" SAC.selfAppraisalDesignation.selfAppraisalDesignationId in (:desigList) and " +
				   		" SAC.selfAppraisalDesignation.isActive = 'Y' and " +
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
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			   queryStr.append(" and SACL.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			   queryStr.append(" and SACL.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			   queryStr.append(" and SACL.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		   }
		   queryStr.append(" group by SAC.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				" SAC.selfAppraisalCandidateId, " +
		   				" SACL.selfAppraisalLocationScopeId");   
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		   if(locationValueSet != null && locationValueSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
		   }
		   if(stateId != null && stateId.longValue() > 0){
			   query.setParameter("stateId", stateId);
		   }
		   if(desigList != null ){
			   query.setParameterList("desigList", desigList);  
		   }
		   return query.list();  
	   }
	   public List<Object[]> getLocationListByCndIdAndScopeId(Set<Long> cndIdList, Set<Long> selfAppLocationScpIdList){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select " +
		   				" SACL.selfAppraisalCandidate.selfAppraisalCandidateId, " +
		   				" SACL.selfAppraisalLocationScopeId," +
		   				" SACL.locationValue " +
		   				" from " +
		   				" SelfAppraisalCandidateLocation SACL " +
		   				" where " +
		   				" SACL.selfAppraisalCandidate.selfAppraisalCandidateId in (:cndIdList) and " +
		   				" SACL.selfAppraisalLocationScopeId in (:selfAppLocationScpIdList) and " +
		   				" SACL.selfAppraisalCandidate.isActive = 'Y' ");
		   Query query = getSession().createQuery(queryStr.toString());
		   query.setParameterList("cndIdList", cndIdList);
		   query.setParameterList("selfAppLocationScpIdList", selfAppLocationScpIdList);  
		   return query.list();        
	   }
	   public List<Object[]> getTotalLeadersDesignationBy(List<Long> desigIdList,Long userAccessLevelId,Set<Long> locationValueSet,Date fromDate,Date toDate){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select " +
		   				   " SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId, " +
		   				   " count( distinct SACL.selfAppraisalCandidate.selfAppraisalCandidateId ) " +
		   				   " from SelfAppraisalCandidateLocation SACL where " +
		   				   " SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:desigIdList) " +
		   				   " and SACL.selfAppraisalCandidate.isActive = 'Y' " +
		   				   " and SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' ");
		   if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.state.stateId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.district.districtId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			   queryStr.append(" and SACL.userAddress.tehsil.tehsilId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			   queryStr.append(" and SACL.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			   queryStr.append(" and SACL.userAddress.panchayat.panchayatId in (:userAccessLevelValues) "); 
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			   queryStr.append(" and SACL.userAddress.ward.constituencyId in (:userAccessLevelValues) "); 
		   }
		   queryStr.append(" group by SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId ");   
		   Query query = getSession().createQuery(queryStr.toString());	
	   
		   if(locationValueSet != null && locationValueSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
		   }
		   if(desigIdList != null ){
			   query.setParameterList("desigIdList", desigIdList);  
		   }
		   return query.list();   
	   }
	   public List<Object[]> getDesignationListDtls(Long userAccessLevelId, Set<Long> locationValueSet){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select distinct SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				   " SACL.selfAppraisalCandidate.selfAppraisalDesignation.designation," +
		   				   " SACL.selfAppraisalCandidate.selfAppraisalDesignation.orderNo " +
		   				   " from SelfAppraisalCandidateLocation SACL where " +
		   				   " SACL.selfAppraisalCandidate.isActive = 'Y' " +  
		   				   " and SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' ");
		   if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.state.stateId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.district.districtId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			   queryStr.append(" and SACL.userAddress.tehsil.tehsilId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			   queryStr.append(" and SACL.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			   queryStr.append(" and SACL.userAddress.panchayat.panchayatId in (:userAccessLevelValues) "); 
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			   queryStr.append(" and SACL.userAddress.ward.constituencyId in (:userAccessLevelValues) "); 
		   }
		   queryStr.append(" order by  SACL.selfAppraisalCandidate.selfAppraisalDesignation.orderNo ");
		   Query query = getSession().createQuery(queryStr.toString());	  
		   if(locationValueSet != null && locationValueSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
		   }
		   return query.list();    
	   }
}
