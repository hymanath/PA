package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationNewDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocationNew;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateLocationNewDAO extends GenericDaoHibernate<SelfAppraisalCandidateLocationNew, Long> implements
		ISelfAppraisalCandidateLocationNewDAO {
       public SelfAppraisalCandidateLocationNewDAO(){
    	   super(SelfAppraisalCandidateLocationNew.class);
       }
       
       public List<Object[]> getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userTypeId){
		     StringBuilder queryStr = new StringBuilder();
		     queryStr.append(" select " +
		     		 		" model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		     		        " model.selfAppraisalCandidate.selfAppraisalDesignation.designation," +
		     		        " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)" +
		     		        " from SelfAppraisalCandidateLocationNew model " +
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
       public List<Object[]> getDesignationWiseAllCandiateBasedOnUserAccessLevel(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.designation," +//1
		   				" SACL.selfAppraisalCandidate.selfAppraisalCandidateId," +//2
		   				" SACL.selfAppraisalCandidate.tdpCadre.firstname," +//3 
		   				" SACL.locationScopeId," +//4
		   				" SACL.locationValue " +//5
		   		   		" from " +
				   		" SelfAppraisalCandidateLocationNew SACL " +
				   		" where " +
				   		" SACL.selfAppraisalCandidate.isActive = 'Y' and " +  
				   		" SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) and " +
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
		   
		   queryStr.append(" group by SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				  " SACL.selfAppraisalCandidate.selfAppraisalCandidateId, " +
		   				  " SACL.locationScopeId");   
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		   if(locationValueSet != null && locationValueSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
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
}
