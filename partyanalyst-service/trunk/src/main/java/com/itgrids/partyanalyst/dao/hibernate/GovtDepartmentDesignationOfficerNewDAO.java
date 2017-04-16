package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerNewDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;

public class GovtDepartmentDesignationOfficerNewDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerNew, Long> implements IGovtDepartmentDesignationOfficerNewDAO {

	public GovtDepartmentDesignationOfficerNewDAO(){
		super(GovtDepartmentDesignationOfficerNew.class);
	}
	
	public List<Object[]> getDeptAndDesignationNames(Long govtDepDesigOffcrId){
		
	    	  StringBuilder sb = new StringBuilder();
	    	  
	    	  sb.append(" select model.govtDepartmentDesignation.govtDepartmentDesignationId,model.govtDepartmentDesignation.designationName," +
	    	  		" model.govtDepartment.govtDepartmentId,model.govtDepartment.departmentName   from GovtDepartmentDesignationOfficerNew model where " +
	    	  		" model.isDeleted = 'N' ");
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
	    	  }
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
	    	  }
	    	  
	    	  return query.list();
	      }
	public List<Object[]> getSubOrdinateLevels(Long govtDepartmentDesignationId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct model.govtDepartmentScope.govtDepartmentScopeId," +
				"model.govtDepartmentScope.levelName,model.govtDepartmentScope.color " +
				" from GovtDepartmentDesignationOfficerNew model where ");
		if(govtDepartmentDesignationId != null && govtDepartmentDesignationId.longValue() >0l){
  		  sb.append("   model.govtDepartmentDesignationId = :govtDepartmentDesignationId " );
  	  }
		 Query query = getSession().createQuery(sb.toString());
   	  
   	  if(govtDepartmentDesignationId != null && govtDepartmentDesignationId.longValue() >0l){
   		  query.setParameter("govtDepartmentDesignationId", govtDepartmentDesignationId);  
   	   }
		

	 return query.list();
    }
	
	/*1	STATE
	2	ZONE
	3	REGION
	4	CIRCLE
	5	DISTRICT
	6	DIVISION
	7	SUB DIVISION
	8	MANDAL
	9	MUNICIPALITY
	10	PANCHAYAT*/
	
	public List<Long> getGovtDepartmentDesignationOfficer(Long levelId,List<Long> levelValues,Long subDesignationId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.govtDepartmentDesignationOfficerId "
				+ " from GovtDepartmentDesignationOfficerNew model "
				+ " where model.govtDepartmentDesignationId = :subDesignationId "
				+ "  ");
		
		if(levelId !=null && levelValues !=null && levelValues.size()>0){
			if(levelId.longValue() == 1l){
				sb.append(" and model.govtUserAddress.stateId in (:levelValues) ");
			}else if(levelId.longValue() == 2l){
				sb.append(" and model.govtUserAddress.zoneId in (:levelValues) ");
			}else if(levelId.longValue() == 3l){
				sb.append(" and model.govtUserAddress.regionId in (:levelValues) ");
			}else if(levelId.longValue() == 4l){
				sb.append(" and model.govtUserAddress.circleId in (:levelValues) ");
			}else if(levelId.longValue() == 5l){
				sb.append(" and model.govtUserAddress.districtId in (:levelValues) ");
			}else if(levelId.longValue() == 6l){
				sb.append(" and model.govtUserAddress.divisionId in (:levelValues) ");
			}else if(levelId.longValue() == 7l){
				sb.append(" and model.govtUserAddress.subDivisionId in (:levelValues) ");
			}else if(levelId.longValue() == 8l){
				sb.append(" and model.govtUserAddress.tehsilId in (:levelValues) ");
			}else if(levelId.longValue() == 9l){
				sb.append(" and model.govtUserAddress.localElectionBodyId in (:levelValues) ");
			}else if(levelId.longValue() == 10l){
				sb.append(" and model.govtUserAddress.panchayatId in (:levelValues) ");
			}
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("levelValues",levelValues);
		query.setParameter("subDesignationId",subDesignationId);
		return query.list();
	}
	
	public List<Long> getGovtDepartmentDesinationOfficerId(Long designationId,Long levelId,List<Long> levelValues,Long userId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.govtDepartmentDesignationOfficerId "
				+ " from GovtDepartmentDesignationOfficerDetailsNew model "
				+ " where model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId "
				+ " and model.govtDepartmentDesignationOfficer.govtDepartmentScopeId = :levelId"
				+ " and model.govtDepartmentDesignationOfficer.levelValue in (:levelValues) "
				+ " and model.userId = :userId  ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("designationId",designationId);
		query.setParameter("levelId",levelId);
		query.setParameterList("levelValues",levelValues);
		query.setParameter("userId",userId);
		
		return query.list();
		
	}
	public Long getUserIdCount(Long userId){
		Query query = getSession().createQuery("select count(model) from GovtDepartmentDesignationOfficerNew model where model.userId = :userId ");
		query.setParameter("userId", userId);
		return (Long) query.uniqueResult();
	}
}
