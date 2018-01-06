package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationNewDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocationNew;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateLocationNewDAO extends GenericDaoHibernate<SelfAppraisalCandidateLocationNew, Long> implements
		ISelfAppraisalCandidateLocationNewDAO {
       public SelfAppraisalCandidateLocationNewDAO(){
    	   super(SelfAppraisalCandidateLocationNew.class);
       }
       
       public List<Object[]> getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userTypeId,String type){
		     StringBuilder queryStr = new StringBuilder();
		     queryStr.append(" select " +
		     		 		" model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		     		        " model.selfAppraisalCandidate.selfAppraisalDesignation.designation," );
		            if(type.equalsIgnoreCase("Candiate")){
		            	queryStr.append(" model.selfAppraisalCandidate.selfAppraisalCandidateId ");
		            }else{
		            	queryStr.append(" count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)");
		            }
		     		
		     		queryStr.append(" from SelfAppraisalCandidateLocationNew model " +
		     		        " where " +
		     		        " model.selfAppraisalCandidate.isActive='Y' and model.isDeleted='N' " +
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
			     if(userTypeId != IConstants.STATE_TYPE_USER_ID){
			    	 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in(:designationIds) ");	 
			     }
		     	 queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId ");
		     	  if(type.equalsIgnoreCase("Candiate")){
		     		  queryStr.append(",model.selfAppraisalCandidate.selfAppraisalCandidateId");
		     	  }
		     	  queryStr.append(" order by model.selfAppraisalCandidate.selfAppraisalDesignation.orderNo ");
		     	  Query query = getSession().createQuery(queryStr.toString());
		     		
		 		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		 			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 		 }
		 		if(stateId != null && stateId.longValue() > 0){
		 			 query.setParameter("stateId", stateId);
		 		}
	 		  /* if(userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID){
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
		     	}else if(userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.INCHARGE_MINISTER_SUB_LEVEL_DESIG_IDS));	
		     	}else if(userTypeId.longValue() == IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.PARLIAMENT_INCHARGE_SUB_LEVEL_DESIG_IDS));	
		     	}
		    	 return query.list();
	   }
       public List<Object[]> getDesignationWiseAllCandiateBasedOnUserAccessLevel(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId,List<Long> designationIds){
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
				   		" SACL.selfAppraisalCandidate.isActive = 'Y' and SACL.isDeleted='N' and " +  
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
		   }else if (userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID) {
			   queryStr.append(" and SACL.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		   }
		   if(userTypeId != null && userTypeId > 0 && userTypeId != IConstants.STATE_USER_TYPE_ID){
			  queryStr.append(" and SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) "); 
		   }
		   if (designationIds != null && designationIds.size() > 0) {
			   queryStr.append(" and SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) ");
		   }
		   queryStr.append(" group by SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				  " SACL.selfAppraisalCandidate.selfAppraisalCandidateId, " +
		   				  " SACL.locationScopeId,SACL.locationValue ");   
		   queryStr.append(" order by SACL.selfAppraisalCandidate.selfAppraisalDesignation.orderNo ");
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		   
		   if(locationValueSet != null && locationValueSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
		   }
		   if(stateId != null && stateId.longValue() > 0){
			   query.setParameter("stateId", stateId);
		   }
		   if(designationIds != null && designationIds.size() > 0){
			   query.setParameterList("designationIds",designationIds);   
		   }
		   if(userTypeId != null && userTypeId > 0 && userTypeId != IConstants.STATE_USER_TYPE_ID) {
			   if(userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.GENERAL_SECRETARY_SUB_LEVEL_DESIG_IDS));
		       }else if(userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.ORGANIZING_SECRETARY_SUB_LEVEL_DESIG_IDS));
		       }else if(userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.SECRETARY_SUB_LEVEL_DESIG_IDS));
		       }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.MP_SUB_LEVEL_DESIG_IDS));
		      }else if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID){
		      		query.setParameterList("designationIds",Arrays.asList(IConstants.DISTRICT_PRESIDENT_SUB_LEVEL_DESIG_IDS));
		      }else if(userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.INCHARGE_MINISTER_SUB_LEVEL_DESIG_IDS));	
		      }else if(userTypeId.longValue() == IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.PARLIAMENT_INCHARGE_SUB_LEVEL_DESIG_IDS));	
		      }     
		   }
	     
		   return query.list();  
	   }
       
       public Object[] getAllCandidateLocations(Long cadreId,Long categoryId){
 
    	   	StringBuilder str = new StringBuilder();
    	   
   			str.append("select distinct model.addressId,model.locationScopeId,model.locationValue " +
   				" from SelfAppraisalCandidateLocationNew model " +
   				" where model.selfAppraisalCandidate.tdpCadreId = :cadreId " +
   				" and  model.selfAppraisalTourCategoryId =:categoryId");
   			
   			Query query = getSession().createQuery(str.toString());
   		
   			query.setParameter("cadreId", cadreId);
   			query.setParameter("categoryId", categoryId);
   		
   			return (Object[])query.uniqueResult();
    	      		 
       }
       
       public List<Object[]> getLocationValuesOfCandidate(Long candidateId,Long categoryId){
    	   StringBuilder str = new StringBuilder();
    	   
  			str.append("select distinct model.addressId,model.locationScopeId,model.locationValue " +
  				" from SelfAppraisalCandidateLocationNew model " +
  				" where model.isDeleted='N' and model.selfAppraisalCandidateId = :candidateId " +
  				" and  model.selfAppraisalTourCategoryId =:categoryId");
  			
  			Query query = getSession().createQuery(str.toString());
  		
  			query.setParameter("candidateId", candidateId);
  			query.setParameter("categoryId", categoryId);
  			
  			return query.list();    	       	  
       }
    @SuppressWarnings("unchecked")
	public List<Object[]> getLocationValuesOfCandidate1(Long candidateId,Long categoryId,Long tourTypeId){
    	   StringBuilder str = new StringBuilder();
    	   
  			str.append("select distinct model.addressId,model.locationScopeId,model.locationValue " +
  				" from SelfAppraisalCandidateLocationNew model " +
  				" where model.isDeleted='N' and model.selfAppraisalCandidateId = :candidateId " +
  				" and  model.selfAppraisalTourCategoryId =:categoryId and model.tourTypeId = :tourTypeId ");
  			
  			Query query = getSession().createQuery(str.toString());
  		
  			query.setParameter("candidateId", candidateId);
  			query.setParameter("categoryId", categoryId);
  			query.setParameter("tourTypeId", tourTypeId);
  			return query.list();    	       	  
       }
       @SuppressWarnings("unchecked")
	public List<Object[]> getLocationWiseCandidate(Long cadreId){
    	   StringBuilder str = new StringBuilder();
    	   
  			str.append("select distinct model.selfAppraisalCandidateLocationNewId,model.selfAppraisalCandidateId," +
  					" model.selfAppraisalTourCategoryId," +
  					" model.selfAppraisalTourCategory.tourCategory," +
  					" model.tourTypeId,model.tourType.tourType," +
  					" model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
  					" model.selfAppraisalCandidate.selfAppraisalDesignation.designation " +
  				" from SelfAppraisalCandidateLocationNew model " +
  				" where model.selfAppraisalCandidate.tdpCadreId = :cadreId " +
  				" and  model.isDeleted ='N' and model.tourType.isDeleted = 'N' " +
  				" and model.selfAppraisalTourCategory.isDeleted ='N' and model.selfAppraisalCandidate.selfAppraisalDesignation.isActive ='Y' " +
  				" group by model.selfAppraisalCandidateId,model.selfAppraisalTourCategoryId,model.tourTypeId " +
  				" order by model.selfAppraisalCandidate.selfAppraisalDesignation.orderNo ");
  			Query query = getSession().createQuery(str.toString());
  				query.setParameter("cadreId", cadreId);
  			return query.list();    	       	  
       }
	//Constituency Page Query
	public List<Object[]> getLocationWiseTourMemberDetails(Long locationTypeId,List<Long> locationValues,Long stateId) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  "
						+ " SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId,"+// 0
						" SACL.selfAppraisalCandidate.selfAppraisalDesignation.designation,"+ // 1
						" SACL.selfAppraisalCandidate.selfAppraisalCandidateId,"+ // 2
						" SACL.selfAppraisalCandidate.tdpCadre.firstname,"+ // 3
						" SACL.locationScopeId,"+ // 4
						" SACL.locationValue "+ // 5
						" from "
						+ " SelfAppraisalCandidateLocationNew SACL "
						+ " where "
						+ " SACL.selfAppraisalCandidate.isActive = 'Y' and SACL.isDeleted='N' and "
						+ " SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' and " 
						+ " SACL.userAddress.state.stateId = :stateId ");

		if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0) {

			if (locationTypeId == 2l) { // STATE
				queryStr.append(" and SACL.userAddress.state.stateId in(:locationValues) ");
			} else if (locationTypeId == IConstants.DISTRICT_SCOPE_ID) {
				queryStr.append(" and SACL.userAddress.district.districtId in(:locationValues) ");
			} else if (locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
				queryStr.append(" and SACL.userAddress.constituency.constituencyId in(:locationValues) ");
			} else if (locationTypeId == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID) {
				queryStr.append(" and SACL.userAddress.parliamentConstituency.constituencyId in(:locationValues) ");
			} else if (locationTypeId == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID) {
				queryStr.append(" and SACL.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
			}
		}
	
		queryStr.append(" group by SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId,"
				+ " SACL.selfAppraisalCandidate.selfAppraisalCandidateId, "
				+ " SACL.locationScopeId,SACL.locationValue");
		queryStr.append(" order by SACL.selfAppraisalCandidate.selfAppraisalDesignation.orderNo ");

		Query query = getSession().createQuery(queryStr.toString());
        query.setParameter("stateId", stateId);
		if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0) {
			query.setParameterList("locationValues", locationValues);
		}
		return query.list();
	}
	public List<Object[]> getUniqueCandiateBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userTypeId){
	     StringBuilder queryStr = new StringBuilder();
	            queryStr.append(" select distinct " +
			            		" model.selfAppraisalCandidate.tdpCadreId," +
			            		" model.selfAppraisalCandidate.tdpCadre.firstname," +
			            		" model.selfAppraisalCandidate.tdpCadre.memberShipNo," +
			            		" model.selfAppraisalCandidate.tdpCadre.mobileNo ");
	     		queryStr.append(" from SelfAppraisalCandidateLocationNew model " +
			     		        " where " +
			     		        " model.selfAppraisalCandidate.isActive='Y' and model.isDeleted='N' " +
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
		     if(userTypeId != IConstants.STATE_TYPE_USER_ID){
		    	 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in(:designationIds) ");	 
		     }
	     	
	     	  Query query = getSession().createQuery(queryStr.toString());
	     		
	 		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	 			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	 		 }
	 		if(stateId != null && stateId.longValue() > 0){
	 			 query.setParameter("stateId", stateId);
	 		}
	        if(userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	     		query.setParameterList("designationIds",Arrays.asList(IConstants.GENERAL_SECRETARY_SUB_LEVEL_DESIG_IDS));
	     	}else if(userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID){
	     		query.setParameterList("designationIds",Arrays.asList(IConstants.ORGANIZING_SECRETARY_SUB_LEVEL_DESIG_IDS));
	     	}else if(userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID){
	     		query.setParameterList("designationIds",Arrays.asList(IConstants.SECRETARY_SUB_LEVEL_DESIG_IDS));
	     	}else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
	     		query.setParameterList("designationIds",Arrays.asList(IConstants.MP_SUB_LEVEL_DESIG_IDS));
	     	}else if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID){
	     		query.setParameterList("designationIds",Arrays.asList(IConstants.DISTRICT_PRESIDENT_SUB_LEVEL_DESIG_IDS));
	     	}else if(userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
	     		query.setParameterList("designationIds",Arrays.asList(IConstants.INCHARGE_MINISTER_SUB_LEVEL_DESIG_IDS));	
	     	}else if(userTypeId.longValue() == IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
	     		query.setParameterList("designationIds",Arrays.asList(IConstants.PARLIAMENT_INCHARGE_SUB_LEVEL_DESIG_IDS));	
	     	}
	    	 return query.list();
  }
	  public List<Object[]> getCandidateDesignationDetails(Long tdpCadreId){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.designation," +//1
		   				" SACL.selfAppraisalCandidate.selfAppraisalCandidateId "+
		   		   		" from " +
				   		" SelfAppraisalCandidateLocationNew SACL " +
				   		" where " +
				   		" SACL.selfAppraisalCandidate.isActive = 'Y' and SACL.isDeleted='N' and " +  
				   		" SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' and " +
				   		" SACL.selfAppraisalCandidate.tdpCadre.tdpCadreId=:tdpCadreId ");
		   
		   queryStr.append(" group by SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				  " SACL.selfAppraisalCandidate.selfAppraisalCandidateId ");   
		   queryStr.append(" order by SACL.selfAppraisalCandidate.selfAppraisalDesignation.orderNo ");
		   Query query = getSession().createQuery(queryStr.toString());	
		   query.setParameter("tdpCadreId", tdpCadreId);
		   return query.list();  
	   }
      
}
