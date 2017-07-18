package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetailsNew;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;
import com.itgrids.partyanalyst.utils.IConstants;

public class GovtDepartmentDesignationOfficerDetailsNewDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerDetailsNew, Long>
		implements IGovtDepartmentDesignationOfficerDetailsNewDAO {
      public GovtDepartmentDesignationOfficerDetailsNewDAO(){
    	  super(GovtDepartmentDesignationOfficerDetailsNew.class);
      }
      
public List<Object[]> getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(Long userId,List<Long> levelValues , Long levelId){
    	  
    	  StringBuilder sb = new StringBuilder();
    	  
    	sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId,model.govtOfficer.govtOfficerId," +
    	  		" model.govtOfficer.officerName, model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
    	  		" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
    	  		" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName  from GovtDepartmentDesignationOfficerDetailsNew model where " +
    	  		" model.isDeleted = 'N' ");
    	  
    	  
    	  if(levelId != null && levelId.longValue() >0l){
    		  sb.append(" and  model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :levelId " );
    	  }
    	  
    	  if(levelValues != null && levelValues.size() >0){
    		  sb.append(" and  model.govtDepartmentDesignationOfficer.levelValue in (:levelValues) ");
    	  }
    	  if(userId != null && userId.longValue() >0l){
    		  sb.append(" and  model.user.userId = :userId " );
    	  }
    	  Query query = getSession().createQuery(sb.toString());
    	  
    	  if(userId != null && userId.longValue() >0l){
    		  query.setParameter("userId", userId);  
    	  }
    	  
    	  if(levelId != null && levelId.longValue() >0l){
    		  query.setParameter("levelId", levelId); 
    	  }
    	  if(levelValues != null && levelValues.size() >0){
    		  query.setParameterList("levelValues", levelValues);
    	  }
    	  return query.list();
      }
      
      public List<GovtDepartmentDesignationOfficerNew> getDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId){
    		
    		StringBuilder sb = new StringBuilder();
    		
    		sb.append("select distinct model.govtDepartmentDesignationOfficer " +
    											" from GovtDepartmentDesignationOfficerDetailsNew model" +
    											" where model.govtDepartmentDesignationOfficer.govtDepartmentScopeId = :levelId" +
    											" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
    											" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId " +
    											" and model.govtOfficerId = :officerId " +
    											" and model.isDeleted = 'N'  " );
    		
    		Query query = getSession().createQuery(sb.toString());
    		
    		query.setParameter("levelId", levelId);
    		query.setParameter("levelValue", levelValue);
    		query.setParameter("designationId", designationId);
    		query.setParameter("officerId", officerId);
    		return query.list();
    	}
      
      public List<Object[]> getDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue){
  		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
  												" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
  												" from GovtDepartmentDesignationOfficerDetailsNew model" +
  												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentId = :govtDepartmentId" +
  												" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :levelId " +
  												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue ");
  		
  		query.setParameter("govtDepartmentId", govtDepartmentId);
  		query.setParameter("levelId", levelId);
  		query.setParameter("levelValue", levelValue);
  		return query.list();
  	}
      public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
  		Query query = getSession().createQuery("select distinct model.govtOfficer.govtOfficerId," +
  												" model.govtOfficer.officerName,model.govtOfficer.mobileNo " +
  												" from GovtDepartmentDesignationOfficerDetailsNew model" +
  												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
  												" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :levelId" +
  												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
  												" and model.isDeleted = 'N'");
  		query.setParameter("designationId", designationId);
  		query.setParameter("levelId", levelId);
  		query.setParameter("levelValue", levelValue);
  		
  		return query.list();
  	}
      
      public List<Object[]> getOldDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue){
    		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
    												" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
    												" from GovtDepartmentDesignationOfficerDetails model" +
    												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentId = :govtDepartmentId" +
    												" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId " +
    												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue ");
    		
    		query.setParameter("govtDepartmentId", govtDepartmentId);
    		query.setParameter("levelId", levelId);
    		query.setParameter("levelValue", levelValue);
    		return query.list();
    	  
    	 /* Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
								" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
								" from GovtDepartmentDesignationOfficerDetailsNew model" +
								" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentId = :govtDepartmentId" +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.alertRegionScopes.alertRegionScopesId = :levelId " +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationValue = :levelValue ");
			
			query.setParameter("govtDepartmentId", govtDepartmentId);
			query.setParameter("levelId", levelId);
			query.setParameter("levelValue", levelValue);
			return query.list();*/
    	}
      
      public List<Object[]> getNewDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue){
  		/*Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
  												" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
  												" from GovtDepartmentDesignationOfficerDetails model" +
  												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentId = :govtDepartmentId" +
  												" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId " +
  												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue ");
  		
  		query.setParameter("govtDepartmentId", govtDepartmentId);
  		query.setParameter("levelId", levelId);
  		query.setParameter("levelValue", levelValue);
  		return query.list();*/
  	  
  	  Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
								" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
								" from GovtDepartmentDesignationOfficerDetailsNew model" +
								" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentId = :govtDepartmentId" +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.alertRegionScopes.alertRegionScopesId = :levelId " +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationValue = :levelValue ");
			
			query.setParameter("govtDepartmentId", govtDepartmentId);
			query.setParameter("levelId", levelId);
			query.setParameter("levelValue", levelValue);
			return query.list();
  	}
      
      public List<Object[]> getOldOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
    		Query query = getSession().createQuery("select distinct model.govtOfficer.govtOfficerId," +
    												" model.govtOfficer.officerName,model.govtOfficer.mobileNo " +
    												" from GovtDepartmentDesignationOfficerDetails model" +
    												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
    												" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId" +
    												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
    												" and model.isDeleted = 'N'");
    		query.setParameter("designationId", designationId);
    		query.setParameter("levelId", levelId);
    		query.setParameter("levelValue", levelValue);
    		
    		return query.list();
    	  
    	 /* Query query = getSession().createQuery("select distinct model.govtOfficer.govtOfficerId," +
								" model.govtOfficer.officerName,model.govtOfficer.mobileNo " +
								" from GovtDepartmentDesignationOfficerDetailsNew model" +
								" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.alertRegionScopes.alertRegionScopesId = :levelId" +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationValue = :levelValue" +
								" and model.isDeleted = 'N'");
			query.setParameter("designationId", designationId);
			query.setParameter("levelId", levelId);
			query.setParameter("levelValue", levelValue);
				
			return query.list();*/
    	}
      
      public List<Object[]> getNewOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
  		/*Query query = getSession().createQuery("select distinct model.govtOfficer.govtOfficerId," +
  												" model.govtOfficer.officerName,model.govtOfficer.mobileNo " +
  												" from GovtDepartmentDesignationOfficerDetails model" +
  												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
  												" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId" +
  												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
  												" and model.isDeleted = 'N'");
  		query.setParameter("designationId", designationId);
  		query.setParameter("levelId", levelId);
  		query.setParameter("levelValue", levelValue);
  		
  		return query.list();*/
  	  
  	  Query query = getSession().createQuery("select distinct model.govtOfficer.govtOfficerId," +
								" model.govtOfficer.officerName,model.govtOfficer.mobileNo " +
								" from GovtDepartmentDesignationOfficerDetailsNew model" +
								" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.alertRegionScopes.alertRegionScopesId = :levelId" +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationValue = :levelValue" +
								" and model.isDeleted = 'N'");
			query.setParameter("designationId", designationId);
			query.setParameter("levelId", levelId);
			query.setParameter("levelValue", levelValue);
				
			return query.list();
  	}
      
      public List<Long> getOldDesignationOfficerIds(Long levelId,Long levelValue,Long designationId,Long officerId){
    		
    		StringBuilder sb = new StringBuilder();
    		sb.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
    											" from GovtDepartmentDesignationOfficerDetails model" +
    											" where model.govtDepartmentDesignationOfficer.govtDepartmentLevelId = :levelId" +
    											" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
    											" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId " +
    											" and model.govtOfficerId = :officerId " +
    											" and model.isDeleted = 'N'  " );
    		
    		/*sb.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
  								" from GovtDepartmentDesignationOfficerDetailsNew model" +
  								" where model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.alertRegionScopes.alertRegionScopesId = :levelId" +
  								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationValue = :levelValue" +
  								" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId " +
  								" and model.govtOfficerId = :officerId " +
  								" and model.isDeleted = 'N'  " );*/
    		
    		Query query = getSession().createQuery(sb.toString());
    		
    		query.setParameter("levelId", levelId);
    		query.setParameter("levelValue", levelValue);
    		query.setParameter("designationId", designationId);
    		query.setParameter("officerId", officerId);
    		return query.list();
    	}
      public List<Long> getOldDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId){
  		
  		StringBuilder sb = new StringBuilder();
  		/*sb.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
  											" from GovtDepartmentDesignationOfficerDetails model" +
  											" where model.govtDepartmentDesignationOfficer.govtDepartmentLevelId = :levelId" +
  											" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
  											" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId " +
  											" and model.govtOfficerId = :officerId " +
  											" and model.isDeleted = 'N'  " );*/
  		
  		sb.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
								" from GovtDepartmentDesignationOfficerDetailsNew model" +
								" where model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.alertRegionScopes.alertRegionScopesId = :levelId" +
								" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationValue = :levelValue" +
								" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId " +
								" and model.govtOfficerId = :officerId " +
								" and model.isDeleted = 'N'  " );
  		
  		Query query = getSession().createQuery(sb.toString());
  		
  		query.setParameter("levelId", levelId);
  		query.setParameter("levelValue", levelValue);
  		query.setParameter("designationId", designationId);
  		query.setParameter("officerId", officerId);
  		return query.list();
  	}
     @SuppressWarnings("unchecked")
	public List<Object[]> getDesignationsNameForUser(Long userId){
  		Query query = getSession().createQuery("select model.govtOfficer.officerName," +
  				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName " +
  												" from GovtDepartmentDesignationOfficerDetailsNew model" +
  												" where model.user.userId = :userId" +
  												" and model.isDeleted = 'N' ");
  		query.setParameter("userId", userId);
  		return query.list();
  	}
	public List<Long> getGovtDeptDesigOfficerIdListByUserId(Long userId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId from GovtDepartmentDesignationOfficerDetailsNew model " +
						" where  model.userId = :userId and model.isDeleted = 'N' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Long> getGovtDeptDesigIdListByUserId(Long userId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId " +
						" from " +
						" GovtDepartmentDesignationOfficerDetailsNew model " +
						" where  model.userId = :userId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Long> getGovtDeptScopeIdsForUserId(Long userId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " +
						" from " +
						" GovtDepartmentDesignationOfficerDetailsNew model " +
						" where  model.userId = :userId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("userId", userId);
		return query.list();
		
	}
	
	public List<Object[]> getGovtAllDepartmentDetails(){
		
		Query query = getSession().createQuery(" SELECT distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
												" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName " +
												" FROM GovtDepartmentDesignationOfficerDetailsNew model " +
												" WHERE model.isDeleted ='N' and model.deptActive = 'Y' ") ;
		
		return query.list();
		
	}
	public List<Object[]> getDesigNameForUser(Long userId){
	    Query query = getSession().createQuery("select distinct model.user.userName , " +
	        " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName, " +
	        " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " +
	        " model.govtDepartmentDesignationOfficer.govtDepartmentScopeId, model.govtDepartmentDesignationOfficer.levelValue ," +
	        " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.shortName, " +
	        "  model.userId " +//5
	        " from GovtDepartmentDesignationOfficerDetailsNew model " +
	        " where model.userId = :userId");
	    query.setParameter("userId", userId);
	    return query.list();
	  }
	
	public List<Object[]> getDesignationNameForUsers(List<Long> userIdsList){
	    Query query = getSession().createQuery("select distinct model.user.userName , " +
	        " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName, " +
	        " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " +
	        " model.govtDepartmentDesignationOfficer.govtDepartmentScopeId, model.govtDepartmentDesignationOfficer.levelValue ," +
	        " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.shortName, " +
	        "  model.userId " +//5
	        " from GovtDepartmentDesignationOfficerDetailsNew model " +
	        " where model.userId in (:userIdsList) ");
	    query.setParameterList("userIdsList", userIdsList);
	    return query.list();
	  }
	
	public List<String> getHigherOfficerMobileNums(List<Long> designationIds,Long locationTypeId,List<Long> levelValuesList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.govtOfficer.mobileNo from GovtDepartmentDesignationOfficerDetailsNew model  ");
		queryStr.append(" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId in (:designationIds) and " +
				" model.isDeleted = 'N' and model.govtOfficer.mobileNo is not null ");
		
		
		if(locationTypeId != null && levelValuesList != null && levelValuesList.size() > 0){
			if( locationTypeId.longValue() == 1L){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.govtUserAddress.stateId in (:levelValuesList) ");
			}else if(locationTypeId.longValue() == 2L){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.govtUserAddress.zoneId  in (:levelValuesList) ");
			}else if(locationTypeId.longValue() == 3l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.govtUserAddress.regionId in (:levelValuesList)  ");
			}else if(locationTypeId.longValue() == 4l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.govtUserAddress.circleId in (:levelValuesList)  ");
			}else if(locationTypeId.longValue() == 5l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.govtUserAddress.districtId in (:levelValuesList) ");
			}else if(locationTypeId.longValue() == 6l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.govtUserAddress.divisionId  in (:levelValuesList)  ");
			}else if(locationTypeId.longValue() == 7l){
				queryStr.append(" and  model.govtDepartmentDesignationOfficer.govtUserAddress.subDivisionId in (:levelValuesList)  ");
			}else if(locationTypeId.longValue() == 8l){
				queryStr.append(" and  model.govtDepartmentDesignationOfficer.govtUserAddress.tehsilId  in (:levelValuesList)  ");
			}else if(locationTypeId.longValue() == 9l){
				queryStr.append(" and  model.govtDepartmentDesignationOfficer.govtUserAddress.localElectionBodyId  in (:levelValuesList)  ");
			}else if(locationTypeId.longValue() == 10l){
				queryStr.append(" and  model.govtDepartmentDesignationOfficer.govtUserAddress.panchayatId  in (:levelValuesList)  ");
			}else if(locationTypeId.longValue() == 11l){
				queryStr.append(" and  model.govtDepartmentDesignationOfficer.govtUserAddress.wardId  in (:levelValuesList)  ");
			}
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("designationIds", designationIds);
		if(locationTypeId !=null && levelValuesList != null && levelValuesList.size()>0L)
			query.setParameterList("levelValuesList", levelValuesList);
		
		return query.list();
	}
	
	public List<Long> getuserIdDtlsForDesignationOfficerId(Long assignedOfficerId){
		Query query = getSession().createQuery("select distinct model.userId from GovtDepartmentDesignationOfficerDetailsNew model ,AlertAssignedOfficerNew model2  where " +
				" model.govtDepartmentDesignationOfficerId = :assignedOfficerId and model.govtDepartmentDesignationOfficerId = model2.govtDepartmentDesignationOfficerId and model2.isDeleted='N'  ");
		query.setParameter("assignedOfficerId", assignedOfficerId);
		return query.list();
	}
	public List<Object[]> getLocationNameByAssignedOficer(Long officerId){
		Query query = getSession().createQuery("select distinct model.user.userName," +
				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName," +
				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
				" model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName" +
				" from GovtDepartmentDesignationOfficerDetailsNew model " +
				" where model.userId = :officerId");
		query.setParameter("officerId", officerId);
		return  query.list();
	}
	public List<Object[]> getDeptListForGreivanceReport(Set<Long> deptIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId, " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName " +
						" from GovtDepartmentDesignationOfficerDetailsNew model " +
						" where" +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList)" +
						" and model.isDeleted = 'N' ");
		//queryStr.append(" and model.deptActive = 'Y' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("deptIdList", deptIdList);
		return query.list();
	}
	public List<Object[]> getGovtDepaDesigIdForLoginOfficer(Long userId, Long govtDeptId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId," +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId, " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId, " +
						" model.govtDepartmentDesignationOfficer.levelValue " +
						" from GovtDepartmentDesignationOfficerDetailsNew model " +
						" where" +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :govtDeptId" +
						" and model.userId = :userId " +
						" and model.isDeleted = 'N' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("govtDeptId", govtDeptId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getGovtDepaDesigOfficerDtls(List<Long> govtDepartmentDesignationIds, Long govtDepartmentScopeId, Long levelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId, " +
						" model.govtOfficer.govtOfficerId, " +
						" model.govtOfficer.officerName " +
						" from " +
						" GovtDepartmentDesignationOfficerDetailsNew model " +
						" where " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId in (:govtDepartmentDesignationIds) " +
						" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :govtDepartmentScopeId " +
						" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue " +
						" and model.isDeleted = 'N' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("govtDepartmentScopeId", govtDepartmentScopeId);
		query.setParameter("levelValue", levelValue);
		query.setParameterList("govtDepartmentDesignationIds", govtDepartmentDesignationIds);
		return query.list();
	}
	
	public List<String> getGovtOfficerMobileNums(Long locationId,String locationType){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select gon.mobile_no as mobileNum from " +
				" govt_department_work_location gdwl,govt_department_designation_officer_new gddon," +
				" govt_department_designation_officer_details_new gddodn,govt_officer_new gon " +
				" where gddodn.govt_department_designation_officer_id=gddon.govt_department_designation_officer_id and " +
				" gddon.level_value=gdwl.govt_department_work_location_id and gdwl.is_deleted='N' " +
				" and gddodn.govt_officer_id = gon.govt_officer_id " +
				" and gdwl.alert_region_scopes_id=:scopeId and gdwl.location_value=:locationId and gdwl.govt_department_id=:departmentId " +
				" and gddon.govt_department_designation_id =:designationId ");
		
		/*sb.append(" select model.govtOfficer.mobileNo " +
				" from GovtDepartmentDesignationOfficerDetailsNew model " +
				" where model.isDeleted='N' and model.deptActive='Y' " +
				" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.isDeleted='N' " +
				" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.govtDepartmentId=:departmentId " +
				" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.alertRegionScopesId=:scopeId " +
				" and model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationValue=:locationId " +
				" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId=:designationId ");*/
		
		Query query = getSession().createSQLQuery(sb.toString()).addScalar("mobileNum", Hibernate.STRING);
		
		query.setParameter("departmentId", 20l);
		
		if(locationType.equalsIgnoreCase("tehsil")){
			query.setParameter("scopeId", 5l);
			query.setParameter("designationId", IConstants.PANCHAYATRAJ_MPDO_DESIGNATION_ID);
		}else if(locationType.equalsIgnoreCase("village")){
			query.setParameter("scopeId", 6l);
			query.setParameter("designationId", IConstants.PANCHAYATRAJ_PANCHAYAT_SECRERATORY_ID);
		}
		query.setParameter("locationId", locationId);
		
		return query.list();
	}

	public List<Object[]> getDesigAndDepartForUser(List<Long> userIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.user.userId,model.user.userName," +
				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName," +
				" model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName," +
				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName" +
				" from GovtDepartmentDesignationOfficerDetailsNew model" +
				" where model.userId in (:userIds) and model.isDeleted = 'N'");
		Query  query = getSession().createQuery(sb.toString());
		query.setParameterList("userIds", userIds);
		return query.list();
	}
	public List<Object[]> getLoginUserDetails(Long userId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
				" model.govtDepartmentDesignationOfficer.levelValue,model.govtDepartmentDesignationOfficer.govtDepartmentScopeId " +
				" from GovtDepartmentDesignationOfficerDetailsNew model " +
						" where  model.userId = :userId and model.isDeleted = 'N' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Long> getNewDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId){
  		
  		StringBuilder sb = new StringBuilder();
  		/*sb.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
  											" from GovtDepartmentDesignationOfficerDetails model" +
  											" where model.govtDepartmentDesignationOfficer.govtDepartmentLevelId = :levelId" +
  											" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
  											" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId " +
  											" and model.govtOfficerId = :officerId " +
  											" and model.isDeleted = 'N'  " );*/
  		
  		sb.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
								" from GovtDepartmentDesignationOfficerDetailsNew model" +
								" where model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :levelId" +
								" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
								" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId " +
								" and model.govtOfficerId = :officerId " +
								" and model.isDeleted = 'N'  " );
  		
  		Query query = getSession().createQuery(sb.toString());
  		
  		query.setParameter("levelId", levelId);
  		query.setParameter("levelValue", levelValue);
  		query.setParameter("designationId", designationId);
  		query.setParameter("officerId", officerId);
  		return query.list();
  	}
	public List<String> getOfficerMobilenNo(Long userId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.govtOfficer.mobileNo " +
						" from " +
						" GovtDepartmentDesignationOfficerDetailsNew model " +
						" where  model.userId = :userId and isDeleted ='N'");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("userId", userId);
		return query.list();
	} 
	
	public List<Object[]> getOfficerDesingationDetails(Long userId){
		
		Query query = getSession().createQuery(" select model.govtDepartmentDesignationOfficerId,model.govtOfficerId" +
				"  from GovtDepartmentDesignationOfficerDetailsNew model" +
				" where model.userId =:userId and model.isDeleted ='N' ");
		
		query.setParameter("userId", userId);
		
		return query.list();
	}
}
