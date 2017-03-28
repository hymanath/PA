package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateLocationDAO extends GenericDaoHibernate<SelfAppraisalCandidateLocation, Long> implements ISelfAppraisalCandidateLocationDAO {
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
	   public List<Object[]> getNoOfLeadersCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userTypeId){
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
		     	 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in(:designationIds) ");
		     	 
		     	 queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId ");
		     	  Query query = getSession().createQuery(queryStr.toString());
		     		
		 		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		 			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 		 }
		 		if(stateId != null && stateId.longValue() > 0){
		 			 query.setParameter("stateId", stateId);
		 		}
	 		   if(userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID){
	 		    	query.setParameterList("designationIds",Arrays.asList(IConstants.STATE_SUB_LEVEL_DESIG_IDS));
		     	}else if(userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
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
	   
	   public List<Object[]> getTotalLeaderDesignationWise(List<Long> designationIds,Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId){
			StringBuilder queryStr = new StringBuilder();
			 queryStr.append(" select model.selfAppraisalCandidate.selfAppraisalCandidateId," +//0
			 		         " model.selfAppraisalCandidate.tdpCadre.firstname," +//1
			 		         " model.selfAppraisalCandidate.tdpCadre.mobileNo," +//2
			 		         " model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//3
			 		         " model.selfAppraisalCandidate.selfAppraisalDesignation.designation," +//4
			 		         " model.selfAppraisalCandidate.tdpCadre.tdpCadreId " +//5
			 		         " from SelfAppraisalCandidateLocation model where model.selfAppraisalCandidate.isActive='Y' ");
				 if(designationIds != null && designationIds.size() > 0){
					 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in(:designationIds) ");
				 }
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
		      queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalCandidateId ");
			  Query query = getSession().createQuery(queryStr.toString());
			  if(designationIds != null && designationIds.size() > 0){
			  query.setParameterList("designationIds", designationIds);
			  }
			  if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
				   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			   }
			    if(stateId != null && stateId.longValue() > 0){
					 query.setParameter("stateId", stateId);
				}
			  return query.list();
		}
	   public List<Long> getCandiateIdList(Long userAccessLevelId, Set<Long> locationValueSet,List<Long> desigIdList){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select " +
			           " distinct model.selfAppraisalCandidate.selfAppraisalCandidateId " +
			           " from SelfAppraisalCandidateLocation model " +
			           " where " +
			           " model.selfAppraisalCandidate.isActive='Y' " +
			           " and model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:desigIdList) ");
		   if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			   queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			   queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			   queryStr.append(" and model.userAddress.panchayat.panchayatId in (:userAccessLevelValues) "); 
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			   queryStr.append(" and model.userAddress.ward.constituencyId in (:userAccessLevelValues) "); 
		   }
		   Query query = getSession().createQuery(queryStr.toString());
		   query.setParameterList("desigIdList", desigIdList);
		   if(userAccessLevelId != null){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
		   }
		   return query.list();  
	   }
	 public List<Object[]> getCandiateIdsScope(List<Long> candiateIds,String designations){
		 StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.selfAppraisalLocationScopeId,model.selfAppraisalCandidate.selfAppraisalCandidateId " +
		  				  " from SelfAppraisalCandidateLocation model where model.selfAppraisalCandidateId in (:candiateIds)");
				          if(designations.equalsIgnoreCase("MP/MLA/CI")){
				        	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId in (6,7,8) ");  
				          }else if(designations.equalsIgnoreCase("DistrictPresident")){
				        	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId in (2) and model.type='Incharge' ");   
				          }else if(designations.equalsIgnoreCase("Minister")){
				        	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId in (1) and model.type='Own' ");  
				          }
		  				  queryStr.append(" order by model.selfAppraisalCandidate.selfAppraisalDesignationId ");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setParameterList("candiateIds", candiateIds);
		 return query.list();
	 }
	 public List<Object[]> getCandiateLocation(Long locationScopeId,List<Long> candiateIds,String designationType){
		   
		    StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.selfAppraisalCandidate.selfAppraisalCandidateId,");
		    if(locationScopeId != null && locationScopeId == 1l){ //district
				  queryStr.append(" model.userAddress.district.districtId,");
				  queryStr.append(" model.userAddress.district.districtName ");
		    }else if(locationScopeId != null && locationScopeId == 2l){//Parliament
			     queryStr.append(" model.userAddress.parliamentConstituency.constituencyId,");
			     queryStr.append(" model.userAddress.parliamentConstituency.name ");
		    }else if(locationScopeId != null && locationScopeId == 3l){//Constituency
				  queryStr.append(" model.userAddress.constituency.constituencyId,");
				  queryStr.append(" model.userAddress.constituency.name ");
		    }
		    queryStr.append(" from SelfAppraisalCandidateLocation model where model.selfAppraisalCandidate.selfAppraisalCandidateId in (:candiateIds) ");
		    if(designationType != null && designationType.equalsIgnoreCase("DistrictPresident")){
		    	queryStr.append(" and model.type='Incharge' ");
		    }else if(designationType != null && designationType.equalsIgnoreCase("Minister")){
		    	queryStr.append(" and model.type='Own' ");
		    }
		    Query query = getSession().createQuery(queryStr.toString());
		    query.setParameterList("candiateIds", candiateIds);
		    return query.list();
	 }
}
