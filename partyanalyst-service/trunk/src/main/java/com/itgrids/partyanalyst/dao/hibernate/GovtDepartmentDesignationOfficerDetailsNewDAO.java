package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetailsNew;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;

public class GovtDepartmentDesignationOfficerDetailsNewDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerDetailsNew, Long>
		implements IGovtDepartmentDesignationOfficerDetailsNewDAO {
      public GovtDepartmentDesignationOfficerDetailsNewDAO(){
    	  super(GovtDepartmentDesignationOfficerDetailsNew.class);
      }
      
public List<Object[]> getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(Long userId,List<Long> levelValues , Long levelId){
    	  
    	  StringBuilder sb = new StringBuilder();
    	  
    	  sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId,model.govtOfficer.govtOfficerId," +
    	  		" model.govtOfficer.officerName, model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
    	  		"model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName  from GovtDepartmentDesignationOfficerDetailsNew model where " +
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
      
      public List<Long> getDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId){
    		
    		StringBuilder sb = new StringBuilder();
    		
    		sb.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
    											" from GovtDepartmentDesignationOfficerDetailsNew model" +
    											" where model.govtDepartmentDesignationOfficer.govtDepartmentScopeId = :levelId" +
    											" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
    											" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId " +
    											" and model.govtOfficerId = :officerId " +
    											" and model.isDeleted = 'N'  " );
    		
    		/*if(officerId !=null && officerId.longValue()>0l){
    			sb.append(" ");
    		}*/
    	
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
						" where  model.userId = :userId");
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
												" WHERE model.isDeleted ='N' ") ;
		
		return query.list();
		
	}
}
